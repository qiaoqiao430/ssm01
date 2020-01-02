package cn.kgc.controller;

import cn.kgc.dao.ProviderMapper;
import cn.kgc.pojo.Bill;
import cn.kgc.pojo.Provider;
import cn.kgc.service.BillService;
import cn.kgc.service.ProviderService;
import cn.kgc.util.Data;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/bill")
public class BillController {
    @Resource
    private BillService billService;
    @Resource
    private ProviderService providerService;

    @RequestMapping("/billdel")
    @ResponseBody
    public Object del(Long billid){
        Data data = new Data();
        Bill bill = billService.billListById(billid);
        if (bill==null){
            data.setDelResult("notexist");
        }else {
            int i = billService.delete(billid);
            if (i==0){
                data.setDelResult("false");
            }else{
                data.setDelResult("true");
            }

        }
        return "data";
    }

    //订单修改
    @RequestMapping("/billmodify")
    public String modify(HttpSession session,Long billid){
        Bill bill = billService.billListById(billid);
        session.setAttribute("bill",bill);
        return "bill/billmodify";
    }
    @RequestMapping("/dobillmodify")
    public String domodify(Bill bill){
        int i = billService.modify(bill);
        if(i > 0){
            return "redirect:/bill/list";
        }else{
            return "redirect:/bill/billmodify";
        }
    }


    //订单查看
    @RequestMapping("/billview/{id}")
    public String view( HttpSession session,@PathVariable Long id){
        Bill bill = billService.billListById(id);
        Provider provider = providerService.queryListById(bill.getProviderId());
        bill.setProvider(provider);
        session.setAttribute("bill",bill);
        return "bill/billview";
    }

    /*添加订单*/
    @RequestMapping("/add")
    public String add(){
        return "bill/billadd";
    }
    @RequestMapping("/dobilladd")
    public String addList(Bill bill){
        billService.addBillList(bill);
        return "redirect:/bill/list";
    }
    @RequestMapping("/billselect")
    @ResponseBody
    public Object allProvider(){
        List<Provider> list = providerService.queryList(null,null);
        return list;
    }

    //分页查询
    @RequestMapping("/list")
    public String queryList(HttpSession session,Integer pageIndex,
                            String productName,
                            Integer providerId,
                            Integer isPayment ){
        if (pageIndex == null){
            pageIndex=1;
        }
        Page<Bill> page = billService.queryList(pageIndex,5,productName,providerId,isPayment);
        List<Provider> providerList = providerService.queryList(null,null);
        long pages = page.getPages();
        session.setAttribute("pages",pages);

        session.setAttribute("billList",page.getRecords());
        session.setAttribute("providerList",providerList);
        session.setAttribute("page",page);
        session.setAttribute("productName",productName);
        session.setAttribute("providerId",providerId);
        session.setAttribute("isPayment",isPayment);
        return "bill/billlist";
    }
}
