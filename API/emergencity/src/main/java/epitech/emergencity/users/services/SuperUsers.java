package epitech.emergencity.users.services;

import epitech.emergencity.users.User;
import epitech.emergencity.users.dto.GradeRequest;
import epitech.emergencity.users.dto.UsersRequest;
import io.vavr.control.Option;

public interface SuperUsers {
    User createUser(UsersRequest request);
}
