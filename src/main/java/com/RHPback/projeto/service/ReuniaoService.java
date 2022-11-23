package com.RHPback.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RHPback.projeto.entities.Reuniao;
import com.RHPback.projeto.repository.ReuniaoRepository;
import com.RHPback.projeto.service.exceptions.ResourceNotFoundException;

@Service
public class ReuniaoService {

	@Autowired
	private ReuniaoRepository repository;

	public List<Reuniao> findAll() {
		return repository.findAll();
	}

	public Reuniao findById(Long id) {
		Optional<Reuniao> obj = repository.findById(id);
		//return obj.get();
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}

	public Reuniao insert(Reuniao obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Reuniao update(Long id, Reuniao obj) {
		// Reuniao entity=repository.getReferenceById(id);
		Reuniao entity = findById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Reuniao entity, Reuniao obj) {
		entity.setMoment(obj.getMoment());

		//entity.setSala(obj.getSala());

	}
}
