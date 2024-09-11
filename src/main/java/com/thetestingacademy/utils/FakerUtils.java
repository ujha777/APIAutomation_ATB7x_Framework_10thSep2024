package com.thetestingacademy.utils;

import com.github.javafaker.Faker;

public class FakerUtils {
    static Faker faker;

    public static String getUsername(){
        faker=new Faker();
        String name = faker.name().fullName();
        return name;

    }
}
