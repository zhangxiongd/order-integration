package me.smart.order.dao;

import me.smart.order.model.MenuCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangxiong on 16/2/14.
 */
public interface MenuCourseMapper {
    int insert(MenuCourse menuCourse);

    List<MenuCourse> getListByMenuOrderNo(@Param("merchantId") Long merchantId, @Param("memberId") Long memberId,  @Param("menuOrderNo") String menuOrderNo);
}
