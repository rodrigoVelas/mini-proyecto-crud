package com.rodri.miniapp.controller;

import com.rodri.miniapp.model.Producto;
import com.rodri.miniapp.model.Marca;
import com.rodri.miniapp.model.Categoria;
import com.rodri.miniapp.repository.ProductoRepository;
import com.rodri.miniapp.repository.MarcaRepository;
import com.rodri.miniapp.repository.CategoriaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

    private final ProductoRepository productoRepository;
    private final MarcaRepository marcaRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoController(
            ProductoRepository productoRepository,
            MarcaRepository marcaRepository,
            CategoriaRepository categoriaRepository
    ) {
        this.productoRepository = productoRepository;
        this.marcaRepository = marcaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable Long id) {
        return productoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {

        Marca marca = marcaRepository.findById(producto.getMarca().getId())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));

        Categoria categoria = categoriaRepository.findById(producto.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        producto.setMarca(marca);
        producto.setCategoria(categoria);

        Producto guardado = productoRepository.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {

        if (!productoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Marca marca = marcaRepository.findById(producto.getMarca().getId())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));

        Categoria categoria = categoriaRepository.findById(producto.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        producto.setId(id);
        producto.setMarca(marca);
        producto.setCategoria(categoria);

        Producto actualizado = productoRepository.save(producto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!productoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        productoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
