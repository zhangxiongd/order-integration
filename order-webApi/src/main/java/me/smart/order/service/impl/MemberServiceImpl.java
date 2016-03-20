package me.smart.order.service.impl;

import me.smart.order.dao.MemberMapper;
import me.smart.order.dao.MemberUnionMapper;
import me.smart.order.enums.MemberUnionEnums;
import me.smart.order.model.Member;
import me.smart.order.model.MemberUnion;
import me.smart.order.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by zhangxiong on 16/3/9.
 */
@Service("memberService")
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
    private Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
    @Resource
    private MemberMapper memberMapper;

    @Resource
    private MemberUnionMapper memberUnionMapper;

    /**
     * 根据微信授权回调后 获取的用户信息创建会员
     *
     * @param userInfo
     * @return
     */
    @Transactional
    public Member createMember(Map<String, Object> userInfo) throws Exception {
        Member member = new Member(userInfo.get("nickname").toString(), Integer.valueOf(userInfo.get("sex").toString()),
                userInfo.get("headimgurl").toString(), userInfo.get("country").toString(), userInfo.get("province").toString(),
                userInfo.get("city").toString());
        memberMapper.insert(member);
        MemberUnion memberUnion = new MemberUnion(member.getId(),
                userInfo.get("openid").toString(), userInfo.get("unionid").toString(), MemberUnionEnums.TEN_SERVICE.getSource());
        memberUnionMapper.insert(memberUnion);
        return member;
    }
}
