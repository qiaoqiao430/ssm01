package cn.kgc.service;

import cn.kgc.pojo.Provider;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

public interface ProviderService {

    List<Provider> queryList( String proCode,String proName);
    Provider queryListById(Long id);

    int add(Provider pro);
    int modify(Provider pro);
    int delet(Long id);

}
