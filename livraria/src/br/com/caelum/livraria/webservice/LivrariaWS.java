package br.com.caelum.livraria.webservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Livro;

@WebService // EJB publica o serviço
@Stateless
public class LivrariaWS {
	
	@Inject
	LivroDao dao;

	@WebResult(name="autores") // @WebResult permite usar o name na mensagem soap para o retorno
	public List<Livro> getLivrosPeloNome(@WebParam(name="titulo") String nome){ // @WebParam permite usar o name na mensagem soap para o argumento
		
		System.out.println("LivrariaWS: procurando pelo nome " + nome);
		
		return dao.livrosPeloNome(nome);
	}
	
}
