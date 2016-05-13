package me.smart.order.service.impl;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import me.smart.order.api.Result;
import me.smart.order.api.merchant.request.MerchantCourseCategoryAddRequest;
import me.smart.order.api.merchant.request.MerchantCourseCategoryListRequest;
import me.smart.order.api.merchant.request.MerchantCourseCategoryUpdateRequest;
import me.smart.order.api.merchant.response.MerchantCourseCategoryListResponse;
import me.smart.order.dao.MerchantCourseCategoryMapper;
import me.smart.order.enums.ErrorCode;
import me.smart.order.enums.ResultCode;
import me.smart.order.exception.BusinessException;
import me.smart.order.exception.SystemException;
import me.smart.order.model.MerchantCourseCategory;
import me.smart.order.service.MerchantPrintCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangxiong on 16/4/18.
 */
@Service("merchantPrintCategoryService")
public class MerchantPrintCategoryServiceImpl implements MerchantPrintCategoryService {

    private Logger logger = LoggerFactory.getLogger(MerchantPrintCategoryServiceImpl.class);

    @Resource
    private MerchantCourseCategoryMapper merchantCourseCategoryMapper;

    @Override
    public Result addCategory(MerchantCourseCategoryAddRequest request) throws Exception {
        MerchantCourseCategory category = merchantCourseCategoryMapper.findByMchIdAndName(
                Long.valueOf(request.getMerchantId()), request.getCategoryName());
        if (category != null) {
            throw new BusinessException(ResultCode.MERCHANT_COURSE_CATEGORY_EXISTED_ERROR);
        }
        try {
            int count = merchantCourseCategoryMapper.insert(createCategory(request));
            if (count == 0) {
                logger.error("sql insert error");
                throw new SystemException(ErrorCode.SQL_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SystemException(ErrorCode.SQL_ERROR);
        }
        return Result.createResult(null);
    }

    private MerchantCourseCategory createCategory(MerchantCourseCategoryAddRequest request) {
        MerchantCourseCategory category = new MerchantCourseCategory();
        category.setCategoryName(request.getCategoryName());
        category.setCreatedAt(new Date());
        category.setIsDelete(false);
        category.setMerchantId(Long.valueOf(request.getMerchantId()));
        category.setSort(100);
        category.setUpdatedAt(new Date());
        return category;

    }

    @Override
    public Result deleteCategory() throws Exception {
        return null;
    }

    @Override
    public Result updateCategory(MerchantCourseCategoryUpdateRequest request) throws Exception {
        MerchantCourseCategory category = merchantCourseCategoryMapper.findById(Long.valueOf(request.getCategoryId()));
        if (category == null) {
            throw new BusinessException(ResultCode.MERCHANT_COURSE_CATEGORY_NOT_EXIST_ERROR);
        }
        MerchantCourseCategory updateCategory = new MerchantCourseCategory();
        updateCategory.setCategoryName(request.getCategoryName());
        updateCategory.setUpdatedAt(new Date());
        updateCategory.setId(category.getId());
        try {
            int count = merchantCourseCategoryMapper.update(updateCategory);
            if (count == 0) {
                logger.error("sql insert error");
                throw new SystemException(ErrorCode.SQL_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SystemException(ErrorCode.SQL_ERROR);
        }
        return Result.createResult(null);
    }

    @Override
    public Result listCategory(MerchantCourseCategoryListRequest request) throws Exception {
        List<MerchantCourseCategory> list = merchantCourseCategoryMapper.getListByMerchantId(Long.valueOf(request.getMerchantId()));
        List<MerchantCourseCategoryListResponse> result = new ArrayList<>();
        list.stream().forEach(item -> {
            MerchantCourseCategoryListResponse temp = new MerchantCourseCategoryListResponse();
            temp.setCategoryId(item.getId().toString());
            temp.setCategoryName(item.getCategoryName());
            result.add(temp);
        });
        return Result.createResult(result);
    }


    public static void main(String[] args) {
        try {
            String apiKey = "8IpmuTXlIxkbaY58SbpofG53";
            String secretKey = "FzFbEl2MUwSvM3Lm92kvhManUvOITKGf";
            PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

            BaiduPushClient pushClient = new BaiduPushClient(pair,
                    BaiduPushConstants.CHANNEL_REST_URL);

            pushClient.setChannelLogHandler(new YunLogHandler() {
                @Override
                public void onHandle(YunLogEvent event) {
                    System.out.println(event.getMessage());
                }
            });


            // 4. 设置请求参数，创建请求实例
            PushMsgToSingleDeviceRequest requestPush = new PushMsgToSingleDeviceRequest().
                    addChannelId("234234").
                    addMsgExpires(new Integer(3600)).   //设置消息的有效时间,单位秒,默认3600*5.
                    addMessageType(1).addMessage("{\"title\":\"TEST\",\"description\":\"Hello Baidu push!\"}").
                    addDeviceType(3);      //设置设备类型，deviceType => 1 for web, 2 for pc,
            //3 for android, 4 for ios, 5 for wp.
            // 5. 执行Http请求
            PushMsgToSingleDeviceResponse response = pushClient.
                    pushMsgToSingleDevice(requestPush);
            // 6. Http请求返回值解析
            System.out.println("msgId: " + response.getMsgId()
                    + ",sendTime: " + response.getSendTime());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
