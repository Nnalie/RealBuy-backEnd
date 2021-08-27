package com.leonardoelian.ecommerceAPI.resources;

import com.leonardoelian.ecommerceAPI.domain.Categoria;
import com.leonardoelian.ecommerceAPI.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService catServ;

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<Categoria> obj = catServ.buscarTodos();

        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {

        Optional<Categoria> obj = catServ.buscarPorId(id);

        return ResponseEntity.ok().body(obj);
    }

}
