package com.glauko.spring.dslist.controllers;

import com.glauko.spring.dslist.dtos.GameDTO;
import com.glauko.spring.dslist.dtos.GameMinDTO;
import com.glauko.spring.dslist.entities.Game;
import com.glauko.spring.dslist.exceptions.GameNotFoundException;
import com.glauko.spring.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/games")
@CrossOrigin(origins = "http://localhost:4200") // Permitir requisições do Angular
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameMinDTO> findAll() {
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    public GameDTO findById(@PathVariable Long id) throws GameNotFoundException {return gameService.findById(id);}



}
