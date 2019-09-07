package com.example.ProjekatIsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.model.SeatArrangement;

@RestController
@RequestMapping(value = "/seatArrangement")
public class SeatArrangementController {
	
	@Autowired
    private com.example.ProjekatIsa.service.SeatArrangementService SeatArrangementService;

    @GetMapping
    private ResponseEntity<List<SeatArrangement>> getSeatArrangements(){
        List<SeatArrangement> airplaneSeatArrangements = this.SeatArrangementService.getAllSeatArrangements();
        if (airplaneSeatArrangements == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(airplaneSeatArrangements);
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<SeatArrangement> getSeatArrangement(@PathVariable Long id){
        SeatArrangement airplaneSeatArrangement = this.SeatArrangementService.getSeatArrangementById(id);
        if (airplaneSeatArrangement == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(airplaneSeatArrangement);
    }

}
