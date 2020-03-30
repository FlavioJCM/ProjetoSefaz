package util;

public class SenhaException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private String retorno;	
	
	@Override
	public String toString() {
		return this.retorno;
	}
	
	public SenhaException(String retorno) {
		
		if(retorno.equals("senhaSemEspaco")) {
			this.retorno = "A senha n�o poder� conter espa�o!";
		}
		
		if(retorno.equals("senhaObrigatoria")) {
			this.retorno = "Obrigat�rio informar a senha!";
		}
		
		if(retorno.equals("senhaNaoCompativel")) {
			this.retorno = "Senhas n�o compat�veis!";
		}
		
		if(retorno.equals("senhaInvalida")) {
			this.retorno = "Senha inv�lida!";
		}
		
	}
	
}
