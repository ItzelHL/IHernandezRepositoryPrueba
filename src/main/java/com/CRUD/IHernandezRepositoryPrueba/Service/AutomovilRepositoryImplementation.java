package com.CRUD.IHernandezRepositoryPrueba.Service;

import com.CRUD.IHernandezRepositoryPrueba.JPA.Automovil;
import com.CRUD.IHernandezRepositoryPrueba.JPA.Result;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AutomovilRepositoryImplementation
{
    private final IAutomovilRepository iAutomovilRepository;

    public AutomovilRepositoryImplementation(IAutomovilRepository iAutomovilRepository)
    {
        this.iAutomovilRepository = iAutomovilRepository;
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
    
    public Result GetById(int idAutomovil)
    {
        Result result = new Result();
        
        try 
        {
            Optional<Automovil> optionalAuto = iAutomovilRepository.findById(idAutomovil);
            if (optionalAuto.isPresent()) 
            {
                result.object = optionalAuto.get();
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
    
    public Result GetByNoSerie(String noSerie)
    {
        Result result = new Result();
        
        try 
        {
            if (iAutomovilRepository.existsByNoSerie(noSerie)) 
            {
                result.object = iAutomovilRepository.findAutomovilByNoSerie(noSerie);
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
    
    public Result GetByMarca(String marca)
    {
        Result result = new Result();
        
        try 
        {
            result.object = iAutomovilRepository.findAutomovilByMarca_nombre(marca);
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
    public Result Add(Automovil automovil)
    {
        Result result = new Result();
        
        try 
        {
            if (!(iAutomovilRepository.existsByNoSerie(automovil.getNoSerie()))) 
            {
                Automovil auto = iAutomovilRepository.save(automovil);
                result.object = auto;
                result.correct = true;
            }
            
        } catch (Exception ex) 
        {
         result.correct = false;
         result.errorMessage = ex.getLocalizedMessage();
         result.ex = ex;
        }    
        return  result;
    }
    
    @Transactional
    public Result Replace(Automovil automovil)
    {
        Result result = new Result();
        
        try 
        {
            result.object = iAutomovilRepository.save(automovil);
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
    public Result Update(Automovil automovil)
    {
        Result result = new Result();       
        try 
        {
            Optional<Automovil> automov = iAutomovilRepository.findById(automovil.getIdAutomovil());
            if (automov.isPresent()) 
            {
                Automovil auto = automov.get();
                
                auto.setColor(automovil.getColor());
                result.object = iAutomovilRepository.save(auto);
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
    public Result Delete(int idAutomovil)
    {
        Result result = new Result();     
        try 
        {
            iAutomovilRepository.deleteById(idAutomovil);
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