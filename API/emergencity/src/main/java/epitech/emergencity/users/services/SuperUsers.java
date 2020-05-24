package epitech.emergencity.users.services;

import epitech.emergencity.users.User;
import epitech.emergencity.users.dto.GradeRequest;
import epitech.emergencity.users.dto.UsersRequest;
import io.vavr.control.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SuperUsers {
    User createUser(UsersRequest request);
    Page<User> listUser(Pageable pageable);
    PageImpl<Object> listAdmin(Pageable pageable);
    PageImpl<Object> listAlgo(Pageable pageable);
    PageImpl<Object> listSU(Pageable pageable);
    public Boolean deleteUserById(UUID id);
}
