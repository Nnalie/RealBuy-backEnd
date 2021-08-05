package com.leonardoelian.ecommerceAPI.repositories;

import com.leonardoelian.ecommerceAPI.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {



}
