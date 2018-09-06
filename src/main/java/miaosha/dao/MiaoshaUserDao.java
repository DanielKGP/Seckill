package miaosha.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import miaosha.domain.MiaoshaUser;

@Mapper
public interface MiaoshaUserDao {

    @Select("select * from miaosha_user where id=#{id}")
    public MiaoshaUser getById(@Param("id") long id);

}
