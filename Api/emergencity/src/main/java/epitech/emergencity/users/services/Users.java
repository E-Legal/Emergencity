package epitech.emergencity.users.services;

import epitech.emergencity.users.User;
import io.vavr.control.Option;

import java.util.UUID;


public interface Users {

    User create(String name, String password);
    boolean validUser(String name, String password);
    Option<User> tokenGen(String name, String password);
    Option<User> tokenUpdate(String oldToken);
    Option<User> checkToken(String token);
    Option<User> get(UUID id);
    Option<User> UserEgalOfSuppAdmin(String token);
}
