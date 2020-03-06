package com.hbnu.dao;

import com.hbnu.entity.Goods;

import java.util.List;

/**
 * ClassName: IGoods <br/>
 * Description: <br/>
 * date: 2020/3/5 15:43<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
public interface IGoods {
    List<Goods> selectCurUserGoods(int id);
    int delGoodById(int id);
}
