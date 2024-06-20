package com.phosa.ftms.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("`order`")
public class Order {

  @JsonProperty("order_id")
  @TableId(type = IdType.AUTO)
  private Long orderId;
  @JsonProperty("user_id")
  private Long userId;
  @JsonProperty("order_status")
  private int orderStatus;
  @JsonProperty("total_price")
  private Double totalPrice;
  @JsonProperty("order_details")
  @TableField(exist = false)
  private List<OrderDetail> orderDetails;
  @JsonProperty("created_at")
  @TableField(fill = FieldFill.INSERT)
  private Date createdAt;
  @JsonProperty("updated_at")
  @TableField(fill = FieldFill.UPDATE)
  private Date updatedAt;
  @TableField(exist = false)
  private User user;
}
