package com.phosa.ftms.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

  @JsonProperty("role_id")
  @TableId(type = IdType.AUTO)
  private long roleId;
  @JsonProperty("role_name")
  private String roleName;
}
