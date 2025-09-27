package com.CRUD.IHernandezRepositoryPrueba.Service;

import com.CRUD.IHernandezRepositoryPrueba.JPA.Automovil;
import com.CRUD.IHernandezRepositoryPrueba.JPA.Marca;
import com.CRUD.IHernandezRepositoryPrueba.JPA.Result;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AutomovilRepositoryImplementation
{
    private final IAutomovilRepository iAutomovilRepository;
    private final IMarcaRepository iMarcaRepository;

    public AutomovilRepositoryImplementation(IAutomovilRepository iAutomovilRepository, IMarcaRepository iMarcaRepository)
    {
        this.iAutomovilRepository = iAutomovilRepository;
        this.iMarcaRepository = iMarcaRepository;
    }
    
    public Result GetAll()
    {
        Result result = new Result();
        
        try 
        {
            List<Automovil> automoviles = iAutomovilRepository.findAll();
            result.object = automoviles;
            result.correct = true;
            
        } catch (Exception ex) 
        {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    
    public Result Add(Automovil automovil)
    {
        Result result = new Result();
        
        try 
        {
            if (true) {
                
            }

            result.correct = true;
            
        } catch (Exception ex) 
        {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
}
