package controle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import dao.PessoaDAOImpl;
import entidade.Pessoa;
import util.JpaUtil;
import util.PessoaNaoExisteException;
import util.SenhaException;

@ManagedBean(name = "LoginBean")
@RequestScoped
public class LoginBean {

	private static boolean isLogado = false;
	private Pessoa pessoa;

	private static final String telaAdm = "/templatePrincipal.xhtml";
	private static final String telaLogin = "/paginas/TelaLogin.xhtml";

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@PostConstruct
	public void init() {
		pessoa = new Pessoa();
	}

	public String logar() {
		
		PessoaDAOImpl pessoaDAO = new PessoaDAOImpl(JpaUtil.getEntityManager());
		
		try {
						
			String cpf = pessoa.getCpf().substring(0, 3) + pessoa.getCpf().substring(4, 7)
						+ pessoa.getCpf().substring(8, 11) + pessoa.getCpf().substring(12, 14);
						
			Pessoa x = pessoaDAO.pesquisar(cpf);			
			
			if (x == null) {
				throw new PessoaNaoExisteException(this.pessoa.getCpf());
			}

			if (!x.getSenha().equals(pessoa.getSenha())) {
				throw new SenhaException("senhaInvalida");
			}

			isLogado = true;

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", e.toString()));
		}
		if (isLogado) {
			return telaAdm;
		} else {
			return null;
		}

	}

	public String sairLogin() {

		isLogado = false;

		return telaLogin;
	}

}
