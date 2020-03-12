package epitech.emergencity.barrack.services;

import epitech.emergencity.barrack.BarrackUser;
import epitech.emergencity.barrack.BarrackVehicle;
import epitech.emergencity.barrack.dto.BarrackUserRequest;
import epitech.emergencity.barrack.dto.BarrackVehicleRequest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BarrackVehicles {
    BarrackVehicle create(BarrackVehicleRequest request);
    Boolean delete(BarrackVehicleRequest request);
    PageImpl<Object> listUser(UUID id, Pageable pageable);
}
