package com.yicj.study.hello;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.*;

/**
 * 校验结果类
 */
@Data
public class ValidResult {
    /**
     * 是否有错误
     */
    private boolean hasErrors;
    /**
     * 错误信息
     */
    private List<ErrorMessage> errors;

    public ValidResult() {
        this.errors = new ArrayList<>();
    }
    public boolean hasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    /**
     * 获取所有验证信息
     * @return 字符串形式
     */
    public String getErrors(){
        StringBuilder sb = new StringBuilder();
        for (ErrorMessage error : errors) {
            sb.append(error.getPropertyPath())
                    .append(":")
                    .append(error.getMessage()).append(" ");
        }
        return sb.toString();
    }

    public void addError(String propertyName, String message) {
        this.errors.add(new ErrorMessage(propertyName, message));
    }

    @Data
    @AllArgsConstructor
    class ErrorMessage {
        private String propertyPath;
        private String message;
    }
}

