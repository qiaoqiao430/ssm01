package cn.kgc.dao;

import cn.kgc.pojo.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {
    //修改密码
    int modifyPwd(@Param("id") Long id, @Param("pwd") String pwd);
}
