package controle;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dao.EnderecoDAO;
import dao.EnderecoDAOImpl;
import dao.PessoaDAOImpl;
import entidade.Endereco;
import entidade.Pessoa;
import util.JpaUtil;

@ManagedBean(name = "PessoaBean")
@RequestScoped
public class PessoaBean {

	private Pessoa pessoa;
	private Pessoa pessoaPesquisa;
	private List<Pessoa> listaPessoas;
	private Pessoa pessoaEditar = new Pessoa();
	private Endereco enderecoEditar;

	public Endereco getEnderecoEditar() {
		return enderecoEditar;
	}

	public void setEnderecoEditar(Endereco enderecoEditar) {
		this.enderecoEditar = enderecoEditar;
	}

	private String cpfPessoaEditar;
	private String confirmarSenha;
	private String confirmarSenhaEditar;
	private static String cpfNaoEditavel;

	public String getConfirmarSenhaEditar() {
		return confirmarSenhaEditar;
	}

	public void setConfirmarSenhaEditar(String confirmarSenhaEditar) {
		this.confirmarSenhaEditar = confirmarSenhaEditar;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Pessoa getPessoaPesquisa() {
		return pessoaPesquisa;
	}

	public void setPessoaPesquisa(Pessoa pessoaPesquisa) {
		this.pessoaPesquisa = pessoaPesquisa;
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}

	public Pessoa getPessoaEditar() {
		return pessoaEditar;
	}

	public void setPessoaEditar(Pessoa pessoaEditar) {
		this.pessoaEditar = pessoaEditar;
	}

	public String getCpfPessoaEditar() {
		return cpfPessoaEditar;
	}

	public void setCpfPessoaEditar(String cpfPessoaEditar) {
		this.cpfPessoaEditar = cpfPessoaEditar;
	}

	public static String getEditarpessoa() {
		return editarPessoa;
	}

	public static String getTelapessoa() {
		return telaPessoa;
	}

	private Endereco endereco;

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	private static final String editarPessoa = "/paginas/TelaPessoaEditar.xhtml";
	private static final String telaPessoa = "/paginas/TelaPessoa.xhtml";

	@PostConstruct
	public void init() {
		limparCampos();
		this.pessoa = new Pessoa();
		this.endereco = new Endereco();
		this.pessoaPesquisa = new Pessoa();
		this.enderecoEditar = new Endereco();
		pesquisar();
	}

	public void salvar() {

		PessoaDAOImpl pessoaDAO = new PessoaDAOImpl(JpaUtil.getEntityManager());

		// SALVAR O ENDERECO PRIMEIRO
		EnderecoDAOImpl enderecoDAO = new EnderecoDAOImpl(JpaUtil.getEntityManager());
		Endereco endSalvar = enderecoDAO.isEnderecoExiste(this.endereco);

		Endereco eAuxiliar = new Endereco();

		if (endSalvar == null) {
			eAuxiliar.setRua(this.endereco.getRua().toUpperCase());
			eAuxiliar.setNumero(this.endereco.getNumero());
			eAuxiliar.setComplemento(this.endereco.getComplemento().toUpperCase());
			enderecoDAO.inserir(eAuxiliar);
			eAuxiliar = enderecoDAO.isEnderecoExiste(eAuxiliar);
		} else {
			eAuxiliar = endSalvar;
		}

		endereco = eAuxiliar;

		// VALIDAR CPF
		String cpf = "";

		if (pessoa.getCpf() != null && pessoa.getCpf().contains(".")) {
			cpf = pessoa.getCpf().substring(0, 3) + pessoa.getCpf().substring(4, 7) + pessoa.getCpf().substring(8, 11)
					+ pessoa.getCpf().substring(12, 14);
		}

		if (pessoaDAO.pesquisar(cpf) != null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "CPF Já Existe!"));
			return;
		}

		// VALIDAR SENHA

		if (pessoa.getSenha() == null || pessoa.getSenha().equals("")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Senha Obrigatória!"));
			return;
		}

		// VALIDAR CONFIRMAR SENHA
		if (!getConfirmarSenha().equals(pessoa.getSenha())) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Senha Incompatível!"));
			return;
		}

		Pessoa pAuxiliar = new Pessoa(this.pessoa.getNome().toUpperCase(), cpf, this.pessoa.getIdade(),
				this.pessoa.getSexo().toUpperCase(), this.pessoa.getSenha(), endereco);

		try {

			pessoaDAO.inserir(pAuxiliar);

			System.out.println("------ Salvou -----");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Salvo com Sucesso!!!"));
			limparCampos();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Atenção! " + e.toString()));
		}
		pesquisar();
	}

	public void pesquisar() {
		PessoaDAOImpl pessoaDAO = new PessoaDAOImpl(JpaUtil.getEntityManager());
		try {
			this.listaPessoas = pessoaDAO.listarTodos();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Atenção! " + e.toString()));
			e.printStackTrace();
		}
	}

	public void limparCampos() {
		pessoa = new Pessoa();
		pessoaPesquisa = new Pessoa();
		endereco = new Endereco();
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String prepararEditar() {
		PessoaDAOImpl pessoaDAO = new PessoaDAOImpl(JpaUtil.getEntityManager());
		EnderecoDAOImpl enderecoDAO = new EnderecoDAOImpl(JpaUtil.getEntityManager());

		Pessoa p = pessoaDAO.pesquisar(cpfPessoaEditar);

		try {
			cpfNaoEditavel = cpfPessoaEditar;
			pessoaEditar = p;
			enderecoEditar = enderecoDAO.pesquisar(p.getEndereco().getCodigo());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}

		return editarPessoa;
	}

	public void editar() {

		PessoaDAOImpl pessoaDAOEditar = new PessoaDAOImpl(JpaUtil.getEntityManager());

		// EDITAR O ENDERECO PRIMEIRO
		EnderecoDAOImpl enderecoDAO = new EnderecoDAOImpl(JpaUtil.getEntityManager());
		Endereco endEditar = enderecoDAO.isEnderecoExiste(this.enderecoEditar);

		Endereco eAuxiliarEditar = new Endereco();

		if (endEditar == null) {
			eAuxiliarEditar.setRua(this.enderecoEditar.getRua().toUpperCase());
			eAuxiliarEditar.setNumero(this.enderecoEditar.getNumero());
			eAuxiliarEditar.setComplemento(this.enderecoEditar.getComplemento().toUpperCase());
			enderecoDAO.inserir(eAuxiliarEditar);
			eAuxiliarEditar = enderecoDAO.isEnderecoExiste(eAuxiliarEditar);
		} else {
			eAuxiliarEditar = endEditar;
		}

		enderecoEditar = eAuxiliarEditar;

		// VALIDAR SENHA

		if (pessoaEditar.getSenha() == null || pessoaEditar.getSenha().equals("")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Senha Obrigatória!"));
			return;
		}

		// VALIDAR CONFIRMAR SENHA
		if (!getConfirmarSenhaEditar().equals(pessoaEditar.getSenha())) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Senha Incompatível!"));
			return;
		}

		Pessoa pAuxiliarEditar = new Pessoa(this.pessoaEditar.getNome().toUpperCase(), cpfNaoEditavel,
				this.pessoaEditar.getIdade(), this.pessoaEditar.getSexo().toUpperCase(), this.pessoaEditar.getSenha(),
				enderecoEditar);

		try {

			pessoaDAOEditar.alterar(pAuxiliarEditar);

			System.out.println("------ Alterou -----");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Alterado com Sucesso!!!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Atenção! " + e.toString()));
		}
		pessoaEditar.setCpf(cpfNaoEditavel);
		voltarParaTelaPessoa();
	}

	public void excluir(Pessoa pessoa) {
		try {
			PessoaDAOImpl pessoaDAO = new PessoaDAOImpl(JpaUtil.getEntityManager());
			pessoaDAO.remover(pessoaDAO.pesquisar(pessoa.getCpf()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
			e.printStackTrace();
		}

		pesquisar();
	}

	public void pesquisarCpf() {
		PessoaDAOImpl pessoaDAO = new PessoaDAOImpl(JpaUtil.getEntityManager());

		// VALIDAR CPF
		String cpf = "";

		try {

			if (!pessoaPesquisa.getCpf().trim().equals("")) {

				if (pessoaPesquisa.getCpf() != null && pessoaPesquisa.getCpf().contains(".")) {
					cpf = pessoaPesquisa.getCpf().substring(0, 3) + pessoaPesquisa.getCpf().substring(4, 7)
							+ pessoaPesquisa.getCpf().substring(8, 11) + pessoaPesquisa.getCpf().substring(12, 14);
				}

				Pessoa p = pessoaDAO.pesquisar(cpf);

				if (p == null) {

					this.listaPessoas.clear();

				} else {
					this.listaPessoas.clear();
					this.listaPessoas.add(p);
				}

			} else {
				pesquisar();
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Atenção! " + e.toString()));
			e.printStackTrace();
		}

	}

	public String voltarParaTelaPessoa() {
		pesquisar();
		return telaPessoa;
	}
}
