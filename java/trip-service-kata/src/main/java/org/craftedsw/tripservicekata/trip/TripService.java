package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		if (loggedInUser() == null) {
			throw new UserNotLoggedInException();
		}

		List<Trip> tripList = new ArrayList<Trip>();
		return user.isFriendsWith(loggedInUser())
						? tripsBy(user)
						: new ArrayList<Trip>();

	}

	List<Trip> tripsBy(User user) {
		return TripDAO.findTripsByUser(user);
	}

	User loggedInUser() {
		return UserSession.getInstance().getLoggedUser();
	}

}
