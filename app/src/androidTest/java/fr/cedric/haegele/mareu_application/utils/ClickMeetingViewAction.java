package fr.cedric.haegele.mareu_application.utils;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;

public class ClickMeetingViewAction implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on meeting name";
    }

    @Override
    public void perform(UiController uiController, View view) {
view.performClick();
    }
}
