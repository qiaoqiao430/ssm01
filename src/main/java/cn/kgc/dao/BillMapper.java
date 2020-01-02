package cn.kgc.dao;

import cn.kgc.pojo.Bill;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface BillMapper extends BaseMapper<Bill> {
    List<Bill> queryList();

    int count(Long id);

}
