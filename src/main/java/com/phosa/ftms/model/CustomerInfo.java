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
public class CustomerInfo {

    @JsonProperty("customer_id")
    @TableId(type = IdType.AUTO)
    private Long customerId;
    @JsonProperty("user_id")
    private Long userId;
    private String email;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("real_name")
    private String realName;
    private String address;


}
