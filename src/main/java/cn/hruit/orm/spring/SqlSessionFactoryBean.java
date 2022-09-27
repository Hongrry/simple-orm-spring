package cn.hruit.orm.spring;

import cn.hruit.orm.io.Resources;
import cn.hruit.orm.session.SqlSessionFactory;
import cn.hruit.orm.session.SqlSessionFactoryBuilder;
import cn.hruit.orm.spring.mapper.MapperScanner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.io.IOException;

/**
 * @author HONGRRY
 * @description
 * @date 2022/09/27 19:46
 **/
public class SqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>, BeanFactoryAware {
    private SqlSessionFactory factory;
    private String configLocation;
    private String scanPackage;
    private DefaultListableBeanFactory beanFactory;
    private MapperScanner mapperScanner = new MapperScanner();

    @Override

    public SqlSessionFactory getObject() throws Exception {
        if (factory == null) {
            afterPropertySet();
        }
        return factory;
    }

    private void afterPropertySet() throws IOException {
        assert (configLocation != null);
        mapperScanner.doScan();
        buildFactory();
    }

    private void buildFactory() throws IOException {
        factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(configLocation));
        SqlSessionHolder.setSqlSessionFactory(factory);
    }

    @Override
    public Class<?> getObjectType() {
        return SqlSessionFactory.class;
    }

    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }

    public void setScanPackage(String scanPackage) {
        this.scanPackage = scanPackage;
        mapperScanner.setBasePackage(scanPackage);

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
        mapperScanner.setBeanFactory(beanFactory);
    }
}
