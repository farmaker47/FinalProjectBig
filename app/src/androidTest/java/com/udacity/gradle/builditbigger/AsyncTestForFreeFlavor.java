package com.udacity.gradle.builditbigger;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by farmaker1 on 30/03/2018.
 */
@RunWith(AndroidJUnit4.class)
public class AsyncTestForFreeFlavor {

    @Rule
    public ActivityTestRule<MainActivity> activityTest = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void emptyString() throws ExecutionException, InterruptedException {

        Thread.sleep(3000);

        onView(withId(R.id.buttonForJoke)).check(matches((isDisplayed())));
        onView(withId(R.id.buttonForJoke)).perform(click());

        //little time for ad to come alive
        Thread.sleep(5000);

        //perform click on ads close button
        ViewInteraction imageButton = onView(
                allOf(withContentDescription("Interstitial close button"), isDisplayed()));
        imageButton.perform(click());

        EndpointsAsyncTask test = new EndpointsAsyncTask(activityTest.getActivity());
        test.execute();
        String joke = test.get();
        if (joke.equals("connect timed out")) {
            joke = null;
        }
        assertNotNull(joke);
    }

}
