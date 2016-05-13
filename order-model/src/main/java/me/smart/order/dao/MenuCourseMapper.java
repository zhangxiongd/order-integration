package me.smart.order.dao;

import me.smart.order.model.MenuCourse;

import java.util.List;

/**
 * Created by zhangxiong on 16/2/14.
 */
public interface MenuCourseMapper {
    int insert(MenuCourse menuCourse);

    List<MenuCourse> getListByMenuOrderNo(String menuOrderNo);
}
