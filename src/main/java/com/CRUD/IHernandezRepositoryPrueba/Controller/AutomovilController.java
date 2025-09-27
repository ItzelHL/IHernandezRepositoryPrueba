package com.CRUD.IHernandezRepositoryPrueba.Controller;

import com.CRUD.IHernandezRepositoryPrueba.JPA.Automovil;
import com.CRUD.IHernandezRepositoryPrueba.JPA.Result;
import com.CRUD.IHernandezRepositoryPrueba.Service.AutomovilRepositoryImplementation;
import com.CRUD.IHernandezRepositoryPrueba.Service.IAutomovilRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/auto")
public class AutomovilController 
{
    @Autowired
    private AutomovilRepositoryImplementation automovilRepositoryImplementation;
    
    @GetMapping
    public Result GetAll()
    {
        return automovilRepositoryImplementation.GetAll();
    }
}
