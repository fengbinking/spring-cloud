package com.cloud.domain;

/**
 * Created by fengbin on 2017-07-26.
 */
public class User {
//    @NotNull(message = "id不能为空")
//    @Size(min=0,max=999999,message = "id必需为0到999999之间")
    private int id;
//    @NotNull(message = "用户名不能为空")
    private String userName;
//    @Min(value = 1,message = "年龄不能小于1")
//    @Max(value = 100,message = "年龄不能大于100")
    private int age;

    public User(){};
    public User(int id, String userName, int age){
        this.id=id;
        this.userName=userName;
        this.age=age;
    }
    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
