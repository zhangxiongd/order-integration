package me.smart.order.dao;

import me.smart.order.model.MenuOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangxiong on 15/12/31.
 */
public interface MenuOrderMapper {
    int insert(MenuOrder menuOrder);

    List<MenuOrder> queryListByStatus(@Param("pageSize") int pageSize, @Param("pageNo") int pageNo,
                                      @Param("status") int status);

    MenuOrder selectByMenuOrderNO(@Param("merchantId") Long merchantId, @Param("menuOrderNo") String menuOrderNo);

    int updateByStatus(@Param("menuOrderNo") String menuOrderNo, @Param("status") int status);

    MenuOrder selectByMIdAndOrderNoAndMemberId(@Param("memberId") Long memberId, @Param("menuOrderNo") String menuOrderNo,
                                               @Param("merchantId") Long merchantId);
}
