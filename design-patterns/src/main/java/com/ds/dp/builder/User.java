package com.ds.dp.builder;

/**
 * @Author ds
 * @Date 2021/3/15 9:56
 * @Description
 */
public class User {

    private String name;

    private String email;

    public User(){

    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public static class UserBuilder{

        private String name;

        private String email;

        public UserBuilder name(String name){
            this.name = name;
            return this;
        }
        
        public UserBuilder email(String email){
            this.email = email;
            return this;
        }

        public User build(){
            if(name == null || name.length() < 1){
                throw new IllegalArgumentException("name不能为空！");
            }
            return new User(name,email);
        }

    }

}
