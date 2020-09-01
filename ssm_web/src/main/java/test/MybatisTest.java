package test;

import com.demo.dao.IProductDao;
import com.demo.domain.Product;

import com.demo.service.IProductService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MybatisTest {

    @Test
    public void Test1(){
        //获取ioc容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取dao
        IProductDao dao = ac.getBean("productDao",IProductDao.class);
        List<Product> all = dao.findAll();
        for (Product product : all) {
            System.out.println(product);
        }
    }

   /* @Test
    public void tet2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        IProductService service = ac.getBean("productService", IProductService.class);
        List<Product> all = service.findAll();
        for (Product product : all) {
            System.out.println(product);
        }
    }*/
}
