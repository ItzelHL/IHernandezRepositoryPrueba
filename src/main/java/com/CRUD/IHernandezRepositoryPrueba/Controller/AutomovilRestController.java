package com.CRUD.IHernandezRepositoryPrueba.Controller;

import com.CRUD.IHernandezRepositoryPrueba.JPA.Automovil;
import com.CRUD.IHernandezRepositoryPrueba.JPA.Result;
import com.CRUD.IHernandezRepositoryPrueba.Service.AutomovilRepositoryImplementation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/automoviles")
public class AutomovilRestController 
{
    @Autowired
    private AutomovilRepositoryImplementation automovilRepositoryImplementation;
    
    @GetMapping
    public ResponseEntity GetAll()
    {
        Result result = automovilRepositoryImplementation.GetAll();
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("{IdAutomovil}")
    public ResponseEntity GetById(@PathVariable("IdAutomovil") int IdAutomovil)
    {
        Result result = automovilRepositoryImplementation.GetById(IdAutomovil);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("buscar")
    public ResponseEntity GetBy(@RequestParam(name = "noSerie", required = false) String noSerie,
                                                      @RequestParam(name = "marca", required = false) String marca)
    {
        if (noSerie != null && !noSerie.isBlank()) 
        {
            Result result = automovilRepositoryImplementation.GetByNoSerie(noSerie);
            return ResponseEntity.ok(result);
        }
        if (marca != null && !marca.isBlank()) 
        {
            Result result = automovilRepositoryImplementation.GetByMarca(marca);
            return ResponseEntity.ok(result);
        }
        return null;
    }
    
    @PostMapping("add")
    public ResponseEntity Add(@Valid @RequestBody Automovil automovil)
    {
        Result result = automovilRepositoryImplementation.Add(automovil);
        return ResponseEntity.ok(result);
    }
    
    @PutMapping("update/{IdAutomovil}") // Reemplaza todo
    public ResponseEntity Replace(@PathVariable("IdAutomovil") int IdAutomovil, @RequestBody Automovil automovil)
    {
        automovil.setIdAutomovil(IdAutomovil);
        Result result = automovilRepositoryImplementation.Replace(automovil);
        return ResponseEntity.ok(result);
    }
    
    @PatchMapping("up/{IdAutomovil}") // Reemplaza solo algo en especifico
    public ResponseEntity Update(@PathVariable("IdAutomovil") int IdAutomovil, @RequestBody Automovil automovil)
    {
        automovil.setIdAutomovil(IdAutomovil);
        Result result = automovilRepositoryImplementation.Update(automovil);
        return  ResponseEntity.ok(result);
    }
    
    @DeleteMapping("delete/{IdAutomovil}")
    public ResponseEntity Delete(@PathVariable("IdAutomovil") int IdAutomovil)
    {
        Result result = automovilRepositoryImplementation.Delete(IdAutomovil);
        return ResponseEntity.ok(result);
    }
}