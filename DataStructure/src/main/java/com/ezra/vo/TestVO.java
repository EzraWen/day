package com.ezra.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestVO {

    private Integer key;

    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestVO testVO = (TestVO) o;
        return false;
    }

    @Override
    public int hashCode() {
        return key;
    }
}
