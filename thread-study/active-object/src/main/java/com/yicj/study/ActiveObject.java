package com.yicj.study;

public interface ActiveObject {
    Result<String> makeString(int count, char fillchar)  ;

    void displayString(String str) ;
}
