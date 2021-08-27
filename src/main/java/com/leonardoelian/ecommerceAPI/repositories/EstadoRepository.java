package com.leonardoelian.ecommerceAPI.repositories;

import com.leonardoelian.ecommerceAPI.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
