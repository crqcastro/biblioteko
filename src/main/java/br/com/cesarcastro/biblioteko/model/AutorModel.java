package br.com.cesarcastro.biblioteko.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "autor")
@Entity
@Table(name = "tbl_autor")
public class AutorModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sobrenome;
	private String shortName;
	
	public AutorModel(String nome) {
		this.nome = nome;
	}
	
	public AutorModel() {}
	
	public AutorModel(String nome, String sobrenome, String shortName) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.shortName = shortName;
	}

}
