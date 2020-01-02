package cn.kgc.service;

import cn.kgc.dao.ProviderMapper;
import cn.kgc.pojo.Provider;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class ProviderServiceImpl implements ProviderService {

    @Resource
    private ProviderMapper pm;

    @Override
    public int add(Provider pro) {
        return pm.insert(pro);
    }

    @Override
    public int modify(Provider pro) {
        return pm.updateById(pro);
    }

    @Override
    public int delet(Long id) {
        return pm.deleteById(id);
    }



    @Override
    public List<Provider> queryList( String proCode,String proName) {
        EntityWrapper<Provider> wrapper = new EntityWrapper<>();
        if (proCode != null && !proCode.equals("")){
            wrapper.eq("proCode",proCode);
        }
        if (proName != null && !proName.equals("")){
            wrapper.like("proName",proName);
        }
        return pm.selectList(wrapper);

    }

    @Override
    public Provider queryListById(Long id) {
        Provider provider = pm.selectById(id);
        return provider;
    }


}
