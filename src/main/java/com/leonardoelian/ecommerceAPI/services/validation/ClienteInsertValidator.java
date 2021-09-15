package com.leonardoelian.ecommerceAPI.services.validation;

import com.leonardoelian.ecommerceAPI.domain.enums.TipoCliente;
import com.leonardoelian.ecommerceAPI.dto.ClienteAuxDTO;
import com.leonardoelian.ecommerceAPI.resources.exceptions.FieldMessage;
import com.leonardoelian.ecommerceAPI.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.ArrayList;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteAuxDTO> {

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteAuxDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Integer pF = TipoCliente.PESSOA_FISICA.getCod();
        Integer pJ = TipoCliente.PESSOA_JURIDICA.getCod();

        if(objDto.getTipoCliente() == pF && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            System.out.println("CPF inválido");
            list.add(new FieldMessage("cpfOuCpnj", "CPF inválido"));
        }

        if(objDto.getTipoCliente() == pJ && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            System.out.println("CNPJ inválido");
            list.add(new FieldMessage("cpfOuCpnj", "CNPJ inválido"));
        }

        // inclua os testes aqui, inserindo erros na lista
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        System.out.println(list);
        return list.isEmpty();
    }

}
