package fr.cedric.haegele.mareu_application;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MaReuInstrumentedTest {

    /**
     *when we Click on an item, MeetingDetailActivity is launched
     */

    @Test
    public void displayDetailMeetingsWithSuccess(){
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(withId(R.id.new_meeting_layout)).check(matches(isDisplayed()));

    }

    @Test
    public void createMeetingsWithSuccess(){

    }

    @Test
    public void deleteMeetingsWithSuccess(){


    }
    @Test
    public void filterByRoomWithSuccess(){


    }

    @Test
    public void filterByDateWithSuccess(){


    }

}
