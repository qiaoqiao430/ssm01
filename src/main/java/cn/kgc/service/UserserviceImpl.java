package cn.kgc.service;

import cn.kgc.dao.UserMapper;
import cn.kgc.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserserviceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String usercode, String password) {
        User u = new User();
        u.setUserCode(usercode);
        User user = userMapper.selectOne(u);
        if (user != null && user.getUserPassword().equals(password)){
            return user;
        }
        return null;
    }
    //修改密码
    @Override
    public boolean modifyPwd(Long id, String pwd) {
        int i = userMapper.modifyPwd(id, pwd);
        if (i==1){
            return true;
        }else {
            return false;
        }
    }


}
