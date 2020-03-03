package com.hbnu.service.impl;

import com.hbnu.dao.IUserInfo;
import com.hbnu.service.IUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName: IUpdateService <br/>
 * Description: <br/>
 * date: 2020/2/25 20:26<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
//必须写这个否则无法自动注入
@Service
public class UpdateService implements IUpdateService {
    @Autowired
    private IUserInfo users;


    @Override
    public void updatePwdByAccountAndPassword(String pwd, String account) {
        users.updatePwdByAccountAndPassword(pwd, account);
    }

    @Override
    public void deleteUserById(int id) {
        users.deleteUserById(id);
    }
}
