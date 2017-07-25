package me.alcruz.annotationsvalidation.models;

import me.alcruz.annotationsvalidation.annotations.Required;

public class Person {

    public Person(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Required
    public String firstName;
    @Required
    public String lastName;
    @Required
    public Integer age;
}
