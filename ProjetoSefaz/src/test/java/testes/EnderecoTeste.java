package testes;

import java.util.List;

import org.junit.Test;

import dao.EnderecoDAOImpl;
import entidade.Endereco;
import util.JpaUtil;

public class EnderecoTeste {
	@Test
	public void buscarUsuario() {
		EnderecoDAOImpl enderecoDAO = new EnderecoDAOImpl(JpaUtil.getEntityManager());

		Endereco end = new Endereco();
		end.setRua("RUA CAXANGA");
		end.setNumero(178);
		end.setComplemento("APT21");

		Endereco endSalvar = enderecoDAO.isEnderecoExiste(end);
		
		if(endSalvar != null) {
			System.out.println(endSalvar.toString());
		} else {
			System.out.println("Endereço não existe!");
		}
		
//		List<Endereco> enderecos = enderecoDAO.listarTodos();
//		
//		for (Endereco endereco : enderecos) {
//			System.out.println(endereco.toString());
//		}
		
	}
}
