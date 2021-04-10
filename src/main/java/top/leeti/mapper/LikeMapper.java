package top.leeti.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {
    @Delete("DELETE FROM _like WHERE _id = #{id}")
    void deleteLikeById(@Param("id") String id);
}
