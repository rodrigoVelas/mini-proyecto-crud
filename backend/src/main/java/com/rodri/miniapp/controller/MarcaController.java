package com.rodri.miniapp.controller;

import com.rodri.miniapp.model.Marca;
import com.rodri.miniapp.repository.MarcaRepository;
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

    @PostMapping
    public Marca crear(@RequestBody Marca marca) {
        return marcaRepository.save(marca);
    }
}
