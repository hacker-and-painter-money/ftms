package com.phosa.ftms.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  @JsonProperty("order_id")
  @TableId(type = IdType.AUTO)
  private long orderId;
  @JsonProperty("user_id")
  private long userId;
  @JsonProperty("order_status")
  private String orderStatus;
  @JsonProperty("total_price")
  private double totalPrice;
  @JsonProperty("created_at")
  @TableField(fill = FieldFill.INSERT)
  private Date createdAt;
  @JsonProperty("updated_at")
  @TableField(fill = FieldFill.UPDATE)
  private Date updatedAt;
}
