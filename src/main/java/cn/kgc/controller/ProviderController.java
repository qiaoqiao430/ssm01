package cn.kgc.controller;

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
@RequestMapping("/pro")
public class ProviderController {

    @Resource
    private ProviderService ps;
    @Resource
    private BillService billService;

    @RequestMapping("/delprovider")
    @ResponseBody
    public Object del(Long proid){
        Data data = new Data();
        Provider provider = ps.queryListById(proid);
        int count = billService.count(proid);
        int i = ps.delet(proid);

        if (count>0){
            data.setDelResult(""+count);
        }else {
            if (i == 0) {
                data.setDelResult("false");
            } else {
                data.setDelResult("true");
            }
        }
        return data;
    }

    /*修改*/
    @RequestMapping("/providermodify/{proid}")
    public String modify(@PathVariable Long proid,HttpSession session){
        Provider provider = ps.queryListById(proid);
        session.setAttribute("provider",provider);
        return "pro/providermodify";
    }
    @RequestMapping("/providermodifysave")
    public String doModify(Provider pro){
        System.out.println(pro.getId());
        int i = ps.modify(pro);
        if (i > 0){
            return "redirect:/pro/provider";
        }else{
            return "pro/providermodify";
        }
    }

    //查看
    @RequestMapping("/proview/{proid}")
    public String select(@PathVariable Long proid, HttpSession session){
        Provider provider = ps.queryListById(proid);
        session.setAttribute("provider",provider);
        return "pro/providerview";
    }

    /*添加供应商*/
    @RequestMapping("/provideradd")
    public String add(){
        return "pro/provideradd";
    }
    @RequestMapping("/provideraddsave")
    public String doAdd(Provider pro){
        ps.add(pro);
        return "redirect:/pro/provider";
    }

    //查询
    @RequestMapping("/provider")
    public String queryList(HttpSession session,String queryProCode,String queryProName){
        List<Provider> list = ps.queryList(queryProCode,queryProName);
        session.setAttribute("proList",list);
        session.setAttribute("queryProCode",queryProCode);
        session.setAttribute("queryProName",queryProName);
        return "pro/providerlist";
    }


    //bill订单查看的下拉框
    @RequestMapping("/billselect")
    @ResponseBody
    public Object queryOne(){
        List<Provider> list = ps.queryList(null,null);
        return list;
    }
}
