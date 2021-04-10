package top.leeti.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class College implements Serializable {
    private Integer id;
    private String collegeId;
    private String collegeName;
    private Boolean Available;
}
