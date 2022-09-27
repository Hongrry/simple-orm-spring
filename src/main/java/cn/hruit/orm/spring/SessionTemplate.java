package cn.hruit.orm.spring;

import cn.hruit.orm.session.Configuration;
import cn.hruit.orm.session.SqlSession;
import cn.hruit.orm.session.SqlSessionFactory;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author HONGRRY
 * @description
 * @date 2022/09/27 17:05
 **/
public class SessionTemplate implements SqlSession {
     private final SqlSessionFactory sqlSessionFactory;

    private final SqlSession sqlSessionProxy;

    public SessionTemplate(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.sqlSessionProxy = (SqlSession) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{SqlSession.class},
                new SqlSessionInterceptor());
    }

    @Override
    public <T> T selectOne(String s) {
        return sqlSessionProxy.selectOne(s);
    }

    @Override
    public <T> T selectOne(String s, Object o) {
        return sqlSessionProxy.selectOne(s, o);
    }

    @Override
    public <E> List<E> selectList(String s, Object o) {
        return sqlSessionProxy.selectList(s, o);
    }

    @Override
    public int insert(String s, Object o) {
        return sqlSessionProxy.insert(s, o);
    }

    @Override
    public int update(String s, Object o) {
        return sqlSessionProxy.update(s, o);
    }

    @Override
    public int delete(String s, Object o) {
        return sqlSessionProxy.delete(s, o);
    }

    @Override
    public Configuration getConfiguration() {
        return sqlSessionFactory.getConfiguration();
    }

    @Override
    public <T> T getMapper(Class<T> aClass) {
        return sqlSessionFactory.getConfiguration().getMapper(aClass, this);
    }

    @Override
    public void commit() {
        sqlSessionProxy.commit();

    }

    @Override
    public void commit(boolean b) {
        sqlSessionProxy.commit(b);

    }

    @Override
    public void close() {
        sqlSessionProxy.close();
        SqlSessionHolder.remove();
    }
}

