package com.glauko.spring.dslist.services;

//import org.springframework.stereotype.Component;
//import com.glauko.spring.dslist.dtos.GameMinDTO;
import com.glauko.spring.dslist.dtos.GameDTO;
import com.glauko.spring.dslist.dtos.GameListDTO;
import com.glauko.spring.dslist.dtos.GameMinDTO;
import com.glauko.spring.dslist.entities.Game;
import com.glauko.spring.dslist.exceptions.GameListNotFoundException;
import com.glauko.spring.dslist.exceptions.GameNotFoundException;
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
    public GameDTO findById(Long id) throws GameNotFoundException {

        if (id == 0) {
            throw new GameNotFoundException("Jogo não pode ter ID igual a 0");
        }
        else if (id < 0) {
            throw new GameNotFoundException("Jogo não pode ter ID negativo!");
        }

        Game game = gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException("Jogo de id " + id + " não existe!"));

        if (game == null) {
            throw new GameNotFoundException("Jogo de id " + id + "não existe!");
        }


        //Depois fazer tratamento de exceção pra tratar caso o ID não exista! Feito!

        return new GameDTO(game);
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByListId(Long listId) throws GameListNotFoundException {

        if (listId == 0) {
            throw new GameListNotFoundException("Lista não pode ter ID igual a 0");
        }
        else if (listId < 0) {
            throw new GameListNotFoundException("Lista não pode ter ID negativo!");
        }

        List<GameMinProjection> gameList = gameRepository.searchByListId(listId);

        if (gameList.isEmpty()) {
            throw new GameListNotFoundException("Lista de id " + listId + " não existe!");
        }
        else {
            return gameList.stream().map(game -> new GameMinDTO(game)).toList();
        }
    }

}
