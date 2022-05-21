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
import net.javaguides.springboot.model.Cidade;
import net.javaguides.springboot.repository.CidadeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	// get all cidades
	@GetMapping("/cidades")
	public List<Cidade> getAllCidade(){
		return cidadeRepository.findAll();
	}		
	
	// create cidade rest api
	@PostMapping("/cidades")
	public Cidade createCidade(@RequestBody Cidade cidade) {
		return cidadeRepository.save(cidade);
	}
	
	// get cidade by id rest api
	@GetMapping("/cidades/{id}")
	public ResponseEntity<Cidade> getCidadeById(@PathVariable Long id) {
		Cidade cidade = cidadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cidade nao existe:" + id));
		return ResponseEntity.ok(cidade);
	}
	
	// update cidade rest api
	@PutMapping("/cidades/{id}")
	public ResponseEntity<Cidade> updateCidade(@PathVariable Long id, @RequestBody Cidade cidadeDetails){
		Cidade cidade = cidadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cidade nao existe:" + id));
		
		cidade.setCidade(cidadeDetails.getCidade());
		cidade.setEstado(cidadeDetails.getEstado());
		
		Cidade updatedCidade = cidadeRepository.save(cidade);
		return ResponseEntity.ok(updatedCidade);
	}
	
	// delete cidade rest api
	@DeleteMapping("/cidades/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCidade(@PathVariable Long id){
		Cidade cidade = cidadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cidade nao existe:" + id));
		
		cidadeRepository.delete(cidade);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}	
}