package testes;
import java.util.List;

import org.junit.Test;
import dao.PessoaDAOImpl;
import entidade.Endereco;
import entidade.Pessoa;
import util.JpaUtil;

public class PessoaTeste {

	@Test
	public void buscarUsuario() {
		PessoaDAOImpl pessoaDAO = new PessoaDAOImpl(JpaUtil.getEntityManager());
		
		
		
		
		
//		Endereco end = new Endereco();
//		end.setCodigo(21);
//////		
////		Pessoa p = new Pessoa("Jos�", "11111111111", 25, "M", "111", end);
////		
////		pessoaDAO.inserir(p);
////		
//		Pessoa palt = new Pessoa("Jos�", "11111111111", 25, "M", "1", end);
//		pessoaDAO.alterar(palt);
//		
////		Pessoa pdel = new Pessoa("Fl�vio Cavalcanti", "05826040440", 36, "M", "123", end);
//		
//		pessoaDAO.remover(pessoaDAO.pesquisar("42354367547"));
////		pessoaDAO.remover(pdel);
		
		
		
//		System.out.println(pessoaDAO.pesquisar("11111111111"));
		
		
		
		
	}	
	
}
