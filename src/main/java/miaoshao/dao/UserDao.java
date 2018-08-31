package miaoshao.dao;

import miaoshao.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    @Select("select * from test where id = #{id}")
    public User getByID(@Param("id")int id);

    @Insert("insert into user(name)values(#{name})")
    public int insert(User user);

}
