package com.ezra.dto;

import lombok.Data;

@Data
public class BasePage {


    private int current = 1;

    private int size = 10;

    public int getStartNum() {
        return (current -1) * size;
    }






}
