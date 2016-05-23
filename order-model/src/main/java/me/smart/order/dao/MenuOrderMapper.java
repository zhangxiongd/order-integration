package me.smart.order.dao;

import me.smart.order.model.MenuOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangxiong on 15/12/31.
 */
public interface MenuOrderMapper {
    int insert(MenuOrder menuOrder);

    List<MenuOrder> queryListByStatus(@Param("merchantId") Long merchantId, @Param("pageSize") int pageSize, @Param("startRow") int startRow,
                                      @Param("orderStatus") int orderStatus);

    MenuOrder selectByMenuOrderNo(@Param("merchantId") Long merchantId, @Param("menuOrderNo") String menuOrderNo);

    int updateByStatus(@Param("merchantId") Long merchantId, @Param("menuOrderNo") String menuOrderNo, @Param("orderStatus") int orderStatus);

    MenuOrder selectByMIdAndOrderNoAndMemberId(@Param("memberId") Long memberId, @Param("menuOrderNo") String menuOrderNo,
                                               @Param("merchantId") Long merchantId);
}
