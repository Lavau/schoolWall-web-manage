package top.leeti.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FavoriteMapper {

    @Delete("DELETE FROM _favorited_content WHERE _published_info_id = #{publishedInfoId}")
    void deleteFavoritedContentByPublishedInfoId(@Param("publishedInfoId") String publishedInfoId);

}
