package br.com.cesarcastro.biblioteko.model;

import java.util.List;

import javax.persistence.Entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "livro")
@Entity
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
	private EditoraModel editora;
	private List<AutorModel> autores;
	
}
