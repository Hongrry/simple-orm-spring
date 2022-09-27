package cn.hruit.orm.spring.test.mapper;


import cn.hruit.orm.spring.annotation.Mapper;
import cn.hruit.orm.spring.test.entity.StudentEntity;
@Mapper
public interface StudentMapper {

    public StudentEntity getStudentById(int id);

    public int addStudent(StudentEntity student);

    public int updateStudentName(StudentEntity student);

    public StudentEntity getStudentByIdWithClassInfo(int id);
}
