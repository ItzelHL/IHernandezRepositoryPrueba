package com.CRUD.IHernandezRepositoryPrueba.Service;

import com.CRUD.IHernandezRepositoryPrueba.JPA.Automovil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAutomovilRepository extends JpaRepository<Automovil, String>
{
//    Automovil findAutomovilByIdMarca(String idmarca);
}
