package cn.kgc.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

@Data
public class User {

  private Long id;
//  @TableField(value = "userCode")
  private String userCode;
  private String userName;
  private String userPassword;
  private Long gender;
  private java.sql.Date birthday;
  private String phone;
  private String address;
  private Long userRole;
  private Long createdBy;
  private java.sql.Timestamp creationDate;
  private Long modifyBy;
  private java.sql.Timestamp modifyDate;



}
