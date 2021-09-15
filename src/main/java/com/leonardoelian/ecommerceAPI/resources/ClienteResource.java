package com.leonardoelian.ecommerceAPI.resources;

import com.leonardoelian.ecommerceAPI.domain.Categoria;
import com.leonardoelian.ecommerceAPI.domain.Cliente;
import com.leonardoelian.ecommerceAPI.domain.Cliente;
import com.leonardoelian.ecommerceAPI.dto.CategoriaDTO;
import com.leonardoelian.ecommerceAPI.dto.ClienteAuxDTO;
import com.leonardoelian.ecommerceAPI.dto.ClienteDTO;
import com.leonardoelian.ecommerceAPI.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<?> findAll() {

        List<Cliente> obj = service.findAll();

        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClienteDTO>> findPerPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Integer id) {

        Cliente obj = service.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteAuxDTO clienteAuxDTO) {
        service.validateDTO(clienteAuxDTO);
        Cliente obj = service.fromDTO(clienteAuxDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody ClienteDTO objDto,
                                       @PathVariable Integer id) {
        service.validateDTO(objDto);
        Cliente categoria = service.fromDTO(objDto);
        categoria.setId(id);
        categoria = service.update(categoria);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
