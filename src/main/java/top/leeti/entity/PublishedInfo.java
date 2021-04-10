package top.leeti.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class PublishedInfo implements Serializable {
    private String id;
    private Integer iId;
    private String promulgatorId;
    private Integer typeId;
    private String description;
    private Integer pictureNum;
    private Integer likeNum;
    private Integer viewNum;
    private Integer commentNum;
    private Date gmtCreate;
    private Boolean Audit;
    private Boolean Available;
    private Boolean Anonymous;
    private String msg;
    private Date gmtClaim;
    private String claimantId;
    
    private String createTime;
    private String reportReason;
    private String reportTypeName;
    private String reportId;
    protected List<String> pictureUrlList;
    
}
