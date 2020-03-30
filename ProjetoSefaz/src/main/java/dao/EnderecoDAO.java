package dao;

import java.util.List;
import entidade.Endereco;
import entidade.Pessoa;

public interface EnderecoDAO {
	
	public boolean inserir(Endereco endereco);
	
	public void alterar(Endereco endereco);

	public void remover(Endereco endereco);

	public Endereco pesquisar(int codigo);

	public List<Endereco> listarTodos();
	
	public Endereco isEnderecoExiste(Endereco endereco);

}
