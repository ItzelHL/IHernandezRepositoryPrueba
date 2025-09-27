package com.CRUD.IHernandezRepositoryPrueba.JPA;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "automovil")
public class Automovil 
{
    @Id
    @Column(name = "noserie")
    private String noSerie;
    
    @Column(name = "anio")
    private String anio;
    
    @Column(name = "color")
    private String color;
    
    @Column(name = "carroceria")
    private String carroceria;
    
    @Column(name = "asientos")
    private String asientos;
    
    @Column(name = "motor")
    private String motor;
    
    @Column(name = "potencia")
    private String potencia;
    
    @Column(name = "tiposfrenos")
    private String tiposFrenos;
    
    @ManyToOne
    @JoinColumn(name = "idmarca")
    private Marca marca;
    
    
    public Automovil(){}
    
    public Automovil(String noSerie, String anio, String color, String carroceria, String asientos, String motor, String potencia, String tiposfrenos, Marca marca)
    {
        this.noSerie = noSerie;
        this.anio = anio;
        this.color = color;
        this.carroceria = carroceria;
        this.asientos = asientos;
        this.motor = motor;
        this.potencia = potencia;
        this.tiposFrenos = tiposfrenos;
        this.marca = marca;
    }
    
    public void setNoSerie(String noSerie)
    {
        this.noSerie = noSerie;
    }
    public String getNoSerie()
    {
        return noSerie;
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

    public void setAsientos(String asientos) 
    {
        this.asientos = asientos;
    }
    public String getAsientos() 
    {
        return asientos;
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

    public void setTiposFrenos(String tiposFrenos) 
    {
        this.tiposFrenos = tiposFrenos;
    }
    public String getTiposFrenos() 
    {
        return tiposFrenos;
    }
}
