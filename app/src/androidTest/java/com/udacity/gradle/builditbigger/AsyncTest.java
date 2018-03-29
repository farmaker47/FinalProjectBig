package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static junit.framework.Assert.assertNotNull;


@RunWith(AndroidJUnit4.class)
public class AsyncTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTest = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void emptyString() throws ExecutionException, InterruptedException {

        EndpointsAsyncTask test = new EndpointsAsyncTask(activityTest.getActivity()," ");
        test.execute();
        String joke = test.get();
        if (joke.equals("connect timed out")) {
            joke = null;
        }
        assertNotNull(joke);
    }
}
