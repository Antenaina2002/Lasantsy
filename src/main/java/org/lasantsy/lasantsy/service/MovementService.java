package org.lasantsy.lasantsy.service;

import org.lasantsy.lasantsy.models.Movement;
import org.lasantsy.lasantsy.repository.MovementRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovementService {

    private final MovementRepository movementRepository;

    public MovementService(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    public Movement saveMovement(Movement movement) {
        return movementRepository.save(movement);
    }

    public List<Movement> getAllMovements() {
        return movementRepository.findAll();
    }

    public Movement getMovementById(Long id) {
        return movementRepository.findById(id);
    }
}
