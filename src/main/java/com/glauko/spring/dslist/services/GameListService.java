package com.glauko.spring.dslist.services;

import com.glauko.spring.dslist.dtos.GameListDTO;
import com.glauko.spring.dslist.entities.GameList;
import com.glauko.spring.dslist.repositories.GameListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    public List<GameListDTO> findAll() {
        return gameListRepository.findAll().stream().map(gameList -> new GameListDTO(gameList)).collect(Collectors.toList());
    }

}
