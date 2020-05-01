package epitech.emergencity.profils.services;

import epitech.emergencity.profils.Profil;
import epitech.emergencity.profils.dto.ProfilRequest;
import io.vavr.control.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface Profils {
    Profil create(ProfilRequest request);
    Option<Profil> update(UUID id, ProfilRequest request);
    Option<Profil> getByIdAndUserId(UUID id, UUID user_id);
    Page<Profil> listProfils(UUID user_id, Pageable pageable);
    public Boolean delete(UUID id);
}
