package br.com.cesarcastro.biblioteko.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "livro")
public class LivroModel {

	private Long id;
	private String titulo;
	private String ISBN;
	private String ISBN10;
	private String ISBN13;
	private Integer edicao;
	private Integer anoPublicacao;
	private String resumo;
	private List<String> termosChave;
	private Editora editora;
	private List<Autor> autores;
	
}
