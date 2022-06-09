package Model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Configuration;
public class Person {

    int age;
    String name ;
    public Person(){
    }
    public Person(int age ,String name){
this.age=age;
this.name=name;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
