package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entidade.Endereco;
import entidade.Pessoa;

public class EnderecoDAOImpl implements EnderecoDAO {

	private EntityManager ent;

	// Construtor vai receber a conexão para executar
	public EnderecoDAOImpl(EntityManager ent) {
		this.ent = ent;
	}

	public boolean inserir(Endereco endereco) {

		EntityTransaction tx = ent.getTransaction();
		tx.begin();

		ent.persist(endereco);
		tx.commit();

		return true;

	}

	/**
	 * Metodo alterar, recebe a pessoa, criar uma transição, inicia e executa a ação
	 * de merger
	 */
	public void alterar(Endereco endereco) {

		EntityTransaction tx = ent.getTransaction();
		tx.begin();

		ent.merge(endereco);
		tx.commit();

	}

	public void remover(Endereco endereco) {

		EntityTransaction tx = ent.getTransaction();
		tx.begin();

		ent.remove(endereco);
		tx.commit();

	}

	/**
	 * Pesquisar, pesquisar pela chave primaria que seria o codigo
	 */
	public Endereco pesquisar(int codigo) {

		Endereco endereco = ent.find(Endereco.class, codigo);

		return endereco;

	}

	public List<Endereco> listarTodos() {

		Query query = ent.createQuery("from Endereco e");

		List<Endereco> enderecos = query.getResultList();

		return enderecos;

	}

	public Endereco isEnderecoExiste(Endereco endereco) {

		String sql = "from Endereco e WHERE rua = '" + endereco.getRua() + "' AND numero = '"
				+ endereco.getNumero() + "' AND complemento = '" + endereco.getComplemento() + "'";
		
		Query query = ent.createQuery(sql);		
		List<Endereco> enderecos = query.getResultList();
		if(!enderecos.isEmpty()) {
			return enderecos.get(0);
		} else {
			return null;
		}

	}

}
