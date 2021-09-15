package com.leonardoelian.ecommerceAPI.services;

import com.leonardoelian.ecommerceAPI.domain.Cidade;
import com.leonardoelian.ecommerceAPI.domain.Cliente;
import com.leonardoelian.ecommerceAPI.domain.Endereco;
import com.leonardoelian.ecommerceAPI.domain.enums.TipoCliente;
import com.leonardoelian.ecommerceAPI.dto.ClienteAuxDTO;
import com.leonardoelian.ecommerceAPI.dto.ClienteDTO;
import com.leonardoelian.ecommerceAPI.repositories.CidadeRepository;
import com.leonardoelian.ecommerceAPI.repositories.ClienteRepository;
import com.leonardoelian.ecommerceAPI.repositories.EnderecoRepository;
import com.leonardoelian.ecommerceAPI.services.exceptions.DataIntegrityException;
import com.leonardoelian.ecommerceAPI.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository repoEnd;

    @Autowired
    private CidadeRepository repoCid;

    public List<Cliente> findAll() {
        List<Cliente> obj = repo.findAll();
        return obj;
    }

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        cliente = repo.save(cliente);
        repoEnd.saveAll(cliente.getEnderecos());
        return cliente;
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
            throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos associados a ele.");
        }
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDto) {
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }

    public Cliente fromDTO(ClienteAuxDTO objDto) {
       Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipoCliente()));
       Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
       Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cliente, cid);

       cliente.getEnderecos().add(endereco);
       cliente.getTelefones().add(objDto.getTelefone_1());
       if(objDto.getTelefone_2() != null) {
           cliente.getTelefones().add(objDto.getTelefone_2());
       }
       if(objDto.getTelefone_3() != null) {
           cliente.getTelefones().add(objDto.getTelefone_3());
       }
       System.out.println(objDto);
       return cliente;
    }

    public void validateDTO(ClienteDTO clienteDTO) {
        if(clienteDTO.getNome() == "" || clienteDTO.getEmail() == "") {
            throw new DataIntegrityException("Preenchimento obrigatório dos campos");
        }
        else if(clienteDTO.getNome().length() < 5 && clienteDTO.getNome().length() > 120) {
            throw new DataIntegrityException("O tamanho deve ser entre 5 e 120 caracteres");
        }
    }

    public void validateDTO(ClienteAuxDTO cli) {
        if(cli.getNome() == "" || cli.getEmail() == "" || cli.getCpfOuCnpj() == ""
        || cli.getLogradouro() == "" || cli.getNumero() == "" || cli.getCep() == ""
        || cli.getTelefone_1() == "") {
            throw new DataIntegrityException("Preenchimento obrigatório dos campos");
        }
        else if(cli.getNome().length() < 5 && cli.getNome().length() > 120) {
            throw new DataIntegrityException("O tamanho deve ser entre 5 e 120 caracteres");
        }
    }

}