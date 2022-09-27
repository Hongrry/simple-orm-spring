package cn.hruit.orm.spring.mapper;


import cn.hruit.orm.spring.SessionTemplate;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author HONGRRY
 * @description
 * @date 2022/09/27 16:42
 **/
public class MapperFactoryBean<T> implements FactoryBean<T> {
    private Class<?> interfaceClass;
    private SessionTemplate sessionTemplate;

    public MapperFactoryBean() {
    }

    public MapperFactoryBean(Class<?> interfaceClass, SessionTemplate sessionTemplate) {
        this.interfaceClass = interfaceClass;
        this.sessionTemplate = sessionTemplate;
    }

    @Override
    public T getObject() throws Exception {
        return (T) sessionTemplate.getMapper(interfaceClass);
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceClass;
    }

    public void setInterfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public void setSessionTemplate(SessionTemplate sessionTemplate) {
        this.sessionTemplate = sessionTemplate;
    }
}
