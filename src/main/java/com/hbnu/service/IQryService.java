package com.hbnu.service;

import com.github.pagehelper.PageInfo;
import com.hbnu.entity.Roles;
import com.hbnu.entity.Users;

/**
 * ClassName: IQryService <br/>
 * Description: <br/>
 * date: 2020/2/21 11:55<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
public interface IQryService {

     PageInfo findAll(int pageNum,int pageSize);
     Users selectByAccountAndPassword(String account, String pwd);
     Roles selectByUserid(int userid);

}
