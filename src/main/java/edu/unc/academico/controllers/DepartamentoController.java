package edu.unc.academico.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.unc.academico.domain.Departamento;
import edu.unc.academico.services.DepartamentoService;


@RestController
@RequestMapping("/api/v1/departamento")
public class DepartamentoController {
	
	@Autowired // soporta características de inyeccion de independencias
	private DepartamentoService dptoService;
	
	
	@GetMapping
	public List<Departamento> listarDpto() {
		return dptoService.listarDpto();
	}

	@GetMapping("/{id}") // Formato en el que se captura desde la web
	public ResponseEntity<?> listarPorIdDpto(@PathVariable Long id) { // @PathVariable indica que la variable es capturada de la web
		Optional<Departamento> dptoOptional = dptoService.buscarPorIdDpto(id);
		if (dptoOptional.isPresent()) {
			return ResponseEntity.ok(dptoOptional.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> crearDpto(@RequestBody Departamento dpto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(dptoService.guardarDpto(dpto));
		
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editarDpto(@RequestBody Departamento dpto, @PathVariable Long id){
		Optional<Departamento> dptoOptional = dptoService.buscarPorIdDpto(id);
		if (dptoOptional.isPresent()) {
			Departamento dptoDB = dptoOptional.get();
			dptoDB.setNombreDpto(dpto.getNombreDpto());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(dptoService.guardarDpto(dptoDB));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarDpto(@PathVariable Long id){
		Optional<Departamento> dptoOptional = dptoService.buscarPorIdDpto(id);
		if (dptoOptional.isPresent()){
			dptoService.eliminarDpto(id);
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	

}
