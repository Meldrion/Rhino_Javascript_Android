package lu.innocence.rhino.example;

import android.util.Log;

/**
 * Created by fabien on 12.04.17.
 */

public class Person {

    private String name;
    private int age;

    public Person(String name,int age) {
        this.setAge(age);
        this.setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public void printDetails() {
        Log.d("Person", String.format("Name: %s and Age: %s", this.name,this.age));
    }

}
