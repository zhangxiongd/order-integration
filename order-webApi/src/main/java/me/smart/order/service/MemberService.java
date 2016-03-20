package me.smart.order.service;

import me.smart.order.model.Member;

import java.util.Map;

/**
 * Created by zhangxiong on 16/3/9.
 */
public interface MemberService {
    Member createMember(Map<String, Object> userInfo) throws Exception;
}
