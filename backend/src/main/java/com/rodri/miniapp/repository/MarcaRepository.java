package com.rodri.miniapp.repository;

import com.rodri.miniapp.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
