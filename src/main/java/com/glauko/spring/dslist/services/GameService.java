package com.glauko.spring.dslist.services;

//import org.springframework.stereotype.Component;
//import com.glauko.spring.dslist.dtos.GameMinDTO;
import com.glauko.spring.dslist.dtos.GameMinDTO;
import com.glauko.spring.dslist.entities.Game;
import com.glauko.spring.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Component
@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GameMinDTO> findAll() {
        List<Game> gameList = gameRepository.findAll();
        return gameList.stream().map(game -> new GameMinDTO(game)).toList();
    }

}
