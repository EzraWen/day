package com.ezra.dto;

import lombok.Data;

@Data
public class UserToCustomerDTO {

    private UserInfo userInfo;

    private CustomerInfo customerInfo;

    private String materialId;

    @Data
    public static class CustomerInfo{

        private String customerId;

        private String corpId;
    }


    @Data
    public  static class UserInfo{


        private String userId;

        private String corpId;

    }
}
