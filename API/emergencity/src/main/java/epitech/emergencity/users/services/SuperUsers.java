package epitech.emergencity.users.services;

import epitech.emergencity.users.User;
import epitech.emergencity.users.dto.GradeRequest;
import epitech.emergencity.users.dto.UsersRequest;
import io.vavr.control.Option;

public interface SuperUsers {
    Option<User> isSuperUser(String token);
    User createUser(UsersRequest request);
    Option<User> updateGrade(GradeRequest request);
}
