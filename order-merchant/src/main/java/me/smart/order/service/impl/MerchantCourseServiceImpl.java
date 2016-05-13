package me.smart.order.service.impl;

import me.smart.order.api.CategoryCourseInfo;
import me.smart.order.api.CategoryInfo;
import me.smart.order.api.CourseInfo;
import me.smart.order.api.Result;
import me.smart.order.api.merchant.request.*;
import me.smart.order.api.merchant.response.CategoryAddResponse;
import me.smart.order.api.merchant.response.CategoryListResponse;
import me.smart.order.api.merchant.response.CourseAddResponse;
import me.smart.order.api.merchant.response.CourseShelfResponse;
import me.smart.order.dao.CourseCategoryMapper;
import me.smart.order.dao.CourseMapper;
import me.smart.order.enums.ErrorCode;
import me.smart.order.exception.BusinessException;
import me.smart.order.exception.SystemException;
import me.smart.order.model.Course;
import me.smart.order.model.CourseCategory;
import me.smart.order.service.MerchantCourseService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static me.smart.order.enums.ResultCode.*;

/**
 * Created by zhangxiong on 16/4/14.
 */
@Service("merchantCourseService")
public class MerchantCourseServiceImpl implements MerchantCourseService {

    private Logger logger = LoggerFactory.getLogger(MerchantCourseServiceImpl.class);
    @Resource
    private CourseMapper courseMapper;

    @Resource
    private CourseCategoryMapper courseCategoryMapper;


    /**
     * 根据merchantId查询菜品分类
     *
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<CategoryListResponse> listCategory(CategoryListRequest request) throws Exception {
        List<CourseCategory> categoryList = courseCategoryMapper.getCourseCategoryListByMerchantId(Long.valueOf(request.getMerchantId()));
        List<CategoryInfo> categoryInfoList = new ArrayList<>();
        categoryList.stream().forEach(item -> {
            CategoryInfo info = new CategoryInfo(item.getId(), item.getCategoryName());
            categoryInfoList.add(info);
        });
        CategoryListResponse response = new CategoryListResponse();
        response.setAccountId(request.getAccountId());
        response.setMerchantId(request.getMerchantId());
        response.setCategoryInfoList(categoryInfoList);
        return Result.createResult(response);
    }

    /**
     * 新增分类
     *
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result<CategoryAddResponse> addCategory(CategoryAddRequest request) throws Exception {
        //先根据名字查找对应的分类
        CourseCategory courseCategory = courseCategoryMapper.getByMerchantIdAndName(Long.valueOf(request.getMerchantId())
                , request.getCategoryName());
        //分类已存在
        if (courseCategory != null) {
            throw new BusinessException(CATEGORY_EXISTED_ERROR);
        }
        CourseCategory category = createCategory(request);

        try {
            courseCategoryMapper.insert(category);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SystemException(ErrorCode.SQL_ERROR);
        }

        CategoryAddResponse response = new CategoryAddResponse(category.getId().toString(), category.getCategoryName());

        return Result.createResult(response);
    }

    private CourseCategory createCategory(CategoryAddRequest request) {
        CourseCategory category = new CourseCategory();
        category.setMerchantId(Long.valueOf(request.getMerchantId()));
        category.setIsDelete(false);
        category.setUpdatedAt(new Date());
        category.setCreatedAt(new Date());
        category.setMerchantCategoryId(Long.valueOf(request.getMerchantCategoryId()));
        category.setSort(request.getSort() == null ? CourseCategory.DEFAULT_SORT : request.getSort());
        return category;
    }

    /**
     * 删除分类
     * 需要将这个分类下面的菜品全部做逻辑删除
     * 此处需要加事务
     *
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Result deleteCategory(CategoryDeleteRequest request) throws Exception {
        CourseCategory category = courseCategoryMapper.getById(Long.valueOf(request.getCategoryId()));
        if (category == null) {
            throw new BusinessException(CATEGORY_NOT_EXIST_ERROR);
        }
        try {
            //将此分类置失效
            courseCategoryMapper.updateIsDelete(Long.valueOf(request.getCategoryId()));
            //将此分类下的菜品全部置失效
            courseMapper.updateIsDeleteByCategory(Long.valueOf(request.getMerchantId()), Long.valueOf(request.getCategoryId()
            ));
        } catch (Exception e) {
            throw new SystemException(ErrorCode.SQL_ERROR);
        }
        return Result.createResult(null);
    }

    /**
     * 更新分类，即更新分类名字
     *
     * @return
     * @throws Exception
     */
    @Override
    public Result updateCategory(CategoryUpdateRequest request) throws Exception {
        CourseCategory category = courseCategoryMapper.getById(Long.valueOf(request.getCategoryId()));

        if (category == null) {
            throw new BusinessException(CATEGORY_NOT_EXIST_ERROR);
        }

        try {
            category.setCategoryName(request.getCategoryName());
            courseCategoryMapper.updateCategoryName(category);
        } catch (Exception e) {
            throw new SystemException(ErrorCode.SQL_ERROR);
        }

        return Result.createResult(null);
    }

    /**
     * 新增菜品
     *
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Result addCourse(CourseAddRequest request) throws Exception {
        //先根据菜名和分类查询DB
        Course courseDB = courseMapper.findByMchIdAndCategoryIdAndName(Long.valueOf(request.getMerchantId()),
                Long.valueOf(request.getCategoryId()), request.getCourseName());
        if (courseDB != null) {
            throw new BusinessException(COURSE_EXISTED_ERROR);
        }

        //查找分类
        CourseCategory category = courseCategoryMapper.getById(Long.valueOf(request.getCategoryId()));
        if (category == null) {
            throw new BusinessException(CATEGORY_NOT_EXIST_ERROR);
        }

        Course course = createCourse(request);

        try {
            int count = courseMapper.save(course);
            if (count == 0) {
                throw new SystemException(ErrorCode.SQL_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SystemException(ErrorCode.SQL_ERROR);
        }
        CourseAddResponse response = new CourseAddResponse(course.getId().toString(),
                course.getName(), course.getCourseImg(), course.getSalePrice().toString());
        return Result.createResult(response);
    }


    private Course createCourse(CourseAddRequest request) {
        Course course = new Course();
        course.setMerchantId(Long.valueOf(request.getMerchantId()));
        course.setCategoryId(Long.valueOf(request.getCategoryId()));
        course.setSort(Course.DEFAULT_SORT);
        course.setUpdatedAt(new Date());
        course.setIsDelete(false);
        course.setCreatedAt(new Date());
        course.setIsShelf(true);
        course.setSalePrice(new BigDecimal(request.getPrice()).multiply(new BigDecimal(100)));
        course.setName(request.getCourseName());
        return course;
    }


    /**
     * 删除菜品
     * 暂时没这个功能，只有菜品的上下架
     *
     * @return
     * @throws Exception
     */
    @Override
    public Result deleteCourse() throws Exception {
        return null;
    }

    /**
     * 更新菜品
     *
     * @return
     * @throws Exception
     */
    @Override
    public Result updateCourse(CourseUpdateRequest request) throws Exception {
        //先找到对应的course
        Course course = courseMapper.findById(Long.valueOf(request.getCourseId()));
        if (course == null) {
            throw new BusinessException(COURSE_NOT_EXIST_ERROR);
        }
        if (StringUtils.isNotBlank(request.getCourseName())) {
            course.setName(request.getCourseName());
        }
        if (StringUtils.isNotBlank(request.getCourseImg())) {
            course.setCourseImg(request.getCourseImg());
        }
        if (StringUtils.isNotBlank(request.getPrice())) {
            course.setSalePrice(new BigDecimal(request.getPrice()).multiply(new BigDecimal(100)));
        }
        try {
            courseMapper.updateCourse(course);
        } catch (Exception e) {
            throw new SystemException(ErrorCode.SQL_ERROR);
        }
        return Result.createResult(null);
    }


    /**
     * 查询某个分类下面的所有的菜品
     *
     * @return
     * @throws Exception
     */
    @Override
    public Result listCourseByCategory(CourseListRequest request) throws Exception {
        List<Course> courseList = courseMapper.getCourseListByMerchantAndCategory(
                Long.valueOf(request.getMerchantId()), Long.valueOf(request.getCategoryId()));
        CategoryCourseInfo categoryCourseInfo = new CategoryCourseInfo();
        categoryCourseInfo.setCategoryId(Long.valueOf(request.getCategoryId()));
        List<CourseInfo> courseInfoList = new ArrayList<>();
        courseList.stream().forEach(item ->
                        courseInfoList.add(createCourseInfo(item))
        );
        categoryCourseInfo.setCourseInfoList(courseInfoList);
        return Result.createResult(categoryCourseInfo);
    }


    private CourseInfo createCourseInfo(Course course) {
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setPrice(course.getSalePrice().divide(new BigDecimal(100)).toString());
        courseInfo.setCourseName(course.getName());
        courseInfo.setCourseId(course.getId());
        courseInfo.setCourseImg(StringUtils.isBlank(course.getCourseImg()) ? "" : course.getCourseImg());
        courseInfo.setIsShelf(course.getIsShelf() ? 1 : 0);
        return courseInfo;
    }

    /**
     * 对菜品的上下架操作
     *
     * @return
     * @throws Exception
     */
    @Override
    public Result shelf(CourseShelfRequest request) throws Exception {
        //先找到对应的course
        Course course = courseMapper.findById(Long.valueOf(request.getCourseId()));
        if (course == null) {
            throw new BusinessException(COURSE_NOT_EXIST_ERROR);
        }
        course.setIsShelf(request.getType() == 0 ? false : true);
        try {
            int count = courseMapper.updateByShelf(course);
            if (count == 0) {
                throw new SystemException(ErrorCode.SQL_ERROR);
            }
        } catch (Exception e) {
            throw new SystemException(ErrorCode.SQL_ERROR);
        }
        CourseShelfResponse response = new CourseShelfResponse(request.getCourseId());
        return Result.createResult(response);
    }
}
