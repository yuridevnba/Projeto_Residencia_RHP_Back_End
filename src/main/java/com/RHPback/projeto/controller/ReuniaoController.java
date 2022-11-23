package com.RHPback.projeto.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<Reuniao> insert(@RequestBody Reuniao obj) {
		obj = service.insert(obj);
		// return ResponseEntity.ok().body( obj);// retorna 200 q n é o ideal

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdReuniao())
				.toUri();

		return ResponseEntity.created(uri).body(obj);
	}

	/////////////////////////// PUT //////////////////////////////
	//////////////////////////////////////////////////////////////

	@PutMapping(value = "/alterar/{id}")

	public ResponseEntity<Reuniao> update(@PathVariable Long id, @RequestBody Reuniao obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
//////////////////////////DELETE////////////////////////////
/////////////////////////////////////////////////////////////

	@DeleteMapping(value = ("/remover/{id}"))

	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();

	}
}