package top.leeti.service;

import com.github.pagehelper.PageInfo;
import top.leeti.entity.PublishedInfo;

public interface PublishedInfoService {

    PageInfo<PublishedInfo> listReportedPublishedInfo(int pageNum);

    void updatePublishedInfo(PublishedInfo publishedInfo);

    String dealWithPass(String publishedInfoId, Integer mark, String reportId);
}
