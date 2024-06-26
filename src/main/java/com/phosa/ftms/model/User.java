package com.phosa.ftms.model;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @JsonProperty("user_id")
  @TableId(type = IdType.AUTO)
  private Long userId;
  private String username;
  private String password;
  @JsonProperty("is_real_name")
  @TableField(fill = FieldFill.INSERT)
  private Long isRealName;
  @JsonProperty("created_at")
  @TableField(fill = FieldFill.INSERT)
  private Date createdAt;
  @JsonProperty("updated_at")
  @TableField(fill = FieldFill.UPDATE)
  private Date updatedAt;
  @JsonProperty("customer_info")
  @TableField(exist = false)
  private CustomerInfo customerInfo;


}
