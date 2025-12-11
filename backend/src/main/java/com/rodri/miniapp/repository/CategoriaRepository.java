package com.rodri.miniapp.repository;

import com.rodri.miniapp.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
