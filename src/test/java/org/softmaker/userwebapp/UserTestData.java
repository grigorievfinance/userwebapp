package org.softmaker.userwebapp;

import org.softmaker.userwebapp.model.Role;
import org.softmaker.userwebapp.model.User;
import org.softmaker.userwebapp.web.json.JsonUtil;

import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.softmaker.userwebapp.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(User.class, "registered", "password");
    public static TestMatcher<User> USER_WITH_MEALS_MATCHER =
            TestMatcher.usingAssertions(User.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("registered", "password").ignoringAllOverriddenEquals().isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });


    public static final int NOT_FOUND = 10;
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "user@gmail.com", "password", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin",Role.ADMIN, Role.USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }

    public static String jsonWithPassword(User user, String password){
        return JsonUtil.writeAdditionProps(user, "password", password);
    }
}
