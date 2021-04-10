package top.leeti.result;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class Result implements Serializable {
    private Object object;   //请求返回结果是某个具体对象
    private List list;       //请求返回结果是列表
    private Boolean success; //请求处理是否成功
    private String msg;      //请求处理的建议
    private String status;   //请求处理的状态返回
    private String description; // 描述
}
