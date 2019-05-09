package com.youxiu326.mapper;

import com.youxiu326.model.User;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
//这里可以使用@Mapper注解，但是每个mapper都加注解比较麻烦，所以统一配置@MapperScan在扫描路径在application类中
//@Mapper //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface UserMapper {

    @Select("SELECT id, user_id userId, user_name userName FROM user_tab WHERE user_id = #{userId}")
    @ResultType(User.class)
    User selectUser(String userId);

}
