package cn.kgc.service;

import cn.kgc.pojo.User;

public interface UserService {

    User login(String usercode,String password);

    //修改密码
    boolean modifyPwd(Long id,String pwd);
}
