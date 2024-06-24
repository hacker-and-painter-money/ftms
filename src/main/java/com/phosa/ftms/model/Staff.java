package com.phosa.ftms.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

  @JsonProperty("staff_id")
  @TableId(type = IdType.AUTO)
  private Long staffId;
  private String name;
  @JsonProperty("role_id")
  private Long roleId;
  @JsonProperty("role_name")
  @TableField(exist = false)
  private String roleName;
  private String password;
}
