package com.RHPback.projeto.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_reuniao")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

public class Reuniao implements Serializable {
	private static final long serialVersionUID = 1L;

	/////////////////// ATRIBUTOS////////////////////////
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReuniao;

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd 'T' 'Z'", timezone = "GMT")
	private String moment;
	
	
	private String nomereuniao;
	
	private Integer q_pessoas;
	
	private String termino_reuniao;
	
	private String inicio_reuniao;

	
	
	///////////////////////// ASSOCIAÇÕES////////////////
	
	@OneToMany
	@JoinTable(name = "tb_reuniao_funcionario", joinColumns = @JoinColumn(name = "reuniao_id"), inverseJoinColumns = @JoinColumn(name = "funcionario_id"))
	private Set<Funcionario> convidados = new HashSet<>();


	////////// GET DE COLECTIONS////////////////////////
	
	public Set<Funcionario> getConvidados() {
		return convidados;
	}
}
