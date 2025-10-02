package com.CRUD.IHernandezRepositoryPrueba.JPA;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "automovil")
public class Automovil 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idautomovil")
    private int idAutomovil;
    
    @NotBlank
    @Max(17)
    @Column(name = "noserie")
    private String noSerie;
    
    @NotBlank
    @Column(name = "modelo")
    private String modelo;
    
    @NotBlank
    @Column(name = "anio")
    private String anio;
    
    @NotBlank
    @Column(name = "color")
    private String color;
    
    @NotBlank
    @Column(name = "carroceria")
    private String carroceria;
    
    @NotBlank
    @Column(name = "motor")
    private String motor;
    
    @NotBlank
    @Column(name = "potencia")
    private String potencia;
    
    @NotBlank
    @ManyToOne
    @JoinColumn(name = "idmarca")
    public Marca marca;
    
    
    public Automovil(){}
    
    public Automovil(int idAutomovil, String noSerie, String modelo, String anio, String color, String carroceria, String motor, String potencia, Marca marca)
    {
        this.idAutomovil = idAutomovil;
        this.noSerie = noSerie;
        this.modelo = modelo;
        this.anio = anio;
        this.color = color;
        this.carroceria = carroceria;
        this.motor = motor;
        this.potencia = potencia;
        this.marca = marca;
    }
    
    public void setIdAutomovil(int idAutomovil)
    {
        this.idAutomovil = idAutomovil;
    }
    public int getIdAutomovil()
    {
        return idAutomovil;
    }
    
    public void setNoSerie(String noSerie)
    {
        this.noSerie = noSerie;
    }
    public String getNoSerie()
    {
        return noSerie;
    }
    
    public void setModelo(String modelo)
    {
        this.modelo = modelo;
    }
    public String getModelo()
    {
        return modelo;
    }
    
    public void setAnio(String anio)
    {
        this.anio = anio;
    }
    public String getAnio()
    {
        return anio;
    }

    public void setColor(String color) 
    {
        this.color = color;
    }
    public String getColor()
    {
        return color;
    }

    public void setCarroceria(String carroceria) 
    {
        this.carroceria = carroceria;
    }
    public String getCarroceria() 
    {
        return carroceria;
    }

    public void setMotor(String motor) 
    {
        this.motor = motor;
    }
    public String getMotor() 
    {
        return motor;
    }

    public void setPotencia(String potencia) 
    {
        this.potencia = potencia;
    }
    public String getPotencia() 
    {
        return potencia;
    }

    public void setMarca(Marca marca)
    {
        this.marca = marca;
    }
    public Marca getMarca() 
    {
        return marca;
    }
}
