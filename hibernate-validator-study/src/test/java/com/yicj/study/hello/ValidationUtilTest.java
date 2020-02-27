package com.yicj.study.hello;

import org.junit.Test;

public class ValidationUtilTest {

    @Test
    public void test5() {
        Account account = new Account();
        account.setAlias("kalakala");
        account.setUserName("wokalakala");
        account.setPassWord("密码");
        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(account);
        if(validResult.hasErrors()){
            String errors = validResult.getErrors();
            System.out.println(errors);
        }
    }
}
