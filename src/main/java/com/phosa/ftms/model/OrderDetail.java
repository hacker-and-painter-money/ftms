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
public class OrderDetail {

  @JsonProperty("detail_id")
  @TableId(type = IdType.AUTO)
  private long detailId;
  @JsonProperty("order_id")
  private long orderId;
  @JsonProperty("dish_id")
  private long dishId;
  private int quantity;
  private double price;
  @JsonProperty("created_at")
  @TableField(fill = FieldFill.INSERT)
  private Date createdAt;
}
