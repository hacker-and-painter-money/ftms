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
public class Category {

    @JsonProperty("category_id")
    @TableId(type = IdType.AUTO)
    private long categoryId;
    @JsonProperty("category_name")
    private String categoryName;

}
