package com.hbnu.dao;

import com.hbnu.entity.Roles;
import org.apache.ibatis.annotations.Param;

/**
 * ClassName: IRoles <br/>
 * Description: <br/>
 * date: 2020/3/2 18:45<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
public interface IRoles {
    //    根据userid查询用户角色
     Roles selectByUserid(int userid);

//
    int  insertOneRole(@Param("userid") int userid,@Param("name") String name);

    int delOneRole(int userid);

    int updateOneRole(@Param("name") String name,@Param("userid") int userid);
}
