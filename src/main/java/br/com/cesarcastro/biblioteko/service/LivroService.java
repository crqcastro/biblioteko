package br.com.cesarcastro.biblioteko.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cesarcastro.biblioteko.model.LivroModel;

@Service
public interface LivroService {

	List<LivroModel> buscarLivros(Long offset, Long size, LivroModel params);

}
