package com.glauko.spring.dslist.services;

//import org.springframework.stereotype.Component;
//import com.glauko.spring.dslist.dtos.GameMinDTO;
import com.glauko.spring.dslist.dtos.GameDTO;
import com.glauko.spring.dslist.dtos.GameListDTO;
import com.glauko.spring.dslist.dtos.GameMinDTO;
import com.glauko.spring.dslist.entities.Game;
import com.glauko.spring.dslist.projections.GameMinProjection;
import com.glauko.spring.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    @Transactional(readOnly = true)
    public GameDTO findById(Long id) {
        Game game = gameRepository.findById(id).get();
        //Depois fazer tratamento de exceção pra tratar caso o ID não exista!
        return new GameDTO(game);
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId) {
        List<GameMinProjection> gameList = gameRepository.searchByList(listId);
        return gameList.stream().map(game -> new GameMinDTO(game)).toList();
    }

}
