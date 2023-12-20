package com.panta.denuncia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.panta.denuncia.entity.Denuncia;
import com.panta.denuncia.service.DenunciaService;

@RestController
@RequestMapping("/v1/denuncias")
public class DenunciaController {
	
	@Autowired
	private DenunciaService service;
	
	@GetMapping()
	public ResponseEntity<List<Denuncia>> findAll(
			@RequestParam(value="dni", required=false, defaultValue = "")String dni,
			@RequestParam(value="offset", required=false, defaultValue = "0") int pageNumber,
			@RequestParam(value="limit", required=false, defaultValue = "5") int pageSize
			){
		Pageable page = PageRequest.of(pageNumber,pageSize);
		List<Denuncia> denuncias;
		if (dni==null) {
			denuncias=service.findAll(page);
		}else {
			denuncias=service.findByDni(dni, page);
		}
		
		if (denuncias.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(denuncias);
			
		}
	@GetMapping(value="/{id}")
	public ResponseEntity<Denuncia> findById(@PathVariable("id") int id){
		
		Denuncia denuncia = service.findById(id);
		if (denuncia==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(denuncia);
	}
	
	@GetMapping(value="/dni/{dni}")
	public ResponseEntity<Denuncia> findByDni(@PathVariable("dni") String dni){
		
		Denuncia denuncia = service.findByDni(dni);
		if (denuncia==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(denuncia);
	}
	@PostMapping()
	public ResponseEntity<Denuncia> create(@RequestBody Denuncia denuncia){
		Denuncia registro= service.save(denuncia);
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
	@PutMapping(value="/{id}")
	public ResponseEntity<Denuncia> update(@PathVariable("id") int id,@RequestBody Denuncia denuncia){
		Denuncia registro = service.update(denuncia);
		if (registro==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(registro);
		
	}
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Denuncia> delete(@PathVariable("id") int id){
		service.delete(id);
		return ResponseEntity.ok(null);
	}
}
