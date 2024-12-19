package com.github.clovisgargione.repository;

import com.github.clovisgargione.model.Filme;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FilmeRepository implements PanacheRepository<Filme> {

	public Filme findById(String id) {
		PanacheQuery<Filme> filme = find("id = :id", Parameters.with("id", id));
		if(filme.firstResultOptional().isEmpty()) {
			return null;
		}
		return filme.singleResult();
	}

}
