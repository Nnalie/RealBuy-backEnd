package com.leonardoelian.ecommerceAPI.resources;

import com.leonardoelian.ecommerceAPI.domain.Categoria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @GetMapping
    public List<Categoria> listar() {

        Categoria cat1 = new Categoria(1, "informática");
        Categoria cat2 = new Categoria(2, "Escritório");

        List<Categoria> listCat = new ArrayList<>();
        listCat.add(cat1);
        listCat.add(cat2);

        return listCat;
    }

}
