package com.ezra.document;

import lombok.Data;
import org.elasticsearch.index.VersionType;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "user_detail",type = "user_detail")
public class UserDetailDocument {

    @Field(type = FieldType.Long)
    private Long id;

    @Field(type = FieldType.Long)
    private Long userId;

    private String userAddress;

    private String birthday;

}
