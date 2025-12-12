package com.rodri.miniapp.controller;

import com.rodri.miniapp.model.Marca;
import com.rodri.miniapp.repository.MarcaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
@CrossOrigin(origins = "http://localhost:4200")
public class MarcaController {

    private final MarcaRepository marcaRepository;

    public MarcaController(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }


    @GetMapping
    public List<Marca> listar() {
        return marcaRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Marca> obtener(@PathVariable Long id) {
        return marcaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Marca crear(@RequestBody Marca marca) {
        return marcaRepository.save(marca);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Marca> actualizar(
            @PathVariable Long id,
            @RequestBody Marca marca
    ) {
        return marcaRepository.findById(id)
                .map(existente -> {
                    existente.setNombre(marca.getNombre());
                    Marca actualizada = marcaRepository.save(existente);
                    return ResponseEntity.ok(actualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!marcaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        marcaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
