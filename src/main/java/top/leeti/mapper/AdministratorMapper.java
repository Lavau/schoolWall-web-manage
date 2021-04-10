package top.leeti.mapper;

import org.apache.ibatis.annotations.*;
import top.leeti.entity.Administrator;

import java.util.List;

@Mapper
public interface AdministratorMapper {
    @Select("SELECT a._id AS id, _username AS username, _en_password AS enPassword, a._role_id AS roleId, " +
            "a._gmt_create AS gmtCreate, a._is_available AS Available, _role_name AS roleName FROM _administrator " +
            "AS a LEFT JOIN _role ON a._role_id = _role._id WHERE _username = #{username}")
    Administrator findAdministratorByUsername(@Param("username") String username);

    @Select("SELECT a._id AS id, _username AS username, _en_password AS enPassword, a._role_id AS roleId, " +
            "a._gmt_create AS gmtCreate, a._is_available AS Available, _role_name AS roleName FROM _administrator " +
            "AS a LEFT JOIN _role ON a._role_id = _role._id WHERE a._id = #{id}")
    Administrator findAdministratorById(@Param("id") String id);

    @Select("SELECT a._id AS id, _username AS username, _en_password AS enPassword, a._role_id AS roleId, " +
            "a._gmt_create AS gmtCreate, a._is_available AS Available, _role_name AS roleName FROM _administrator " +
            "AS a LEFT JOIN _role ON a._role_id = _role._id ORDER BY a._gmt_create ASC")
    List<Administrator> findAdministrators();

    @Update("UPDATE _administrator SET _username = #{a.username}, _en_password = #{a.enPassword}, _role_id = " +
            "#{a.roleId}, _is_available = #{a.Available} WHERE _id=#{a.id}")
    void updateAdministrator(@Param("a") Administrator administrator);

    @Insert("INSERT _administrator (_id, _username, _en_password, _role_id, _gmt_create) VALUES " +
            "(#{a.id}, #{a.username}, #{a.enPassword}, #{a.roleId}, #{a.gmtCreate})")
    void insertAdministrator(@Param("a") Administrator administrator);

    @Delete("DELETE FROM _administrator WHERE _id = #{id}")
    void deleteAdministratorById(@Param("id")String id);
}
