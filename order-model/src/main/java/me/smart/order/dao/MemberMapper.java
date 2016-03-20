package me.smart.order.dao;

import me.smart.order.model.Member;

/**
 * Created by zhangxiong on 15/12/31.
 */
public interface MemberMapper {
    int insert(Member member);

    Member selectById(Long id);
}
