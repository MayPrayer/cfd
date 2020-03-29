package com.hbnu.dao;

import com.hbnu.entity.OrderDetail;

import java.util.List;

/**
 * ClassName: IOrderDetail <br/>
 * Description: <br/>
 * date: 2020/3/27 10:36<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
public interface IOrderDetail {
/*
* 订单详情
* */
   List<OrderDetail>  selectOrderGood(long id);
   List<OrderDetail>  selectOrderDetailByOrId(long id);

}
