package cn.hruit.orm.spring;

import cn.hruit.orm.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author HONGRRY
 * @description
 * @date 2022/09/27 19:34
 **/
public class SqlSessionInterceptor implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SqlSession session = SqlSessionHolder.getSession();
        return method.invoke(session, args);
    }
}
