package com.hbnu.service.impl;

import com.hbnu.dao.IGoods;
import com.hbnu.dao.IInforms;
import com.hbnu.dao.IUserInfo;
import com.hbnu.entity.Users;
import com.hbnu.service.IUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    @Autowired
    private IGoods goods;
    @Autowired
    private IInforms informs;


    @Override
    public void updatePwdByAccountAndPassword(String pwd, String account) {
        users.updatePwdByAccountAndPassword(pwd, account);
    }

    @Override
    public void deleteUserById(int id) {
        users.deleteUserById(id);
    }

    @Override
    public int insertOneUser(Users user) {
        int code = users.insertOneUser(user);
        System.out.println("插入数据返回值为"+code);
        return code;
    }

    @Override
    public int updateOneUser(String name, String phone, String nickname,int id) {
        int code=users.updateOneUser(name,phone,nickname,id);
        System.out.println("更新数据返回值为"+code);
        return code;
    }

    @Override
    public int delGoodById(int id) {
        int code = goods.delGoodById(id);
        System.out.println("删除数据返回值为"+code);
        return code;
    }

    @Override
    public int insertOneInform(String title, String content, int shopid ,int important) {
        return informs.insertOneInform(title,content,shopid,important) ;
    }

    @Override
    public int delOneInformById(int id) {
        return informs.delOneInformById(id);
    }
}
