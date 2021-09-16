package com.leonardoelian.ecommerceAPI.services;

import com.leonardoelian.ecommerceAPI.domain.ItemPedido;
import com.leonardoelian.ecommerceAPI.domain.PagamentoComBoleto;
import com.leonardoelian.ecommerceAPI.domain.Pedido;
import com.leonardoelian.ecommerceAPI.domain.enums.EstadoPagamento;
import com.leonardoelian.ecommerceAPI.repositories.ItemPedidoRepository;
import com.leonardoelian.ecommerceAPI.repositories.PagamentoRepository;
import com.leonardoelian.ecommerceAPI.repositories.PedidoRepository;
import com.leonardoelian.ecommerceAPI.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    @Autowired
    private PagamentoRepository repoPagto;

    @Autowired
    private ProdutoService prodServ;

    @Autowired
    private ItemPedidoRepository ipRepo;

    public List<Pedido> findAll() {
        List<Pedido> obj = repo.findAll();
        return obj;
    }

    public Pedido findById(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Categoria não encontrada! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    public Pedido insert(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.getPagamento().setPedido(pedido);
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);

        if(pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagtoB = (PagamentoComBoleto) pedido.getPagamento();
            BoletoService.preencherPagtoComBoleto(pagtoB, pedido.getInstante());
        }

        pedido = repo.save(pedido);
        repoPagto.save(pedido.getPagamento());

        for(ItemPedido ip : pedido.getItens()) {
            ip.setDesconto(0.0);
            ip.setPreco((prodServ.findById(ip.getProduto().getId())).getPreco());
            ip.setPedido(pedido);
        }

        ipRepo.saveAll(pedido.getItens());
        return pedido;
    }
}