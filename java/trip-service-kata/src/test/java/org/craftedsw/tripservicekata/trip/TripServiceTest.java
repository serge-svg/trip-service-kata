package org.craftedsw.tripservicekata.trip;


import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TripServiceTest {

    private static final User GUEST = null;
    private static final User USER = new User();
    private static final User REGISTERED_USER = new User();
    private static final User ANOTHER_USER = new User();;
    private static final Trip CATALONIA = new Trip();
    private static final Trip CHINA = new Trip();
    private User loggedInUser;
    private TripService tripService;

    @Before
    public void initialise(){
        loggedInUser = REGISTERED_USER;

        tripService = new TestableTripService();
    }

    @Test(expected = UserNotLoggedInException.class) public void
    validate_the_logged_in_user(){
        loggedInUser = GUEST;

        tripService.getTripsByUser(USER);
    }

    @Test public void
    return_no_trips_when_users_are_not_friends(){
        User stranger = new User();
        stranger.addFriend(ANOTHER_USER);
        stranger.addTrip(CATALONIA);
        List<Trip> trips = tripService.getTripsByUser(stranger);

        assertThat(trips).isEmpty();
    }

    @Test public void
    return_trips_when_users_are_friends(){
        User friend = new User();
        friend.addFriend(ANOTHER_USER);
        friend.addFriend(REGISTERED_USER);
        friend.addTrip(CATALONIA);
        friend.addTrip(CHINA);
        List<Trip> trips = tripService.getTripsByUser(friend);

        //assertThat(trips).isNotEmpty();
        assertThat(trips).containsExactlyInAnyOrder(CHINA, CATALONIA);
    }

    private class TestableTripService extends TripService{
        @Override
        User loggedInUser() {
            return loggedInUser;
        }

        @Override
        List<Trip> tripsBy(User user) {
            return user.trips();
        }

    }

}
