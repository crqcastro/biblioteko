package br.com.cesarcastro.biblioteko.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "autor")
@Entity
public class AutorModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sobrenome;
	
	public AutorModel(String nome) {
		this.nome = nome;
	}
	
	public AutorModel(String nome, String sobrenome) {
		this.nome = nome;
		this.sobrenome = sobrenome;
	}

}
