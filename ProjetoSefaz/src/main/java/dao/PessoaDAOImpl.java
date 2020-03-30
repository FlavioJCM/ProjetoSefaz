package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entidade.Pessoa;

/**
 * 
 * @author Cleiton
 *
 *	Essa classe implementa a interface, todos os metodos mostrados na interface.
 *  Lembrando de uma coisa, a implementação ela recebe no construtor o entityManager,
 *  a conexão com o banco de dados, deixando assim essa classe totalemnte independente 
 *
 */

public class PessoaDAOImpl implements PessoaDAO {

	private EntityManager ent;

	//Construtor vai receber a conexão para executar
	public PessoaDAOImpl(EntityManager ent) {
		this.ent = ent;
	}


	/**
	 * Metodo inserir, recebe a pessoa toda preenchida, cria uma transição, inicia e 
	 * executar a ação de persistir, tudo dando certo realiza o commit no final
	 */
	public boolean inserir(Pessoa pessoa) {

		EntityTransaction tx = ent.getTransaction();
		tx.begin();

		ent.persist(pessoa);
		tx.commit();

		return true;

	}

/**
 * Metodo alterar, recebe a pessoa, criar uma transição, inicia e executa a ação de merger
 */
	public void alterar(Pessoa pessoa) {
		
		EntityTransaction tx = ent.getTransaction();
		tx.begin();

		ent.merge(pessoa);
		tx.commit();

	}


	public void remover(Pessoa pessoa) {
		
		EntityTransaction tx = ent.getTransaction();
		tx.begin();

		ent.remove(pessoa);
		tx.commit();
		
	}

/**
 * Pesquisar, pesquisar pela chave primaria que seria o cpf
 */
	public Pessoa pesquisar(String cpf) {

		Pessoa pessoa = ent.find(Pessoa.class, cpf);
		
		return pessoa;
		
	}

/**
 * O metodo listar todos, faz um select * from, porém com o JPA, vc faz a consulta pelo objeto direto
 * assim from Pessoa, que isso é o objeto pessoa e não a tabela
 */
	
	public List<Pessoa> listarTodos() {

		Query query = ent.createQuery("from Pessoa p");
		
		List<Pessoa> pessoas = query.getResultList();
	
		return pessoas;
		
	}	

}
