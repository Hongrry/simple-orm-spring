package cn.hruit.orm.spring.mapper;

import cn.hruit.orm.spring.annotation.Mapper;
import cn.hutool.core.util.ClassUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.util.Set;

import static cn.hutool.core.util.StrUtil.toCamelCase;

/**
 * @author HONGRRY
 * @description
 * @date 2022/09/27 19:47
 **/
public class MapperScanner {
    private DefaultListableBeanFactory beanFactory;
    private String basePackage;

    public void doScan() {
        Set<Class<?>> classes = ClassUtil.scanPackage(basePackage);
        for (Class<?> aClass : classes) {
            Mapper annotation = aClass.getAnnotation(Mapper.class);
            if (annotation != null) {
                BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
                        .genericBeanDefinition(MapperFactoryBean.class);
                // 这里就是Bean依赖的填充,Bean的引用变量名属性，Bean的类型，这个Bean必须是在ioc中的
                beanDefinitionBuilder.addPropertyReference("sessionTemplate", "sessionTemplate");

                // 这里就是Bean属性的填充
                beanDefinitionBuilder.addPropertyValue("interfaceClass", aClass);
                String name = aClass.getName();
                String beanName = toCamelCase(name);
                // 正式注入Bean
                beanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getRawBeanDefinition());

            }
        }

    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}
