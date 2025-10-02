package com.CRUD.IHernandezRepositoryPrueba.Service;

import com.CRUD.IHernandezRepositoryPrueba.JPA.Marca;
import com.CRUD.IHernandezRepositoryPrueba.JPA.Result;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class MarcaRepositoryImplementation 
{
    private final IMarcaRepository iMarcaRepository;
    
    public MarcaRepositoryImplementation(IMarcaRepository iMarcaRepository)
    {
        this.iMarcaRepository = iMarcaRepository;
    }
    
    public Result GetAll()
    {
        Result result = new Result();
        
        try 
        {
            result.object = iMarcaRepository.findAll();
            result.correct = true;
            
        } catch (Exception ex) 
        {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    
    public Result GetById(int idMarca)
    {
        Result result = new Result();
        
        try 
        {
            if (iMarcaRepository.existsById(idMarca)) 
            {
                result.object = iMarcaRepository.findById(idMarca);
                result.correct = true;
            }
        } catch (Exception ex) 
        {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    
    @Transactional
    public Result Add(Marca marca)
    {
        Result result = new Result();
        
        try 
        {
            result.object = iMarcaRepository.save(marca);
            result.correct = true;
            
        } catch (Exception ex) 
        {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    
    @Transactional
    public Result Update(Marca marca)
    {
        Result result = new Result();
        
        try 
        {
            if (iMarcaRepository.existsById(marca.getIdMarca())) 
            {
                result.object = iMarcaRepository.save(marca);
                result.correct = true;
            }  
            
        } catch (Exception ex) 
        {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    
    @Transactional
    public Result Delete(int idMarca)
    {
        Result result = new Result();
        
        try 
        {
            if (iMarcaRepository.existsById(idMarca)) 
            {
                iMarcaRepository.deleteById(idMarca);
                result.correct = true;
            }
            
        } catch (Exception ex) 
        {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
}