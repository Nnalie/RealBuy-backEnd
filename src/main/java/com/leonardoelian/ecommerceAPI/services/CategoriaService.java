package com.leonardoelian.ecommerceAPI.services;

import com.leonardoelian.ecommerceAPI.domain.Categoria;
import com.leonardoelian.ecommerceAPI.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository catRepo;

    public List<Categoria> buscarTodos() {
        List<Categoria> obj = catRepo.findAll();
        return obj;
    }

    public Optional<Categoria> buscarPorId(Integer id) {
        Optional<Categoria> obj = catRepo.findById(id);
        return obj;
    }

}