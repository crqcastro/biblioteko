package br.com.cesarcastro.biblioteko.model;

import javax.persistence.Entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "autor")
@Entity
public class AutorModel {
	
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
