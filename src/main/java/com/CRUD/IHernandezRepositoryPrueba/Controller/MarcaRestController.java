package com.CRUD.IHernandezRepositoryPrueba.Controller;

import com.CRUD.IHernandezRepositoryPrueba.JPA.Marca;
import com.CRUD.IHernandezRepositoryPrueba.JPA.Result;
import com.CRUD.IHernandezRepositoryPrueba.Service.MarcaRepositoryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/marca")
public class MarcaRestController 
{
    @Autowired
    private MarcaRepositoryImplementation marcaRepositoryImplementation;
    
    @GetMapping
    public ResponseEntity GetAll()
    {
        Result result = marcaRepositoryImplementation.GetAll();
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("{IdMarca}")
    public ResponseEntity GetById(@PathVariable("IdMarca") int IdMarca)
    {
        Result result = marcaRepositoryImplementation.GetById(IdMarca);
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("addmarca")
    public ResponseEntity Add(@RequestBody Marca marca)
    {
        Result result = marcaRepositoryImplementation.Add(marca);
        return ResponseEntity.ok(result);
    }
    
    @PutMapping("updatemarca/{IdMarca}")
    public ResponseEntity Update(@PathVariable("IdMarca") int IdMarca, @RequestBody Marca marca)
    {
        marca.setIdMarca(IdMarca);
        Result result = marcaRepositoryImplementation.Update(marca);
        return ResponseEntity.ok(result);
    }
    
    @DeleteMapping("deletemarca/{IdMarca}")
    public ResponseEntity Delete(@PathVariable("IdMarca") int IdMarca)
    {
        Result result = marcaRepositoryImplementation.Delete(IdMarca);
        return ResponseEntity.ok(result);
    }
}
