package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.interceptador.LogInterceptador;
import br.com.caelum.livraria.modelo.Autor;

// Passando o Controle da Classe para o EJB
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER) // Opcional, esse é o valor default
@Interceptors({LogInterceptador.class}) // Classe que interceptará
public class AutorDao {

	// Injetando a dependencia
	@PersistenceContext
	private EntityManager manager;
	
	// Esse método será como um callback
	@PostConstruct
	void aposCriacao(){
		System.out.println("AutorDao foi criado");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED) // Opcional, esse é o valor default
	public void salva(Autor autor) {
		
		System.out.println("Salvando Autor " + autor.getNome());
		
//		try {
//			// Causando pausa temporária na execução da thread para análise de funcionamento
//			Thread.sleep(20000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} // 20s
		
		manager.persist(autor);
		
		System.out.println("Autor " + autor.getNome() + " salvo");
	}
	
	public List<Autor> todosAutores() {
		return manager.createQuery("select a from Autor a", Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		return manager.find(Autor.class, autorId);
	}
	
}
