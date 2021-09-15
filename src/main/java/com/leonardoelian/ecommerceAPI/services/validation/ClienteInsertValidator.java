package com.leonardoelian.ecommerceAPI.services.validation;

import com.leonardoelian.ecommerceAPI.domain.Cliente;
import com.leonardoelian.ecommerceAPI.domain.enums.TipoCliente;
import com.leonardoelian.ecommerceAPI.dto.ClienteAuxDTO;
import com.leonardoelian.ecommerceAPI.repositories.ClienteRepository;
import com.leonardoelian.ecommerceAPI.resources.exceptions.FieldMessage;
import com.leonardoelian.ecommerceAPI.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.ArrayList;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteAuxDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteAuxDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Integer pF = TipoCliente.PESSOA_FISICA.getCod();
        Integer pJ = TipoCliente.PESSOA_JURIDICA.getCod();

        if(objDto.getTipoCliente() == pF && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCpnj", "CPF inválido"));
        }

        if(objDto.getTipoCliente() == pJ && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCpnj", "CNPJ inválido"));
        }

        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
        if(aux != null) {
            list.add(new FieldMessage("email", "Email já existente!"));
        }

        // inclua os testes aqui, inserindo erros na lista
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }

}
