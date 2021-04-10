package top.leeti.mapper;

import org.apache.ibatis.annotations.*;
import top.leeti.entity.Msg;

import java.util.List;

@Mapper
public interface MsgMapper {
    @Insert("INSERT _msg (_id, _receiver_id, _content, _gmt_create) VALUES " +
            "(#{m.id}, #{m.receiverId}, #{m.content}, #{m.gmtCreate})")
    void insertMsg(@Param("m") Msg msg);
}
