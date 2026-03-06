package io.github.com.rasec23rj.locadora.repository;

import io.github.com.rasec23rj.locadora.entity.CarroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<CarroEntity, Long> {
    List<CarroEntity> findByModelo(String modelo);
}
