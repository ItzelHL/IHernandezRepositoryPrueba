package com.CRUD.IHernandezRepositoryPrueba.Service;

import com.CRUD.IHernandezRepositoryPrueba.JPA.Automovil;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAutomovilRepository extends JpaRepository<Automovil, Integer>
{
    boolean existsByNoSerie(String noSerie);
    Optional<Automovil> findAutomovilByNoSerie(String noSerie);
    List<Automovil> findAutomovilByMarca_nombre(String marca);
}
