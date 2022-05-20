package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Estado;
import net.javaguides.springboot.repository.EstadoRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	// get all estados
	@GetMapping("/estados")
	public List<Estado> getAllEstado(){
		return estadoRepository.findAll();
	}		
	
	// create estado rest api
	@PostMapping("/estados")
	public Estado createEstado(@RequestBody Estado estado) {
		return estadoRepository.save(estado);
	}
	
	// get estado by id rest api
	@GetMapping("/estados/{id}")
	public ResponseEntity<Estado> getEstadoById(@PathVariable Long id) {
		Estado estado = estadoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Estado nao existe:" + id));
		return ResponseEntity.ok(estado);
	}
	
	// update estado rest api
	@PutMapping("/estados/{id}")
	public ResponseEntity<Estado> updateEstado(@PathVariable Long id, @RequestBody Estado estadoDetails){
		Estado estado = estadoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Estado nao existe:" + id));
		
		estado.setEstado(estadoDetails.getEstado());
		estado.setSigla(estadoDetails.getSigla());
		
		Estado updatedEstado = estadoRepository.save(estado);
		return ResponseEntity.ok(updatedEstado);
	}
	
	// delete estado rest api
	@DeleteMapping("/estados/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEstado(@PathVariable Long id){
		Estado estado = estadoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Estado nao existe:" + id));
		
		estadoRepository.delete(estado);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}	
}