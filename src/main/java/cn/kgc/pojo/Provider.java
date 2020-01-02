package cn.kgc.pojo;

import lombok.Data;

@Data
public class Provider {

  private Long id;
  private String proCode;
  private String proName;
  private String proDesc;
  private String proContact;
  private String proPhone;
  private String proAddress;
  private String proFax;
  private Long createdBy;
  private java.sql.Timestamp creationDate;
  private java.sql.Timestamp modifyDate;
  private Long modifyBy;




}
