package com.yicj.thread.hello;

import org.junit.Test;

public class ValidationUtilTest {

    @Test
    public void testValidateBean() {
        Account account = new Account();
        account.setAlias("kalakala");
        account.setUserName("wokalakala");
        account.setPassWord("密码");
        account.setLevel(800);

        ValidResult validResult = ValidationUtil.validateBean(account);
        if(validResult.hasErrors()){
            String errors = validResult.getErrors();
            System.out.println(errors);
        }
    }

    @Test
    public void testValidateProperty(){
        Account account = new Account();
        account.setAlias("kalakala");
        account.setUserName("wokalakala");
        account.setPassWord("密码");
        account.setLevel(800);
        ValidResult validResult = ValidationUtil.validateProperty(account,"level") ;
        if(validResult.hasErrors()){
            String errors = validResult.getErrors();
            System.out.println(errors);
        }
    }
}
