package com.yicj.optional;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.junit.Assert.* ;

@Slf4j
public class OptionalTest {

    @Test(expected = NoSuchElementException.class)
    public void testWhenCreateEmptyOptionThenNull(){
        Optional<Object> emptyOpt = Optional.empty();
        emptyOpt.get() ;
    }

    //你可以使用  of() 和 ofNullable() 方法创建包含值的 Optional。
    // 两个方法的不同之处在于如果你把 null 值作为参数传递进去，
    // of() 方法会抛出 NullPointerException：
    @Test(expected = NullPointerException.class)
    public void testWhenCreateOfEmptyOptionalThenNullPointerException(){
        User user = null;
        Optional<User> opt = Optional.of(user) ;
    }


    @Test
    public void testWhenCreateOfNullableOptionalThenOk(){
        String name = "John" ;
        Optional<String> opt = Optional.ofNullable(name);
        assertEquals("John", opt.get());
    }


    @Test
    public void testWhenCheckIfPresentThenOk(){
        User user = new User("john@gmail.com","1234") ;
        Optional<User> opt = Optional.ofNullable(user);
        assertTrue(opt.isPresent());
        assertEquals(user.getEmail(),opt.get().getEmail());
    }



    //返回默认值
    //orElse()，如果有值则返回该值，否则返回传递给它参数值
    @Test
    public void testWhenEmptyValueThenReturnDefault(){
        User user = null ;
        User user2 = new User("anna@gmail.com","1234") ;
        User result = Optional.ofNullable(user).orElse(user2);
        assertEquals(user2.getEmail(),result.getEmail());
    }


    //如果对象的初始值不是 null，那么默认值会被忽略
    @Test
    public void testWhenValueNotNullThenIgnoreDefault(){
        User user = new User("john@gmail.com","1234") ;
        User user2 = new User("anna@gmail.com","1234") ;
        User result = Optional.ofNullable(user).orElse(user2) ;
        assertEquals("john@gmail.com",result.getEmail());
    }


    //两个 Optional  对象都包含非空值，两个方法都会返回对应的非空值。
    // 不过，orElse() 方法仍然创建了 User 对象。与之相反，orElseGet() 方法不创建 User 对象。
    @Test
    public void testGiveEmptyValueWhenCompareThenOk(){
        User user = null ;
        log.info("Using orElse");
        Optional.ofNullable(user).orElse(createNewUser()) ;
        log.info("User orElseGet");
        User result = Optional.ofNullable(user).orElseGet(this::createNewUser) ;
    }


    @Test
    public void testGivePresentValueWhenCompareThenOk(){
        User user = new User("john@gmail.com","1234") ;
        log.info("Using orElse");
        Optional.ofNullable(user).orElse(createNewUser()) ;
        log.info("User orElseGet");
        User result = Optional.ofNullable(user).orElseGet(this::createNewUser) ;
    }

    private User createNewUser(){
        log.info("Creating New User");
        return new User("extra@gmail.com","1234") ;
    }

    //orElseThrow() API —— 它会在对象为空的时候抛出异常，而不是返回备选的值
    @Test
    public void testWhenThrowExceptionThenOk(){
        User user = null ;
        User result = Optional.ofNullable(user).orElseThrow(()-> new IllegalArgumentException()) ;
    }


    @Test
    public void testWhenMapThenOk(){
        User user = new User("anna@gmail.com","1234") ;
        String email = Optional.ofNullable(user).map(u -> u.getEmail()).orElse("default@gmail.com") ;
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testWhenFlatMapThenOk(){

        User user = new User("anna@gmail.com", "1234") ;
        user.setPosition("Developer");
        String position = Optional.ofNullable(user).flatMap(u -> u.getPosition()).orElse("default") ;
        assertEquals(position,user.getPosition().get());
    }


    @Test
    public void testFilterThenOk(){
        User user = new User("anna@gmail.com","1234") ;
        Optional<User> result = Optional.ofNullable(user)
                .filter(u -> u.getEmail() != null && u.getEmail().contains("@"));
        assertTrue(result.isPresent());
    }


    @Test
    public void testWhenChainingThenOk(){
        User user = new User("anna@gmail.com","1234") ;
        String result = Optional.ofNullable(user)
                .flatMap(u -> u.getAddress())
                .flatMap(a -> a.getCountry())
                .map(c -> c.getIsoCode())
                .orElse("default") ;
        assertEquals(result, "default");
    }

    @Test
    public void testWhenChainingThenOk2(){
        User user = new User("anna@gmail.com","1234") ;
        String result = Optional.ofNullable(user)
                .flatMap(User::getAddress)
                .flatMap(Address::getCountry)
                .map(Country::getIsoCode)
                .orElse("default") ;
        assertEquals(result, "default");
    }


    @Data
    class User{
        private String email ;
        private String position ;
        private Address address;

        public User(String email, String position){
            this.email = email ;
            this.position = position ;
        }

        public Optional<String> getPosition(){
            return Optional.ofNullable(position) ;
        }

        public Optional<Address> getAddress(){
            return Optional.ofNullable(address) ;
        }
    }


    @Data
    class Address {
        private Country country;

        public Optional<Country> getCountry() {
            return Optional.ofNullable(country);
        }
    }

    @Data
    class Country{
        private String isoCode ;
    }
}
