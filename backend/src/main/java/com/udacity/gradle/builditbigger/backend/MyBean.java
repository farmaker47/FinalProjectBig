package com.udacity.gradle.builditbigger.backend;


import com.george.javalibraryforjokes.JokesClass;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private JokesClass jokesClass;

    public MyBean() {
        jokesClass = new JokesClass();
    }

    public String getDataJoke() {
        return jokesClass.randomJokesToPass();
    }

}