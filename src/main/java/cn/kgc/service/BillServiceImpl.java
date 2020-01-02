package cn.kgc.service;

import cn.kgc.dao.BillMapper;
import cn.kgc.dao.ProviderMapper;
import cn.kgc.pojo.Bill;
import cn.kgc.pojo.Provider;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Resource
    private BillMapper billMapper;
    @Resource
    private ProviderMapper providerMapper;

    @Override
    public int count(Long id) {

        return billMapper.count(id);
    }


    @Override
    public Bill billListById(Long id) {
        Bill bill = billMapper.selectById(id);
        return bill;
    }

    @Override
    public int modify(Bill bill) {
        return billMapper.updateById(bill);
    }

    @Override
    public int delete(Long id) {
        return billMapper.deleteById(id);
    }



    @Override
    public int addBillList(Bill bill) {
        return billMapper.insert(bill);
    }

    @Override
    public Page<Bill> queryList(Integer currentPageNum, Integer pageSize,
                                String productName,Integer providerId,Integer isPayment) {
        //分页的拼接
        Page<Bill> page = new Page<>(currentPageNum, pageSize);
        //查询的条件
        EntityWrapper<Bill> wrapper = new EntityWrapper<>();
        if (productName != null && !productName.equals(" ")){
            wrapper.like("productName",productName);
        }
        if(providerId != null && providerId != 0){
           wrapper.eq("providerId",providerId);
        }
        if (isPayment !=null && isPayment != 0){
            wrapper.eq("isPayment",isPayment);
        }
        //订单的分页
        List<Bill> billList = billMapper.selectPage(page, wrapper);
        //供应商的全查询
        List<Provider> providerList = providerMapper.selectList(null);
        //根据bill的外键和provider的主键相等  将供应商对象放入订单对象的provider属性
        for (Bill bill:billList){
            for (Provider p : providerList){
                if (p.getId().equals(bill.getProviderId())){
                    bill.setProvider(p);
                    break;
                }
            }
        }
        //page对象保存 订单集合 bill和provider
        page.setRecords(billList);
        return page;
    }


}
