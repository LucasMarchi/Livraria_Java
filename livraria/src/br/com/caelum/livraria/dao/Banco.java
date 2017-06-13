package br.com.caelum.livraria.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Usuario;

//Passando o Controle da Classe para o EJB
@Singleton // Teremos apenas uma instância desse objeto
@Startup // Indica que esse bean será usado desde o início da aplicação, inicializado ao startar o servidor (Eager Inicialization)
public class Banco {
	
	public static List<Livro> livros = new ArrayList<Livro>();
	public static List<Autor> autores = new ArrayList<Autor>();
	public static List<Usuario> usuarios = new ArrayList<Usuario>();
	
	@PersistenceContext
	EntityManager manager;
	
	@PostConstruct
	void aposCriacao(){
		System.out.println("Acabou de criar o Banco");
	}
	
	private static int chave = 1;
	
	static {
		inicializaBanco();
	}

	public void save(Livro livro) {
		livro.setId(chave++);
		livros.add(livro);
	}
	
	public List<Livro> listaLivros() {
		return livros;
	}
	
	public void save(Autor autor) {
		manager.persist(autor);
	}
	
	public List<Autor> listaAutores() {
		return manager.createQuery("select a from Autor a", Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		return manager.find(Autor.class, autorId);
	}
	
	public Usuario buscaPeloNome(String nome) {
		for (Usuario usuario : usuarios) {
			if(usuario.getLogin().equals(nome)) {
				return usuario;
			}
		}
		
		return null;
	}
	
	private static void inicializaBanco() {
		Autor silveira = new Autor(chave++, "Paulo Silveira");
		Autor cordeiro = new Autor(chave++, "Gilliard Cordeiro");
		Autor coelho = new Autor(chave++, "Hébert Coelho ");
		
		autores.add(silveira);
		autores.add(cordeiro);
		autores.add(coelho);
		
		livros.add(new Livro("Java 8 prático",silveira));
		livros.add(new Livro("Lógica de Programação",silveira));

		livros.add(new Livro("CDI: Integre as dependências",cordeiro));
		livros.add(new Livro("JSF e JPA",cordeiro));

		livros.add(new Livro("JPA Efficaz",coelho));
		livros.add(new Livro("JSF Efficaz",coelho));
		
		usuarios.add(new Usuario("admin", "pass"));
	}

}
