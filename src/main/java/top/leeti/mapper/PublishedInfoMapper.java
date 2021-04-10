package top.leeti.mapper;

import org.apache.ibatis.annotations.*;
import top.leeti.entity.PublishedInfo;

import java.util.List;

@Mapper
public interface PublishedInfoMapper {

    @Select("SELECT pi._id AS id, _promulgator_id AS promulgatorId, _description AS description, _picture_num AS " +
            "pictureNum, r._gmt_create AS gmtCreate, r._report_reason AS reportReason, r._id AS reportId, " +
            "rt._name AS reportTypeName, pi._picture_num AS pictureNum, pi._type_id AS typeId FROM _report AS r LEFT " +
            "OUTER JOIN _published_info AS pi ON r._published_info_id = pi._id LEFT OUTER JOIN _report_type AS rt ON " +
            "rt._id = r._report_type_id WHERE r._is_audit = 0 ORDER BY r._gmt_create ASC")
    List<PublishedInfo> listReportedPublishedInfo();

    @Select("SELECT pi._id AS id, _promulgator_id AS promulgatorId, u._avatar_url AS avatarUrl, u._nickname AS " +
            "nickname, _type_id AS typeId, _chinese_name AS typeName, _description AS description, _picture_num " +
            "AS pictureNum, _like_num AS likeNum, _view_num AS viewNum, _comment_num AS commentNum, pi._gmt_create " +
            "AS gmtCreate, pi._is_available AS Available, _is_anonymous AS Anonymous, _is_audit AS Audit, _msg AS " +
            "msg, _gmt_claim AS gmtClaim, _claimant_id AS claimantId FROM _published_info AS pi LEFT JOIN _type ON " +
            "pi._type_id = _type._id LEFT JOIN _user AS u ON u._stu_id = pi._promulgator_id WHERE pi._id = #{id}")
    PublishedInfo getPublishedInfoById(@Param("id") String id);

    @Update("UPDATE _published_info SET _like_num = #{pi.likeNum}, _view_num = #{pi.viewNum}, _comment_num = " +
            "#{pi.commentNum}, _is_available = #{pi.Available}, _is_audit = #{pi.Audit}, _gmt_claim = " +
            "#{pi.gmtClaim}, _claimant_id = #{pi.claimantId} WHERE _id = #{pi.id}")
    void updatePublishedInfo(@Param("pi") PublishedInfo publishedInfo);

    @Delete("DELETE FROM _published_info WHERE _id = #{id}")
    void deletePublishedInfo(@Param("id") String id);
}
