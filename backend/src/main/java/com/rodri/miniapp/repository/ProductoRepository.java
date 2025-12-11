package com.rodri.miniapp.repository;

import com.rodri.miniapp.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
