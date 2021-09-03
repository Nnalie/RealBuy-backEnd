package com.leonardoelian.ecommerceAPI.services;

import com.leonardoelian.ecommerceAPI.domain.Categoria;
import com.leonardoelian.ecommerceAPI.domain.Cliente;
import com.leonardoelian.ecommerceAPI.repositories.CategoriaRepository;
import com.leonardoelian.ecommerceAPI.repositories.ClienteRepository;
import com.leonardoelian.ecommerceAPI.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository cliRepo;

    public List<Cliente> findAll() {
        List<Cliente> obj = cliRepo.findAll();
        return obj;
    }

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = cliRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Categoria não encontrada! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

}