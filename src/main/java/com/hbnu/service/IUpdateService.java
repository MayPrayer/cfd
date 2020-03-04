package com.hbnu.service;

import com.hbnu.entity.Users;

/**
 * ClassName: IUpdate <br/>
 * Description: <br/>
 * date: 2020/2/25 20:27<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
public interface IUpdateService {

    void updatePwdByAccountAndPassword(String pwd ,String account);

    void deleteUserById(int id);

    int insertOneUser(Users user);
}
