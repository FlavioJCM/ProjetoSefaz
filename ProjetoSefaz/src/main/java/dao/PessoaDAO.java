package dao;

import java.util.List;

import entidade.Pessoa;

/**
 * 
 * @author Cleiton
 *
 *  PessoaDAO é uma interface, onde compartilha a chamada dos metodos, mas não os implementam.
 */

public interface PessoaDAO {
	
	public boolean inserir(Pessoa pessoa);
	
	public void alterar(Pessoa pessoa);

	public void remover(Pessoa pessoa);

	public Pessoa pesquisar(String cpf);

	public List<Pessoa> listarTodos();
	
}
