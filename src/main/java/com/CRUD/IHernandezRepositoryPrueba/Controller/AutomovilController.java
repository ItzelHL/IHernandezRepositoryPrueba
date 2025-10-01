package com.CRUD.IHernandezRepositoryPrueba.Controller;

import com.CRUD.IHernandezRepositoryPrueba.JPA.Automovil;
import com.CRUD.IHernandezRepositoryPrueba.JPA.Marca;
import com.CRUD.IHernandezRepositoryPrueba.JPA.Result;
import com.CRUD.IHernandezRepositoryPrueba.Service.AutomovilRepositoryImplementation;
import com.CRUD.IHernandezRepositoryPrueba.Service.MarcaRepositoryImplementation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("automovil")
public class AutomovilController 
{  
    @Autowired
    private AutomovilRepositoryImplementation automovilImplementation;
    @Autowired
    private MarcaRepositoryImplementation marcaImplementation;
    
//    -------------------- CON REST CONTROLLER --------------------
//    GetAll - Index
    @GetMapping
    public String Index(Model model)
    {
        RestTemplate restTemplate = new RestTemplate();
        
        ResponseEntity<Result<List<Automovil>>> responseAuto = restTemplate.exchange("http://localhost:8080/api/automoviles",
                                                                                                                                                      HttpMethod.GET, HttpEntity.EMPTY,
                                                                                                                                                      new ParameterizedTypeReference<Result<List<Automovil>>>(){});
        if (responseAuto.getStatusCode() == HttpStatusCode.valueOf(200)) 
        {
            Result<List<Automovil>> resultAuto = responseAuto.getBody();
            
            model.addAttribute("autoBusqueda", new Automovil());
            
            if (resultAuto != null && resultAuto.correct) 
            {
                model.addAttribute("automoviles", resultAuto.object);
            }
            else
            {
                model.addAttribute("automoviles", null);
            }
        }
        
        ResponseEntity<Result<List<Marca>>> responseMarca = restTemplate.exchange("http://localhost:8080/api/marca", 
                                                                                                                                                HttpMethod.GET, HttpEntity.EMPTY, 
                                                                                                                                                new ParameterizedTypeReference<Result<List<Marca>>>(){});
        if (responseMarca.getStatusCode() == HttpStatusCode.valueOf(200)) 
        {
            Result<List<Marca>> resultMarca = responseMarca.getBody();
            model.addAttribute("marcas", resultMarca.object);
        }
        return "Index";
    }
    
//    GetByNoSerie y GetByMarca - Búsqueda por marca y por número de serie
    @PostMapping
    public String Index(Model model, @ModelAttribute("autoBusqueda") Automovil autoBusqueda)
    {
        RestTemplate restTemplate = new RestTemplate();
        
        if (autoBusqueda.getNoSerie() != null && !autoBusqueda.getNoSerie().isBlank()) 
        {
            ResponseEntity<Result<Automovil>> responseAuto = restTemplate.exchange("http://localhost:8080/api/automoviles/buscar?noSerie=" + autoBusqueda.getNoSerie(),
                                                                                                                                    HttpMethod.GET, HttpEntity.EMPTY, 
                                                                                                                                    new ParameterizedTypeReference<Result<Automovil>>() {});
            if (responseAuto.getStatusCode() == HttpStatusCode.valueOf(200)) 
            {
                Result<Automovil> resultAuto = responseAuto.getBody();
                if (responseAuto != null && resultAuto.correct) 
                {
                    model.addAttribute("automoviles",  resultAuto.object);
                }
            }
        }
        if (autoBusqueda.getMarca() != null && autoBusqueda.getMarca().getNombre() != null && !autoBusqueda.getMarca().getNombre().isBlank())  
        {
            ResponseEntity<Result<List<Automovil>>> responseAuto = restTemplate.exchange("http://localhost:8080/api/automoviles/buscar?marca=" + autoBusqueda.getMarca().getNombre(), 
                                                                                                                                                          HttpMethod.GET, HttpEntity.EMPTY,
                                                                                                                                                          new ParameterizedTypeReference<Result<List<Automovil>>>(){});
            if (responseAuto.getStatusCode() == HttpStatusCode.valueOf(200)) 
            {
                Result<List<Automovil>> resultAuto = responseAuto.getBody();
                if (resultAuto != null && resultAuto.correct) 
                {
                    model.addAttribute("automoviles", resultAuto.object);
                }
            }
        }
        
        ResponseEntity<Result<List<Marca>>> responseMarca = restTemplate.exchange("http://localhost:8080/api/marca", 
                                                                                                                                            HttpMethod.GET, HttpEntity.EMPTY, 
                                                                                                                                            new ParameterizedTypeReference<Result<List<Marca>>>(){});
        if(responseMarca.getStatusCode() == HttpStatusCode.valueOf(200))
        {
            Result<List<Marca>> resultMarca = responseMarca.getBody();
            if (resultMarca != null && resultMarca.correct) 
            {
                model.addAttribute("marcas", resultMarca.object);
            }
        }
        
        model.addAttribute("autoBusqueda", autoBusqueda);
        
        return "Index";
    }
    
//     Detail y Form - Trae la vista para el detalle del automóvil y para Agregar un nuevo automóvil
    @GetMapping("form/{IdAutomovil}")
    public String Form(Model model, @PathVariable("IdAutomovil") int IdAutomovil) 
    {
        RestTemplate restTemplate = new RestTemplate();
        
        if (IdAutomovil == 0) 
        {
            model.addAttribute("automovil", new Automovil());
            
            ResponseEntity<Result<List<Marca>>> responseMarca = restTemplate.exchange("http://localhost:8080/api/marca",
                                                                                                                            HttpMethod.GET, HttpEntity.EMPTY,
                                                                                                                 new ParameterizedTypeReference<Result<List<Marca>>>(){});
            if (responseMarca.getStatusCode() == HttpStatusCode.valueOf(200)) 
            {
                Result<List<Marca>> resultMarca = responseMarca.getBody();
                if (resultMarca != null && resultMarca.correct) 
                {
                    model.addAttribute("marcas", resultMarca.object);
                }
            }
            return "Form";
        }
        else
        {
            ResponseEntity<Result<Automovil>> responseAuto = restTemplate.exchange("http://localhost:8080/api/automoviles/" + IdAutomovil, 
                                                                                                                                    HttpMethod.GET, HttpEntity.EMPTY, 
                                                                                                                                    new ParameterizedTypeReference<Result<Automovil>>(){});
            if (responseAuto.getStatusCode() == HttpStatusCode.valueOf(200)) 
            {
                Result<Automovil> resultAuto = responseAuto.getBody();
                if (resultAuto != null && resultAuto.correct) 
                {
                    model.addAttribute("automovil", resultAuto.object);
                }
            }
            
            ResponseEntity<Result<List<Marca>>> responseMarca = restTemplate.exchange("http://localhost:8080/api/marca",
                                                                                                                            HttpMethod.GET, HttpEntity.EMPTY,
                                                                                                            new ParameterizedTypeReference<Result<List<Marca>>>() {});
            if (responseMarca.getStatusCode() == HttpStatusCode.valueOf(200)) 
            {
                Result<List<Marca>> resultMarca = responseMarca.getBody();
                if (resultMarca != null && resultMarca.correct) 
                {
                    model.addAttribute("marcas", resultMarca.object);
                }
            }
            return "Detail";
        }
    }
    
//     Form - Trae la vista para editar un automóvil
    @GetMapping("update")
    public String Update(Model model, @RequestParam("idAutomovil") int IdAutomovil)
    {
        RestTemplate restTemplate = new RestTemplate();
        
        ResponseEntity<Result<Automovil>> responseAuto = restTemplate.exchange("http://localhost:8080/api/automoviles/" + IdAutomovil, 
                                                                                                                                            HttpMethod.GET, HttpEntity.EMPTY,
                                                                                                                                            new ParameterizedTypeReference<Result<Automovil>>(){});
        if(responseAuto.getStatusCode() == HttpStatusCode.valueOf(200))
        {
            Result<Automovil> resultAuto = responseAuto.getBody();
            if(resultAuto != null && resultAuto.correct)
            {
                model.addAttribute("automovil", resultAuto.object);
            }
            else
            {
                return "redirect:/automovil";
            }
        }
        else
        {
            return "redirect:/automovil";
        }
        
        ResponseEntity<Result<List<Marca>>> responseMarca = restTemplate.exchange("http://localhost:8080/api/marca", 
                                                                                                                      HttpMethod.GET, HttpEntity.EMPTY, 
                                                                                                     new ParameterizedTypeReference<Result<List<Marca>>>(){});
        if (responseMarca.getStatusCode() == HttpStatusCode.valueOf(200)) 
        {
            Result<List<Marca>> resultMarca = responseMarca.getBody();
            if (resultMarca != null && resultMarca.correct) 
            {
                model.addAttribute("marcas", resultMarca.object);
            }
        }
        return "Form";
    }
    
//     Add y Update - Agrega y actualiza un automóvil
    @PostMapping("form")
    public String Form(Model model, @ModelAttribute("automovil") Automovil automovil)
    {
        RestTemplate restTemplate = new RestTemplate();
        if (automovil.getIdAutomovil() == 0) 
        {
            HttpEntity<Automovil> entity = new HttpEntity<>(automovil);
            ResponseEntity<Result<Automovil>> responseAuto = restTemplate.exchange("http://localhost:8080/api/automoviles/add", 
                                                                                                        HttpMethod.POST, entity, 
                                                                                                        new ParameterizedTypeReference<Result<Automovil>>(){});
            if (responseAuto.getStatusCode() == HttpStatusCode.valueOf(200)) 
            {
                Result<Automovil> resultAuto = responseAuto.getBody();
                if (resultAuto != null && resultAuto.correct) 
                {
                    return "redirect:/automovil";
                }
                else
                {
                    model.addAttribute("automovil", automovil);
                }
            }         
        }
        else
        {
            HttpEntity<Automovil> entity = new HttpEntity<>(automovil);
            ResponseEntity<Result<Automovil>> responseAuto = restTemplate.exchange("http://localhost:8080/api/automoviles/update/" + automovil.getIdAutomovil(), 
                                                                                                        HttpMethod.PUT, entity, 
                                                                                                        new ParameterizedTypeReference<Result<Automovil>>(){});
             if (responseAuto.getStatusCode() == HttpStatusCode.valueOf(200)) 
                {
                    Result<Automovil> resultAuto = responseAuto.getBody();
                    if (resultAuto != null && resultAuto.correct) 
                    {
                        return "redirect:/automovil/form/" + automovil.getIdAutomovil();
                    }
                }
        }
        return "Form";
    }
    
//    Delete - Elimina un automóvil y retorna la misma vista de Automoviles
    @GetMapping("delete/{IdAutomovil}")
    public String Delete(Model model, @PathVariable("IdAutomovil") int IdAutomovil)
    {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Result<Automovil>> responseAuto = restTemplate.exchange("http://localhost:8080/api/automoviles/delete/" + IdAutomovil, 
                                                                                                                            HttpMethod.DELETE, HttpEntity.EMPTY, 
                                                                                                                    new ParameterizedTypeReference<Result<Automovil>>() {});
        if (responseAuto.getStatusCode() == HttpStatusCode.valueOf(200)) 
        {
            Result<Automovil> resultAuto = responseAuto.getBody();
            if (resultAuto != null && resultAuto.correct) 
            {
                return "redirect:/automovil";
            }
        }
        return "redirect:/automovil";
    }
    
    
//    -------------------- SIN REST CONTROLLER --------------------
    // GetAll - Index
//    @GetMapping
//    public String Index(Model model)
//    {
//        Result resultAuto = automovilImplementation.GetAll();
//        if (resultAuto.correct) 
//        {
//            model.addAttribute("automoviles", (List<Automovil>) resultAuto.object);
//        }
//        
//        Result resultMarca = marcaImplementation.GetAll();
//        if (resultMarca.correct) 
//        {
//            model.addAttribute("marcas", (List<Marca>) resultMarca.object);
//        }
//
//        model.addAttribute("autoBusqueda", new Automovil());
//        return "Index";
//    }
//    
//    // GetByNoSerie y GetByMarca - Búsqueda por marca y por número de serie
//    @PostMapping
//    public String Index(Model model, @ModelAttribute("autoBusqueda") Automovil autoBusqueda)
//    {
//        List<Automovil> automoviles = new ArrayList<>();
//        Result resultAuto = automovilImplementation.GetAll();
//        if (resultAuto.correct) 
//        {
//            automoviles = (List<Automovil>) resultAuto.object;
//        }
//        
//        if(autoBusqueda.getNoSerie() != null && !autoBusqueda.getNoSerie().isBlank()) 
//        {
//            String noSerie = autoBusqueda.getNoSerie();
//            automoviles = automoviles.stream().filter(auto -> auto.getNoSerie() != null && auto.getNoSerie().equalsIgnoreCase(noSerie)).collect(Collectors.toList());
//        }
//        
//        if (autoBusqueda.getMarca() != null && autoBusqueda.getMarca().getNombre() != null && !autoBusqueda.getMarca().getNombre().isBlank()) 
//        {
//            String marca = autoBusqueda.getMarca().getNombre();
//            automoviles = automoviles.stream().filter(auto -> auto.getMarca()!= null && auto.getMarca().getNombre() != null && auto.getMarca().getNombre().equalsIgnoreCase(marca)).collect(Collectors.toList());
//        }
//        
//        Result resultMarca = marcaImplementation.GetAll();
//        if (resultMarca.correct) 
//        {
//            model.addAttribute("marcas", (List<Marca>) resultMarca.object);
//        }
//        
//        model.addAttribute("autoBusqueda", autoBusqueda);
//        model.addAttribute("automoviles", automoviles);
//        return "Index";
//    }
//    
//    // Detail y Form - Trae la vista para el detalle del automóvil y para Agregar un nuevo automóvil
//    @GetMapping("form/{IdAutomovil}")
//    public String Form(Model model, @PathVariable("IdAutomovil") int IdAutomovil)
//    {
//        List<Marca> marcas = new ArrayList<>();
//        if (IdAutomovil == 0) 
//        {
//            Result result = marcaImplementation.GetAll();
//            if(result.correct)
//            {
//                model.addAttribute("automovil", new Automovil());
//                model.addAttribute("marcas", (List<Marca>) result.object);
//            }
//            return "Form";
//        }
//        else
//        {
//            Result resultAuto = automovilImplementation.GetById(IdAutomovil);
//            Result resultMarca = marcaImplementation.GetAll();
//            if (resultAuto.correct && resultAuto.object != null) 
//            {
//                model.addAttribute("automovil", (Automovil) resultAuto.object);
//                model.addAttribute("marcas", (List<Marca>) resultMarca.object);
//            }
//            return "Detail";
//        }
//    }
//    
//    // Form - Trae la vista para editar un automóvil
//    @GetMapping("update")
//    public String Update(Model model, @RequestParam("idAutomovil") int IdAutomovil)
//    {
//        Result resultAuto = automovilImplementation.GetById(IdAutomovil);
//        Result resultMarca = marcaImplementation.GetAll();
//        if (resultAuto.correct && resultAuto.object != null) 
//        {
//            model.addAttribute("automovil", (Automovil) resultAuto.object);
//            model.addAttribute("marcas", (List<Marca>) resultMarca.object);
//        }    
//        return "Form";
//    }
//    
//    // Add y Update - Agrega y actualiza un automóvil
//    @PostMapping("form")
//    public String Form(Model model, @ModelAttribute("automovil") Automovil automovil)
//    {
//        if (automovil.getMarca() == null) 
//        {
//            automovil.setMarca(new Marca());
//        }
//        
//        if (automovil.getIdAutomovil() == 0) 
//        {
//            model.addAttribute("automovil", new Automovil());
//            Result result = automovilImplementation.Add(automovil);
//            if (result.correct) 
//            {
//                model.addAttribute("automovil", result.object);
//            }
//            return "redirect:/automovil";
//        }
//        else
//        {
//            automovil.setIdAutomovil(automovil.getIdAutomovil());
//            Result result = automovilImplementation.Replace(automovil);
//            if (result.correct) 
//            {
//                model.addAttribute("automovil", result.object);
//            }
//            
//            return "redirect:/automovil/form/" + automovil.getIdAutomovil();
//        }
//    }
//    
//    @GetMapping("delete/{IdAutomovil}")
//    public String Delete(Model model, @PathVariable("IdAutomovil") int IdAutomovil)
//    {
//        model.addAttribute("automovil", automovilImplementation.Delete(IdAutomovil));
//        return "redirect:/automovil";
//    }
}
