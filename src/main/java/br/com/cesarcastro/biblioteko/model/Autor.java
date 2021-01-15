package br.com.cesarcastro.biblioteko.model;

import lombok.Data;

@Data
public class Autor {
	
	private Long id;
	private String nome;
	private String sobrenome;
	
	public Autor(String nome) {
		this.nome = nome;
	}
	
	public Autor(String nome, String sobrenome) {
		this.nome = nome;
		this.sobrenome = sobrenome;
	}

}
