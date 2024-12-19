package com.github.clovisgargione.controller;

import java.util.List;

import com.github.clovisgargione.dto.FilmeDTO;
import com.github.clovisgargione.model.Filme;
import com.github.clovisgargione.repository.FilmeRepository;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/filme")
public class FilmeController {

	@Inject
	private FilmeRepository repository;
	
	@GET
	public Response getFilmes() {
		List<Filme> filmes = repository.listAll();
		return Response.ok(filmes).build();
	}
	
	@GET
	@Path("{id}")
	public Response getFilme(@PathParam("id") String id) {
		Filme filme = repository.findById(id);
		if(filme == null) {
			return Response.noContent().build();
		}
		return Response.ok(filme).build();
	}
	
	@POST
	@Transactional
	public Response postFilme(FilmeDTO filmeDTO) {
		Filme filme = new Filme(filmeDTO.getTitulo(), filmeDTO.getStatus(), filmeDTO.getValorLocacao());
		repository.persist(filme);
		return Response.ok(filme).build();
	}
	
}
