package epitech.emergencity.Vehicle.services;

import epitech.emergencity.Vehicle.Vehicle;
import epitech.emergencity.Vehicle.dto.VehicleRequest;
import io.vavr.control.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface Vehicles {

    Vehicle create(VehicleRequest request);
    Option<Vehicle> getById(UUID id);
    Option<Vehicle> getByRegistration(String registration);
    Option<Vehicle> modById(UUID id, VehicleRequest request);
    Option<Vehicle> modByRegistration(String registration, VehicleRequest request);
    Boolean deleteById(UUID id);
    Boolean deleteByRegistration(String reg);
    Page<Vehicle> listVehicle(Pageable pageable);
}
