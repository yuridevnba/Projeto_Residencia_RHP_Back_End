package com.RHPback.projeto.controller;


import java.util.List;
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

import com.RHPback.projeto.entities.RespostaModelo;
import com.RHPback.projeto.entities.Reuniao;
import com.RHPback.projeto.service.ReuniaoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/reuniao")
public class ReuniaoController {

	@Autowired
	private ReuniaoService service;

	//////////////////// GET//////////////////////////////////
	/////////////////////////////////////////////////////////

	@GetMapping("/listar")
	public ResponseEntity<List<Reuniao>> findAll() {
		List<Reuniao> list = service.findAll(); // método estático
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Reuniao> findById(@PathVariable Long id) {
		Reuniao obj = service.findById(id);//
		return ResponseEntity.ok().body(obj);
	}
	//////////////////////// POST///////////////////////////////
	/////////////////////////////////////////////////////////////

	@PostMapping("/cadastrar")
	public ResponseEntity<?> insert(@RequestBody Reuniao obj) {
          return service.cadastrar(obj, "cadastrar");
		
	}

	/////////////////////////// PUT //////////////////////////////
	//////////////////////////////////////////////////////////////

	@PutMapping(value = "/alterar")
	public ResponseEntity<?> update( @RequestBody Reuniao obj) {
		return service.cadastrar(obj, "alterar");
	}
//////////////////////////DELETE////////////////////////////
/////////////////////////////////////////////////////////////
	@DeleteMapping("/remover/{codigo}")
	public ResponseEntity<RespostaModelo> remover(@PathVariable long codigo){
		return service.remover(codigo);
	}
	
	
}