package com.leonardoelian.ecommerceAPI.domain.enums;

public enum TipoCliente {

    PESSOA_FISICA(1, "Pessoa Fisíca"),
    PESSOA_JURIDICA(2, "Pessoa Jurídica");

    private int cod;
    private String desc;

    TipoCliente(int cod, String desc) {
        this.cod = cod;
        this.desc = desc;
    }

    public int getCod() {
        return cod;
    }

    public String getDesc() {
        return desc;
    }

    public static TipoCliente toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }

        for(TipoCliente x : TipoCliente.values()) {
            if(cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + cod);
    }



}
