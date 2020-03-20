package epitech.emergencity.barrack.services;

import epitech.emergencity.barrack.Barrack;
import epitech.emergencity.barrack.dto.BarrackRequest;
import io.vavr.control.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface Barracks {
    Barrack create(BarrackRequest request);
    Option<Barrack> getById(UUID id);
    Boolean delete(UUID id);
    Page<Barrack> listBarracks(Pageable pageable);
    Option<Barrack> modById(UUID id, BarrackRequest request);
}
