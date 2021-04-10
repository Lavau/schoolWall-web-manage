package top.leeti.mapper;

import org.apache.ibatis.annotations.*;
import top.leeti.entity.College;

import java.util.List;

@Mapper
public interface CollegeMapper {
    @Select("SELECT _id AS id, _college_id as collegeId, _college_name as collegeName, _is_available as Available " +
            "FROM _college ORDER BY _college_id ASC")
    List<College> findColleges();

    @Select("SELECT _id AS id, _college_id as collegeId, _college_name as collegeName, _is_available as Available " +
            "FROM _college WHERE _id = #{id}")
    College findCollegeById(@Param("id")Integer id);

    @Insert("INSERT _college (_college_id, _college_name) VALUES (#{c.collegeId}, #{c.collegeName})")
    void insertCollege(@Param("c")College college);

    @Update("UPDATE _college SET _college_id = #{c.collegeId} AND _college_name = #{c.collegeName} " +
            "AND _is_available = #{c.Available} WHERE _id = #{c.id}")
    void updateCollege(@Param("c")College college);
}
