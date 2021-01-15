package br.com.cesarcastro.biblioteko.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "editora")
@Entity
public class EditoraModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String pais;
	private String uf;
	private String municipio;
	
	public EditoraModel(String nome) {
		this.nome = nome;
	}

}
