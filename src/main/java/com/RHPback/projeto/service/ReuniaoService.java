package com.RHPback.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.RHPback.projeto.entities.RespostaModelo;
import com.RHPback.projeto.entities.Reuniao;
import com.RHPback.projeto.repository.ReuniaoRepository;
import com.RHPback.projeto.service.exceptions.ResourceNotFoundException;

@Service
public class ReuniaoService {

	@Autowired
	private ReuniaoRepository repository;
	
	@Autowired
	private RespostaModelo rm;

	public List<Reuniao> findAll() {
		return repository.findAll();
	}

	public Reuniao findById(Long id) {
		Optional<Reuniao> obj = repository.findById(id);
		//return obj.get();
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}

	public ResponseEntity<?> cadastrar(Reuniao pm, String acao) {
		if(pm.getMoment().equals("")) {
			rm.setMensagem("Todos os campos são obrigatórios !");
			return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
		} else if(pm.getInicio_reuniao().equals("")) {
			rm.setMensagem("Todos os campos são obrigatórios !");
			return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
		}else {
			if(acao.equals("cadastrar")) {
				return new ResponseEntity<Reuniao>(repository.save(pm), HttpStatus.CREATED);
			}else {
				return new ResponseEntity<Reuniao>(repository.save(pm), HttpStatus.OK);
			}
		}
		
	}

	public ResponseEntity<RespostaModelo>remover(long codigo){
		repository.deleteById(codigo);
		rm.setMensagem("O produto foi removido com sucesso!");
		return new ResponseEntity<RespostaModelo>(rm,  HttpStatus.OK);
		
	}

	

	private void updateData(Reuniao entity, Reuniao obj) {
		entity.setMoment(obj.getMoment());

		//entity.setSala(obj.getSala());

	}
}
