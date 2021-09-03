package com.leonardoelian.ecommerceAPI.repositories;

import com.leonardoelian.ecommerceAPI.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
