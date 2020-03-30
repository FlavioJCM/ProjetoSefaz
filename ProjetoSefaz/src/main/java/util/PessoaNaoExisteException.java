package util;

public class PessoaNaoExisteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cpf;
	
	public PessoaNaoExisteException(String cpf) {
		super();
		this.cpf = cpf;
	}  
	
	@Override
	public String toString() {

		return "A pessoa de CPF: " + this.cpf + " Não Existe!";
	}
	
	
}
