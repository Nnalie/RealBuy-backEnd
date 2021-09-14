package com.leonardoelian.ecommerceAPI.dto;

import java.io.Serializable;

public class ClienteAuxDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    //Cliente
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private Integer tipoCliente;

    //Endereço
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    //Telefone
    private String telefone_1;
    private String telefone_2;
    private String telefone_3;

    //Cidade
    private Integer cidadeId;

    public ClienteAuxDTO() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Integer tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone_1() {
        return telefone_1;
    }

    public void setTelefone_1(String telefone_1) {
        this.telefone_1 = telefone_1;
    }

    public String getTelefone_2() {
        return telefone_2;
    }

    public void setTelefone_2(String telefone_2) {
        this.telefone_2 = telefone_2;
    }

    public String getTelefone_3() {
        return telefone_3;
    }

    public void setTelefone_3(String telefone_3) {
        this.telefone_3 = telefone_3;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidade_id) {
        this.cidadeId = cidade_id;
    }


}
