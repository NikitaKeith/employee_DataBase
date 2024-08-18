package com.andreev.exception_handling;

import lombok.Getter;

@Getter
public class EmployeeIncorrectData {
    public String info;

    public EmployeeIncorrectData() {
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
