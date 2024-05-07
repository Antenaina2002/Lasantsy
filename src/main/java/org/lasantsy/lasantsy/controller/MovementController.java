package org.lasantsy.lasantsy.controller;

import org.lasantsy.lasantsy.models.Movement;
import org.lasantsy.lasantsy.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movements")
public class MovementController {

    @Autowired
    private MovementService movementService;

    @PostMapping
    public Movement createMovement(@RequestBody Movement movement) {
        return movementService.saveMovement(movement);
    }

    @GetMapping
    public List<Movement> getAllMovements() {
        return movementService.getAllMovements();
    }

    @GetMapping("/{id}")
    public Movement getMovementById(@PathVariable Long id) {
        return movementService.getMovementById(id);
    }
}
