package com.streamexample;


public class User {
    private Long id;
    private String name;
    private String lName;
    private int age;
    private Boolean isActive;

    public User(Long id, String name, String lName, int age, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.lName = lName;
        this.age = age;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
