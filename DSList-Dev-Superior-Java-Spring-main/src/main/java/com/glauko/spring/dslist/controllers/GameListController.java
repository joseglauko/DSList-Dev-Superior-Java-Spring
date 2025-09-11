package com.glauko.spring.dslist.controllers;

import com.glauko.spring.dslist.dtos.GameListDTO;
import com.glauko.spring.dslist.dtos.GameMinDTO;
import com.glauko.spring.dslist.dtos.ReplacementDTO;
import com.glauko.spring.dslist.entities.GameList;
import com.glauko.spring.dslist.exceptions.GameListNotFoundException;
import com.glauko.spring.dslist.services.GameListService;
import com.glauko.spring.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
@CrossOrigin(origins = "http://localhost:4200") // Permitir requisições do Angular
public class GameListController {

    @Autowired
    private GameListService gameListService;
    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameListDTO> findAll() {
        return gameListService.findAll();
    }

    @GetMapping(value = "/{listId}/games")
    public List<GameMinDTO> findByListId(@PathVariable Long listId) throws GameListNotFoundException {
        return gameService.findByListId(listId);
    }

    @PostMapping(value = "/{listId}/replacement")
    public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) throws GameListNotFoundException {
        gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
    }

}
