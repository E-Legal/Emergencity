package epitech.emergencity.users.services;

import epitech.emergencity.users.User;
import io.vavr.control.Option;

import java.util.UUID;


public interface Users {

    User create(String name, String password);
    boolean validUser(String name, String password);
    Option<User> get(UUID id);
    Option<User> getUserLogin(String name, String password);
}
