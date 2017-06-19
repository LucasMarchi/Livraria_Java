package br.com.caelum.livraria.webservice;

import java.rmi.RemoteException;

public class TestRequestSoapComJava {

	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		
		LivrariaWS cliente = new LivrariaWSProxy();
		
		Livro[] livros = cliente.getLivrosPeloNome("O Mundo de Sofia");
		
		for (Livro livro : livros) {
			System.out.println(livro.getTitulo());
			System.out.println(livro.getAutor().getNome());
		}

	}

}
