package cn.hruit.orm.spring;

import cn.hruit.orm.session.SqlSession;
import cn.hruit.orm.session.SqlSessionFactory;

/**
 * @author HONGRRY
 * @description
 * @date 2022/09/27 19:38
 **/
public class SqlSessionHolder {
    private final static ThreadLocal<SqlSession> sessionHolder = new ThreadLocal<>();
    private static SqlSessionFactory sqlSessionFactory = null;

    public static void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        SqlSessionHolder.sqlSessionFactory = sqlSessionFactory;
    }

    public static SqlSession getSession() {
        SqlSession sqlSession = sessionHolder.get();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            sessionHolder.set(sqlSession);
        }
        return sqlSession;
    }

    public static void remove() {
        sessionHolder.remove();
    }
}
