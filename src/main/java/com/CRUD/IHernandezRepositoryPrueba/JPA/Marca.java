package com.CRUD.IHernandezRepositoryPrueba.JPA;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "marca")
public class Marca 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmarca")
    private int idMarca;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "modelo")
    private String modelo;
    
    public Marca(){}

    public Marca(int idMarca, String nombre, String modelo, List<Automovil> automoviles) 
    {
        this.idMarca = idMarca;
        this.nombre = nombre;
        this.modelo = modelo;
    }
    
    public void setIdMarca(int idMarca) 
    {
        this.idMarca = idMarca;
    }
    public int getIdMarca() 
    {
        return idMarca;
    }
    
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    public String getNombre()
    {
        return nombre;
    }
    
    public void setModelo(String modelo)
    {
        this.modelo = modelo;
    }
    public String getModelo()
    {
        return modelo;
    }
}
