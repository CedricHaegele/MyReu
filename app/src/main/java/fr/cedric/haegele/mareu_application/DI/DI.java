package fr.cedric.haegele.mareu_application.DI;

import java.util.List;

import fr.cedric.haegele.mareu_application.Service.DummyMeetingApiService;
import fr.cedric.haegele.mareu_application.Service.MeetingApiService;
import fr.cedric.haegele.mareu_application.model.Meeting;


/**
 * Singleton : get always an instance of MeetingApiService
 */
public class DI {

    private static final MeetingApiService service = new DummyMeetingApiService();

    /**
     * Get an instance of MeetingApiService
     */
    public static MeetingApiService getMeetingApiService() {
        return service;
    }

    /**
     * Useful for tests, so we ensure the context is clean
     */
    public static MeetingApiService getNewInstanceMeetingApiService() {
        return new DummyMeetingApiService();
    }
}