package com.CRUD.IHernandezRepositoryPrueba.JPA;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    
    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    public List<Automovil> automoviles = new ArrayList<>();
    
    public Marca(){}

    public Marca(int idMarca, String nombre, String modelo, List<Automovil> automoviles) 
    {
        this.idMarca = idMarca;
        this.nombre = nombre;
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
}