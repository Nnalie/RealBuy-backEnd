package com.leonardoelian.ecommerceAPI.repositories;

import com.leonardoelian.ecommerceAPI.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
