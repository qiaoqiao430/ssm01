package cn.kgc.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

@Data
public class Bill {

  private Long id;
  private String billCode;
  private String productName;
  private String productDesc;
  private String productUnit;
  private double productCount;
  private double totalPrice;
  private Long isPayment;
  private Long createdBy;
  private java.sql.Timestamp creationDate;
  private Long modifyBy;
  private java.sql.Timestamp modifyDate;
  private Long providerId;
  @TableField(exist = false)
  private Provider provider;


}
