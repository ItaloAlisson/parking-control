package com.api.parkingcontrol.services;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import com.api.parkingcontrol.transform.ParkingSpotTranformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Transactional
    public ResponseEntity save(ParkingSpotDto parkingSpotDto) {
        List<String> conflictErrors = new ArrayList<>();
        if (parkingSpotRepository.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())) {
            conflictErrors.add("Conflict: License plate car is already in use!");
        }
        if (parkingSpotRepository.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
            conflictErrors.add("Conflict: Parking Spot is already in use!");
        }
        if (parkingSpotRepository.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())) {
            conflictErrors.add("Conflict: Parking Spot already registered for this apartment/block!");
        }
        if (!conflictErrors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(conflictErrors);
        }
        var parkingSpotModel = new ParkingSpotTranformer().tranformModel(parkingSpotDto);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        parkingSpotRepository.save(parkingSpotModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created");
    }

    public ResponseEntity findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotRepository.findAll(pageable));
    }


    public ResponseEntity findByid(UUID id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotRepository.findByid(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
    }

    @Transactional
    public ResponseEntity delete(UUID id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotRepository.findByid(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        parkingSpotRepository.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot deleted successfully");
    }

    public ResponseEntity put(UUID id, ParkingSpotDto parkingSpotDto) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotRepository.findByid(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        var parkingSpotModel = new ParkingSpotTranformer().tranformModel(parkingSpotDto);
        parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
        parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());
        parkingSpotRepository.save(parkingSpotModel);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully updated");

    }
}
