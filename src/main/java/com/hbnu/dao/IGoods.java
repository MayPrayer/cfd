package com.hbnu.dao;

import com.hbnu.entity.Goods;
import org.apache.ibatis.annotations.Param;

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
    /*
    * 商品 查询所有
    * 删除商品
    * */
    List<Goods> selectCurUserGoods(int id);
    int delGoodById(int id);


    /*
    * 商品新增，编辑
    * */
    int addGood(@Param("shopid") int shopid, @Param("name")String name, @Param("describes")String describes, @Param("img")String img, @Param("price")double price, @Param("discountprice")double discountprice,
                @Param("sales")int sales, @Param("inventory")int inventory, @Param("goodsstatus")String goodsstatus, @Param("goodstype")String goodstype);

    int editGood(@Param("name")String name, @Param("describes")String describes, @Param("img")String img, @Param("price")double price, @Param("discountprice")double discountprice,
                 @Param("sales")int sales, @Param("inventory")int inventory, @Param("goodsstatus")String goodsstatus, @Param("goodstype")String goodstype,@Param("id")int id);


}
