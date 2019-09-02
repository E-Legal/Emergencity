package epitech.emergencity.users.services;

import epitech.emergencity.domain.tables.records.EmergencityUserRecord;
import epitech.emergencity.users.User;
import io.vavr.control.Option;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface Users {

    User create(String name, String password);
    boolean validUser(String name, String password);
    Option<User> tokenGen(String name, String password);
    Option<User> get(String token);
}
