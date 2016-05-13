package com.ioc;

import me.smart.order.service.MenuService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by zhangxiong on 16/4/2.
 */
public class TestIoc {

    public static void main(String[] args) {
        // create  xml resource
        ClassPathResource resource = new ClassPathResource("spring/spring-context.xml");
        // create BeanFactory DefaultListableBeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //create XmlBeanDefinitionReader
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        //load beanDefinition from resource
        reader.loadBeanDefinitions(resource);

        MenuService menuService = (MenuService) beanFactory.getBean("menuServiceProxy");
        try {
            System.out.println(menuService.getMerchantMenuList(1l));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
