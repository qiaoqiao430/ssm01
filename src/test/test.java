import cn.kgc.pojo.Bill;
import cn.kgc.pojo.Provider;
import cn.kgc.pojo.User;
import cn.kgc.service.BillService;
import cn.kgc.service.ProviderService;
import cn.kgc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class test {
    @Resource
    private BillService billService;

    @Test
    public void test01(){
      //  Bill bill = billService.billListById(1);
      //  System.out.println(bill);
    }

    @Resource
    private ProviderService providerService;
    @Test
    public void test02(){
        Provider provider = new Provider();
        provider.setId(22l);
        provider.setProContact("张");
        providerService.modify(provider);
    }
}
