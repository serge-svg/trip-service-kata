package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.Trip;

public class UserBuilder extends User {

    private User[] friends = new User[]{};
    private Trip[] trips = new Trip[]{};

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public UserBuilder withTripsTo(Trip... trips) {
        this.trips = trips;
        return this;
    }

    public UserBuilder friendsWith(User... friends) {
        this.friends = friends;
        return this;
    }

    public User build() {
        User user = new User();
        addTripsTo(user);
        addFriendsTo(user);

        return user;
    }

    private void addFriendsTo(User user) {
        for (User friend : friends) {
            user.addFriend(friend);
        }
    }

    private void addTripsTo(User user) {
        for (Trip trip : trips) {
            user.addTrip(trip);
        }
    }
}
