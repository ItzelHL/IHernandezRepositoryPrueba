package com.CRUD.IHernandezRepositoryPrueba.Service;

import com.CRUD.IHernandezRepositoryPrueba.JPA.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMarcaRepository extends JpaRepository<Marca, Integer>
{
    
}