package com.glauko.spring.dslist.repositories;

import com.glauko.spring.dslist.entities.GameList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameListRepository extends JpaRepository<GameList, Long> {

}
