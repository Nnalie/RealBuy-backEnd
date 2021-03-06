package com.leonardoelian.ecommerceAPI.services;

import com.leonardoelian.ecommerceAPI.domain.Categoria;
import com.leonardoelian.ecommerceAPI.dto.CategoriaDTO;
import com.leonardoelian.ecommerceAPI.repositories.CategoriaRepository;
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
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public List<Categoria> findAll() {
        List<Categoria> obj = repo.findAll();
        return obj;
    }

    public Categoria findById(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Categoria não encontrada! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return repo.save(categoria);
    }

    public Categoria update(Categoria categoria) {
        Categoria newCat = findById(categoria.getId());
        updateData(newCat, categoria);
        return repo.save(newCat);
    }

    private void updateData(Categoria newCat, Categoria categoria) {
        newCat.setNome(categoria.getNome());
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos associados a ela.");
        }
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO objDto) {
        return new Categoria(objDto.getId(), objDto.getNome());
    }

    public void validateDTO(CategoriaDTO categoriaDTO) {
        if(categoriaDTO.getNome() == "") {
            throw new DataIntegrityException("Preenchimento obrigatório");
        }
        else if(categoriaDTO.getNome().length() < 5) {
            throw new DataIntegrityException("O tamanho deve ser entre 5 e 80 caracteres");
        }
    }
}