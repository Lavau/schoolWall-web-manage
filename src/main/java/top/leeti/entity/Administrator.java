package top.leeti.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Administrator implements Serializable {
    private String id;
    private String username;
    private String enPassword;
    private Integer roleId;
    private Date gmtCreate;
    private Boolean Available;

    private String roleName;
}
