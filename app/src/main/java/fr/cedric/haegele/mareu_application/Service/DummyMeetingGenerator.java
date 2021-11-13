package fr.cedric.haegele.mareu_application.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.cedric.haegele.mareu_application.R;
import fr.cedric.haegele.mareu_application.model.Meeting;
import fr.cedric.haegele.mareu_application.model.Rooms;

public class DummyMeetingGenerator {

    public static List<Rooms> DUMMY_ROOMS = Arrays.asList(
            new Rooms(1, "Local Bar", R.drawable.ic_baseline_local_bar_24),
            new Rooms(2, "Local Cafe", R.drawable.ic_baseline_local_cafe_24),
            new Rooms(3, "Local Activity", R.drawable.ic_baseline_local_activity_24),
            new Rooms(4, "Local Airport", R.drawable.ic_baseline_local_airport_24),
            new Rooms(5, "Local Car Wash", R.drawable.ic_baseline_local_car_wash_24),
            new Rooms(6, "Local Movies", R.drawable.ic_baseline_local_movies_24),
            new Rooms(7, "Local Pizza", R.drawable.ic_baseline_local_pizza_24),
            new Rooms(8, "Local Police", R.drawable.ic_baseline_local_police_24),
            new Rooms(9, "Local Dining", R.drawable.ic_baseline_local_dining_24),
            new Rooms(10, "Local Atm", R.drawable.ic_baseline_local_atm_24)

    );
    static List<Rooms> generateRooms() {

        return new ArrayList<>(DUMMY_ROOMS);
    }

    List<String> participants = Arrays.asList
            ("Roger@lamzone.fr",
                    "Valentin@lamzone.fr",
                    "Aylin@lamzone.fr",
                    "Samantha@lamzone.fr",
                    "Johanna@lamzone.fr",
                    "Abdel@lamzone.fr",
                    "Sabrina@lamzone.fr",
                    "Loana@lamzone.fr",
                    "Edouard@lamzone.fr",
                    "Bob@lamzone.fr");
}

