package com.goparty.webservice.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Service;

import com.goparty.webservice.FullTextIndexService;

@Service("fullTextIndexService")
public class FullTextIndexServiceImpl implements FullTextIndexService {
	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean read() {
		FullTextEntityManager fullTextEntityManager = Search
				.getFullTextEntityManager(em);
		try {
			fullTextEntityManager.createIndexer().startAndWait();
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return false;
	}
}
