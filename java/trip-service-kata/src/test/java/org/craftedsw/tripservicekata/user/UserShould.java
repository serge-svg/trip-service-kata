package org.craftedsw.tripservicekata.user;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.craftedsw.tripservicekata.user.UserBuilder.aUser;

public class UserShould {

    private static final User ANNA = new User();
    private static final User POL = new User();

    @Test public void
    inform_when_she_is_friend_with_another_user(){
        User user = aUser().friendsWith(ANNA).build();

        assertThat(user.isFriendsWith(POL)).isFalse();
        assertThat(user.isFriendsWith(ANNA)).isTrue();
    }

}
