package com.glauko.spring.dslist.services;

import com.glauko.spring.dslist.dtos.GameListDTO;
import com.glauko.spring.dslist.exceptions.GameListNotFoundException;
import com.glauko.spring.dslist.projections.GameMinProjection;
import com.glauko.spring.dslist.repositories.GameListRepository;
import com.glauko.spring.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        return gameListRepository.findAll().stream().map(GameListDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public void move(Long gameListId, int sourceIndex, int destinationIndex) throws GameListNotFoundException, IndexOutOfBoundsException {

        if (gameListId == 0) {
            throw new GameListNotFoundException("Lista não pode ter ID igual a 0");
        }
        else if (gameListId < 0) {
            throw new GameListNotFoundException("Lista não pode ter ID negativo!");
        }

        List<GameMinProjection> list = gameRepository.searchByListId(gameListId);

        if (list.isEmpty()) {
            throw new GameListNotFoundException("Lista de id " + gameListId + " não existe!");
        }

        GameMinProjection obj = list.remove(sourceIndex);
        list.add(destinationIndex, obj);

        int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
//        int min = Math.min(sourceIndex, destinationIndex);
        int max = sourceIndex > destinationIndex ? sourceIndex : destinationIndex;
//        int max = Math.max(sourceIndex, destinationIndex);

        for (int i = min; i <= max; i++) {
            gameListRepository.updateBelongingPosition(gameListId, list.get(i).getId(), i);
        }
    }

}
