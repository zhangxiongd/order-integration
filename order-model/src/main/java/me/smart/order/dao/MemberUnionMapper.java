package me.smart.order.dao;

import me.smart.order.model.MemberUnion;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zhangxiong on 16/3/9.
 */
public interface MemberUnionMapper {
    int insert(MemberUnion memberUnion);

    MemberUnion selectByMemberIdAndSource(@Param("memberId") Long memberId, @Param("source") int source);
}
