package top.leeti.mapper;

import org.apache.ibatis.annotations.*;
import top.leeti.entity.Report;
import top.leeti.entity.ReportType;

@Mapper
public interface ReportMapper {

    @Select("SELECT _id AS id, _published_info_id AS publishedInfoId, _reporter_id AS reporterId, _report_type_id " +
            "AS reportTypeId, _report_reason AS reportReason, _gmt_create AS gmtCreate FROM _report " +
            "WHERE _is_audit = 0 AND _id = #{id}")
    Report getReportById(@Param("id") String id);

    @Select("SELECT _id AS id, _name AS name, _gmt_create AS gmtCreate FROM _report_type WHERE _id = #{id}")
    ReportType getReportTypeById(@Param("id") Integer id);

    @Update("UPDATE _report SET _is_audit = #{r.Audit} WHERE _id = #{r.id}")
    void updateReport(@Param("r") Report report);

    @Delete("DELETE FROM _report WHERE _id = #{id}")
    void deleteReportById(@Param("id") String id);
}
