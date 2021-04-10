package top.leeti.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import top.leeti.entity.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("SELECT _id AS id, _promulgator_id AS promulgatorId, _attached_id As attachedId, _parent_id AS parentId, " +
            "_content AS content, _comment._gmt_create AS gmtCreate, _avatar_url AS avatarUrl, _nickname AS nickname " +
            "FROM _comment LEFT JOIN _user ON _user._stu_id = _comment._promulgator_id WHERE _is_available = 1 AND " +
            "_is_audit = 1 AND _attached_id = #{attachedId} ORDER BY _comment._gmt_create DESC")
    List<Comment> listCommentsOfPublishedInfo(@Param("attachedId") String attachedId);

    @Delete("DELETE FROM _comment WHERE _parent_id = #{parentId}")
    void deleteCommentByParentId(@Param("parentId") String parentId);

}
