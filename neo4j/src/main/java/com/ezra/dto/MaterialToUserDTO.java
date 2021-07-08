package com.ezra.dto;

import lombok.Data;

@Data
public class MaterialToUserDTO {

    private MaterialInfo materialInfo;

    private UserInfo userInfo;


    @Data
    public static class MaterialInfo{

        private String materialId;

        private String name;

        private Integer materialType;

        private String corpId;
    }


    @Data
    public  static class UserInfo{


        private String userId;

        private String corpId;

    }
}
