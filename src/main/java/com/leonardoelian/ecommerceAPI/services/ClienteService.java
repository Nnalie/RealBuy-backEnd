package com.leonardoelian.ecommerceAPI.services;

import com.leonardoelian.ecommerceAPI.domain.Cliente;
import com.leonardoelian.ecommerceAPI.dto.ClienteDTO;
import com.leonardoelian.ecommerceAPI.repositories.ClienteRepository;
import com.leonardoelian.ecommerceAPI.services.exceptions.DataIntegrityException;
import com.leonardoelian.ecommerceAPI.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public List<Cliente> findAll() {
        List<Cliente> obj = repo.findAll();
        return obj;
    }

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        return repo.save(cliente);
    }

    public Cliente update(Cliente cliente) {
        Cliente newCli = findById(cliente.getId());
        updateData(newCli, cliente);
        return repo.save(newCli);
    }

    private void updateData(Cliente newCli, Cliente cliente) {
        newCli.setNome(cliente.getNome());
        newCli.setEmail(cliente.getEmail());
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma cliente que possui endereços associados a ele.");
        }
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDto) {
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }

    public void validateDTO(ClienteDTO clienteDTO) {
        if(clienteDTO.getNome() == "" || clienteDTO.getEmail() == "") {
            throw new DataIntegrityException("Preenchimento obrigatório dos campos");
        }
        else if(clienteDTO.getNome().length() < 5 && clienteDTO.getNome().length() > 120) {
            throw new DataIntegrityException("O tamanho deve ser entre 5 e 80 caracteres");
        }
    }

}