package cn.hruit.orm.spring.test.mapper;


import cn.hruit.orm.spring.annotation.Mapper;
import cn.hruit.orm.spring.test.entity.Class;


@Mapper
public interface ClassMapper {

    public int updateClassName(Class c);
}
