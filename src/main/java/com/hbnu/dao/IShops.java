package com.hbnu.dao;

import com.hbnu.entity.Shops;
import org.apache.ibatis.annotations.Param;

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

    Shops selectShopinfoByUserid(int userid);

    int addOneShop(@Param("userid")int userid, @Param("name") String name,@Param("logo")String logo,@Param("position")String position, @Param("managername")String managername, @Param("managerphone")String managerphone);


    int delOneShop(int userid);

    int updateOneShop(@Param("name")String name,@Param("logo")String logo,@Param("position")String position,@Param("managername")String managername,@Param("managerphone")String managerphone ,@Param("userid")int userid);



}
