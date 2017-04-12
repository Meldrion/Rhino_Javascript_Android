package lu.innocence.rhino.example;

import android.util.Log;

/**
 * Created by fabien on 12.04.17.
 */

public class Person {

    private String name;
    private int age;

    public Person() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void printDetails() {
        Log.d("Person", String.format("Name: %s and Age: %s", this.name,this.age));
    }

}
