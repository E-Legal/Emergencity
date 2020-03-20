package epitech.emergencity.security.services;

import epitech.emergencity.security.Token;
import io.vavr.control.Option;

import java.util.UUID;

public interface Tokens {
    Token create(UUID user_id);
    Option<Token> checkToken(String token);
    Option<Token> update(String oldToken);
    boolean is_admin(String token);
    boolean is_super_user(String token);
    boolean is_algo(String token);
}
