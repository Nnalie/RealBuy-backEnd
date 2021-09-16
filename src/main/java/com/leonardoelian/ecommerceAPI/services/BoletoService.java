package com.leonardoelian.ecommerceAPI.services;

import com.leonardoelian.ecommerceAPI.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public static void preencherPagtoComBoleto(PagamentoComBoleto pagtoB, Date pedidoInstante) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(pedidoInstante);
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        pagtoB.setDataVencimento(calendar.getTime());
    }

}
