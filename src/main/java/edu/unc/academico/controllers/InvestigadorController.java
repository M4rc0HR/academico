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
import edu.unc.academico.domain.Investigador;
import edu.unc.academico.services.InvestigadorService;


@RestController
@RequestMapping("/api/v1/investigador")
public class InvestigadorController {
	
	@Autowired // soporta características de inyeccion de independencias
	private InvestigadorService invService;
	
	@GetMapping
	public List<Investigador> listarInv() {
		
		return invService.listarInv();
	}
	
	@GetMapping("/{id}") // Formato en el que se captura desde la web
	public ResponseEntity<?> listarPorIdInv(@PathVariable Long id) { // @PathVariable indica que la variable es capturada de la web
		Optional<Investigador> invOptional = invService.buscarPorIdInv(id);
		if (invOptional.isPresent()) {
			return ResponseEntity.ok(invOptional.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> crearInv(@RequestBody Investigador inv) {
		return ResponseEntity.status(HttpStatus.CREATED).body(invService.guardarInv(inv));
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editarInv(@RequestBody Investigador inv, @PathVariable Long id){
		Optional<Investigador> invOptional = invService.buscarPorIdInv(id);
		if (invOptional.isPresent()) {
			
			Investigador invDB = invOptional.get();
			invDB.setNombres(inv.getNombres());
			invDB.setApePat(inv.getApePat());
			invDB.setApeMat(inv.getApeMat());
			invDB.setEmail(inv.getEmail());
			invDB.setFechaNac(inv.getFechaNac());
			//invDB.setIdDpto(inv.getIdDpto());			
			
			return ResponseEntity.status(HttpStatus.CREATED).body(invService.guardarInv(invDB));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarInv(@PathVariable Long id){
		Optional<Investigador> invOptional = invService.buscarPorIdInv(id);
		if (invOptional.isPresent()){
			invService.eliminarInv(id);
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	
	
}
