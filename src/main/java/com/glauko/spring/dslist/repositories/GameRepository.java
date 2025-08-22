package com.glauko.spring.dslist.repositories;

import com.glauko.spring.dslist.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

}
