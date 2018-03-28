package com.george.javalibraryforjokes;

import java.util.Random;

public class JokesClass {

    private String[] jokesForMentors;
    private Random random;

    public JokesClass() {

        random = new Random();

        jokesForMentors = new String[3];
        jokesForMentors[0] = "Mother: \"How was school today, Patrick?\"\n" +
                "\n" +
                "Patrick: \"It was really great mum! Today we made explosives!\"\n" +
                "\n" +
                "Mother: \"Ooh, they do very fancy stuff with you these days. And what will you do at school tomorrow?\"\n" +
                "\n" +
                "Patrick: \"What school?\"";
        jokesForMentors[1] = "Sleep with an open window tonight!      1400 mosquitos like that. 420 mosquitos commented on it. 210 mosquitos shared this. " +
                "One mosquito invited for the event. 2800 mosquitos will be attending the event.";
        jokesForMentors[2] = "In a boomerang shop: \"I'd like to buy a new boomerang please. Also, can you tell me how to throw the old one away?\"\n" +
                "\n" +
                "Check out this really funny jokes: http://www.short-funny.com/funniest-jokes-3.php#ixzz5AxWZ95ya";
    }

    public String randomJokesToPass(){
        return jokesForMentors[random.nextInt(jokesForMentors.length)];
    }
}
