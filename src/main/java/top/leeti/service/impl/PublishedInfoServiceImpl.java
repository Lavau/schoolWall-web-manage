package top.leeti.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.leeti.entity.*;
import top.leeti.mapper.*;
import top.leeti.service.PublishedInfoService;
import top.leeti.util.FileUtil;
import top.leeti.util.TimeStampUtil;
import top.leeti.util.UuidUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class PublishedInfoServiceImpl implements PublishedInfoService {

    @Value("${page.size}")
    private int pageSize;

    @Resource
    private PublishedInfoMapper publishedInfoMapper;

    @Resource
    private ReportMapper reportMapper;

    @Resource
    private MsgMapper msgMapper;

    @Resource
    private LikeMapper likeMapper;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private FavoriteMapper favoriteMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageInfo<PublishedInfo> listReportedPublishedInfo(int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<PublishedInfo> list = publishedInfoMapper.listReportedPublishedInfo();
        PageInfo<PublishedInfo> pageInfo =  new PageInfo<>(list);

        pageInfo.getList().forEach(item -> {
            if (Integer.valueOf(0).equals(item.getPictureNum())) {
                item.setPictureUrlList(new ArrayList<>(0));
            } else {
                item.setPictureUrlList(FileUtil.obtainListOfPictureUrl(item.getTypeId(), item.getId()));
            }
        });

        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePublishedInfo(PublishedInfo publishedInfo) {
        publishedInfoMapper.updatePublishedInfo(publishedInfo);
    }

    @Override
    public String dealWithPass(String publishedInfoId, Integer mark, String reportId) {
        Report report = reportMapper.getReportById(reportId);

        if (report == null) {
            return "(???'???'???) ?????????????????????!???????????????(???'???'???)";
        }

        String contentOfReporterMsg = "??????" + TimeStampUtil.timeStamp(report.getGmtCreate())
                + "??????????????????????????????????????????????????????????????????(???'???'???)";
        Msg reporterMsg = new Msg(UuidUtil.acquireUuid(), report.getReporterId(), contentOfReporterMsg, new Date(), false);
        msgMapper.insertMsg(reporterMsg);

        sendPromulgatorMsg(publishedInfoId, mark, reportId, report);

        return "???????????? (???'???'???) ";
    }

    private void sendPromulgatorMsg(String publishedInfoId, Integer mark, String reportId, Report report) {
        PublishedInfo publishedInfo = publishedInfoMapper.getPublishedInfoById(publishedInfoId);

        String contentOfPromulgatorMsg = "?????? " + TimeStampUtil.timeStamp(publishedInfo.getGmtCreate()) + " ?????????????????? "
                + TimeStampUtil.timeStamp(report.getGmtCreate()) + " ?????????????????????" + report.getReportReason() + "?????????????????????";
        if (Integer.valueOf(0).equals(mark)) {
            contentOfPromulgatorMsg += "??????????????????????????????????????????????????????";

            deletePublishedInfo(publishedInfoId, reportId);
        } else {
            contentOfPromulgatorMsg += "????????????????????????(???'???'???)";

            report.setAudit(true);
            reportMapper.updateReport(report);

            publishedInfo.setAvailable(true);
            publishedInfo.setAudit(true);
            publishedInfoMapper.updatePublishedInfo(publishedInfo);
        }

        Msg promulgatorMsg = new Msg(UuidUtil.acquireUuid(),
                publishedInfo.getPromulgatorId(), contentOfPromulgatorMsg, new Date(), true);
        msgMapper.insertMsg(promulgatorMsg);
    }

    private void deletePublishedInfo(String publishedInfoId, String reportId) {
        likeMapper.deleteLikeById(publishedInfoId);

        List<Comment> comments = commentMapper.listCommentsOfPublishedInfo(publishedInfoId);
        comments.forEach(comment -> commentMapper.deleteCommentByParentId(comment.getId()));

        favoriteMapper.deleteFavoritedContentByPublishedInfoId(publishedInfoId);

        reportMapper.deleteReportById(reportId);

        publishedInfoMapper.deletePublishedInfo(publishedInfoId);
    }
}
