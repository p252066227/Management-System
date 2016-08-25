package edu.feicui.studentsonline.entity;

/**
 * Created by Administrator on 2016/8/17.
 */
public class Student {

    private String name;
    private String age;
    private String xingbie;

    public Student(String name, String age, String xingbie) {
        this.name = name;
        this.age = age;
        this.xingbie = xingbie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getXingbie() {
        return xingbie;
    }

    public void setXingbie(String xingbie) {
        this.xingbie = xingbie;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", xingbie='" + xingbie + '\'' +
                '}';
    }
}
