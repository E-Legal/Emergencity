package epitech.emergencity.barrack.services;

import epitech.emergencity.barrack.BarrackUser;
import epitech.emergencity.barrack.dto.BarrackUserRequest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BarrackUsers {
    BarrackUser create(UUID barrack_id, UUID user_idt);
    Boolean delete(UUID barrack_id, UUID user_id);
    PageImpl<Object> listUser(UUID id, Pageable pageable);
    PageImpl<Object> listBarrack(UUID id, Pageable pageable);
}
