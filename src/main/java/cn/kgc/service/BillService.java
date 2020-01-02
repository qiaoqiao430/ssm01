package cn.kgc.service;

import cn.kgc.pojo.Bill;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

public interface BillService {
    Page<Bill> queryList(Integer currentPageNum, Integer pageSize,
                         String productName,Integer providerId,Integer isPayment);
    //添加订单
    int addBillList(Bill bill);
    //根据id查询订单
    Bill billListById(Long id);
    //修改
    int modify(Bill bill);
    //删除
    int delete(Long id);
    int count(Long id);
}
