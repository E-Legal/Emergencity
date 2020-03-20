package epitech.emergencity.barrack.services;

import epitech.emergencity.barrack.BarrackUser;
import epitech.emergencity.barrack.dto.BarrackUserRequest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BarrackUsers {
    BarrackUser create(BarrackUserRequest request);
    Boolean delete(BarrackUserRequest request);
    PageImpl<Object> listUser(UUID id, Pageable pageable);
}
