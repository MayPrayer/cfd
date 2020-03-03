package com.hbnu.dao;

import com.hbnu.entity.Roles;

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
    public Roles selectByUserid(int userid);

}
