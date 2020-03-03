package com.hbnu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbnu.dao.IRoles;
import com.hbnu.dao.IUserInfo;
import com.hbnu.entity.Roles;
import com.hbnu.entity.Users;
import com.hbnu.service.IQryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: QryService <br/>
 * Description: <br/>
 * date: 2020/2/21 11:58<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Service
public class QryService implements IQryService {
    @Autowired
    private IUserInfo users;
    @Autowired
    private IRoles roles;


    @Override
    public PageInfo findAll(int pageNum, int pageSize) {
//        开始分页
        PageHelper.startPage(pageNum,pageSize);
        List<Users> list = users.findAll();
        PageInfo pageInfo=new PageInfo(list);
        return pageInfo;
    }

    @Override
    public PageInfo selectLikeUser(int pageNum, int pageSize, String account) {
        PageHelper.startPage(pageNum,pageSize);
        List<Users> list = users.selectLikeUser(account);
        PageInfo pageInfo=new PageInfo(list);
        return pageInfo;
    }


    @Override
    public Users selectByAccountAndPassword(String account, String pwd) {
        return users.selectByAccountAndPassword(account, pwd);
    }

    @Override
    public Roles selectByUserid(int userid) {
       return  roles.selectByUserid(userid);
    }


}
