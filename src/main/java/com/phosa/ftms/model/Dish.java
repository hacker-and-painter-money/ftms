package com.phosa.ftms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish {

  @JsonProperty("dish_id")
  @TableId(type = IdType.AUTO)
  private Long dishId;
  @JsonProperty("dish_name")
  private String dishName;
  @JsonProperty("category_id")
  private Long categoryId;
  private Double price;
  private String description;
  @JsonProperty("image_url")
  private String imageUrl;
}
