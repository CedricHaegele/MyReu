package fr.cedric.haegele.mareu_application;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import static fr.cedric.haegele.mareu_application.utils.RecyclerViewItemCountAssertion.withItemCount;

import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.cedric.haegele.mareu_application.DI.DI;
import fr.cedric.haegele.mareu_application.model.Meeting;
import fr.cedric.haegele.mareu_application.ui.ActivityListMeeting;
import fr.cedric.haegele.mareu_application.utils.DeleteViewAction;

@RunWith(AndroidJUnit4.class)
public class MaReuInstrumentedTest {

    // This is fixed
    private static int ITEMS_COUNT = 7;

    private ActivityListMeeting mMeeting;

    @Rule
    public ActivityTestRule<ActivityListMeeting> mActivityRule =
            new ActivityTestRule(ActivityListMeeting.class);

    @Before
    public void setUp() {
        mMeeting = mActivityRule.getActivity();
        assertThat(mMeeting, notNullValue());
    }


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
    public void createMeetingWithSuccess(){

    }

    @Test
    public void deleteMeetingWithSuccess(){
        // Given : We remove the element at position 2
        onView(withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 7
        onView(withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT - 1));
    }



    @Test
    public void filterByRoomWithSuccess(){


    }

    @Test
    public void filterByDateWithSuccess(){


    }

}
