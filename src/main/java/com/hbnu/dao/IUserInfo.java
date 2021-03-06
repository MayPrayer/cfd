package com.hbnu.dao;

import com.hbnu.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * ClassName: IUserInfo <br/>
 * Description: <br/>
 * date: 2020/2/21 11:59<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Repository
public interface IUserInfo {
    //查询所有用户
    List<Users> findAll();

    //   根据账户名和密码进行查询 相同类型参数需要加注解
    Users selectByAccountAndPassword(@Param("account") String account, @Param("pwd") String pwd);

    //根据账户名更新密码
    void updatePwdByAccountAndPassword(@Param("pwd") String account, @Param("account") String pwd);

    //   根据id删除用户
    void deleteUserById(int id);

    //    根据账户名模糊查询
    List<Users> selectLikeUser(@Param("account") String account);

    //   根据账户名查询用户
    List<Users> selectByAccount(@Param("account") String account);


    //   添加用户
    int insertOneUser(Users user);

    //    更新用户  account=#{account},pwd=#{pwd},name=#{name},phone=#{phone},nickname=#{nickname},birthday=#{birthday},idcard=#{idcard},avatar=#{avatar} where id=#{id}
    int updateOneUser(@Param("account") String account, @Param("pwd") String pwd, @Param("name") String name, @Param("birthday") Date birthday, @Param("idcard") String idcard, @Param("avatar") String avatar, @Param("phone") String phone, @Param("nickname") String nickname, @Param("id") int id);

    //根据id查询用户信息
    List<Users> selectInfoByUserid(int id);

    //查询自增id的值
    int selectidValue();
}
