package entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Cleiton
 * 
 *         Classe ENDERECO utilizada com referencia com a PESSOA, onde faz um
 *         para muitos. Nesta classe tem uma coisa em particular, ela tem um ID,
 *         onde o mesmo é autoincrmentado pelo proprio JPA com a sequencia
 *         S_ENDERECO
 *
 */

@Entity
@Table(name = "ENDERECO")
public class Endereco {

	@Id
	@Column(name = "codigo")
	@GeneratedValue(generator = "S_ENDERECO")
	@SequenceGenerator(name = "S_ENDERECO", sequenceName = "S_ENDERECO", allocationSize = 1)
	private int codigo;

	@Column(name = "numero")
	private int numero;

	@Column(name = "rua")
	private String rua;

	@Column(name = "complemento")
	private String complemento;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Endereco(int codigo, int numero, String rua, String complemento) {
		super();
		this.codigo = codigo;
		this.numero = numero;
		this.rua = rua;
		this.complemento = complemento;
	}

	public Endereco() {

	}

	@Override
	public String toString() {
		return "Endereço:\nNúmero: " + this.numero + "\nRua: " + this.rua + "\nComplemento: " + complemento
				+ "\nCódigo: " + this.codigo;
	}

}
