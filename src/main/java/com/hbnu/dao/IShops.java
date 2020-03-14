package com.hbnu.dao;

/**
 * ClassName: IShops <br/>
 * Description: <br/>
 * date: 2020/3/14 14:35<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
public interface IShops {
    /*
     * 根据用户id查询商铺id
     * */
    int selectIdByUserId(int id);
}
