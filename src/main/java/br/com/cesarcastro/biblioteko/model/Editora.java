package br.com.cesarcastro.biblioteko.model;

import lombok.Data;

@Data
public class Editora {
	
	private Long id;
	private String nome;
	private String pais;
	private String uf;
	private String municipio;
	
	public Editora(String nome) {
		this.nome = nome;
	}

}
