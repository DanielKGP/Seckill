package miaoshao.dao;

import miaoshao.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    @Select("select * from user where id = #{id}")
    public User getByID(@Param("id")int id);

    @Insert("insert into user(id, name)values(#{id}, #{name})")
    public int insert(User user);

}
