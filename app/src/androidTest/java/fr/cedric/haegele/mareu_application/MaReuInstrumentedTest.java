package fr.cedric.haegele.mareu_application;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import static fr.cedric.haegele.mareu_application.utils.RecyclerViewItemCountAssertion.withItemCount;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collections;

import fr.cedric.haegele.mareu_application.Service.MeetingApiService;
import fr.cedric.haegele.mareu_application.model.Meeting;
import fr.cedric.haegele.mareu_application.ui.ActivityListMeeting;
import fr.cedric.haegele.mareu_application.utils.ClickMeetingViewAction;
import fr.cedric.haegele.mareu_application.utils.DeleteViewAction;

@RunWith(AndroidJUnit4.class)
public class MaReuInstrumentedTest {

    private static int ITEMS_COUNT = 7;
    private int positionTest = 1;
    MeetingApiService service;

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
     * when we Click on an item, MeetingDetailActivity is launched
     */
    @Test
    public void displayDetailMeetingWithSuccess() {

        onView(allOf(withId(R.id.recyclerView), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(positionTest, new ClickMeetingViewAction()));
        onView(withId(R.id.new_meeting_layout)).check(matches(isDisplayed()));

    }

    /**
     * when the meeting is deleted, this item is no more displayed
     */
    @Test
    public void deleteMeetingWithSuccess() {

        onView(withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT));
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * test to create a meeting
     */

    @Test
    public void createMeetingWithSuccess() {

        onView(withId(R.id.create_meeting_btn)).perform(click());
        onView(withId(R.id.new_meeting_layout)).check(matches((isDisplayed())));
        onView(withId(R.id.meeting_topic)).perform(replaceText("Planning équipe"));
        onView(allOf(withId(R.id.meeting_room),isDisplayed())).perform();
        onView(withId(R.id.meeting_date)).perform(replaceText("01/01/2021"));
        onView(withId(R.id.meeting_hour)).perform(replaceText("12:00"));
        onView(withId(R.id.mailParticipant)).perform(replaceText("sarah@lamzone.fr"));
        onView(withId(R.id.btn_add_mail)).perform(click());
        onView(withId(R.id.btn_add_meeting)).perform(click());
        onView(withId(R.id.recyclerView)).check(matches((isDisplayed())));
        onView(withId(R.id.recyclerView)).check(withItemCount(ITEMS_COUNT + 1));
    }

    @Test
    public void filterByRoomWithSuccess() {



    }

    @Test
    public void filterByDateWithSuccess() {


    }

}
