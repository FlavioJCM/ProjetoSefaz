package entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Cleiton
 * 
 *         PESSOA é a classe principal desse projeto onde tem a tela de pesquisa
 *         e a tela de manter PESSOA tem uma lista de ENDEREÇOS e tem como chave
 *         primaria o CPF, levando em consideração que a maioria dos sistema tem
 *         como login o CPF, nada mais justo que o CPF como chave primaria
 *
 */

@Entity
@Table(name = "PESSOA")
public class Pessoa {

	@Column(name = "nome")
	private String nome;

	@Id
	@Column(name = "cpf")
	private String cpf;

	@Column(name = "idade")
	private int idade;

	@Column(name = "sexo")
	private String sexo;

	@Column(name = "senha")
	private String senha;

	/**
	 * @ManyToOne essa referencia faz com que, ao recuperar um ENDEREÇO, o mesmo,
	 *            trás todos os endereços da pessoa, pegando a chave de referencia.
	 *            codigo de endereço com codigo_endereco da pessoa
	 */
	@ManyToOne
	@JoinColumn(name = "codigo_endereco", referencedColumnName = "codigo", nullable = false)
	private Endereco endereco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Pessoa(String nome, String cpf, int idade, String sexo, String senha, Endereco endereco) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.sexo = sexo;
		this.senha = senha;
		this.endereco = endereco;
	}

	public Pessoa() {

	}

	@Override
	public String toString() {
		return "\nNome: " + this.nome + "\nCPF: " + this.cpf + "\nIdade: " + this.idade + "\nSexo: " + this.sexo
				+ "\nSenha: " + this.senha + "\n" + this.endereco;
	}

}
