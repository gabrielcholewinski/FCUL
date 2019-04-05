

/**
 * 
 * @author 
 *
 */
public class Organizacao {
	private String nome;
	private String fullAddress;
	private String domain;

	/**
	 * Construtor
	 * @param nome - o nome da organizacao 
	 * @param fullAddress - o endereco internet completo da organizacao
	 * @requires nome != null  && fullAddress != null 
	 * @requires fullAddress no formato xxx.yyy...zzz.gtld
	 * @requires gtld in http://data.iana.org/TLD/tlds-alpha-by-domain.txt
	 */
	public Organizacao(String nome, String fullAddress){
		this.nome = nome;
		this.fullAddress = fullAddress;
		String[] aux = fullAddress.split("[.]");
		domain = aux[aux.length-1];
	}  

	/**
	 * Obter o nome da organizacao
	 * @return o nome.
	 */
	public String obterNome(){
		return nome;
	}

	/**
	 * Obter o dominio da organizacao
	 * @return o gtld
	 */
	public String obterDominio(){
		return domain;
	}

	/**
	 * Obter o endereco internet completo da organizacao
	 * @return o endereco internet completo.
	 */
	public String obterEnderecoInternet(){
		return fullAddress;
	} 

	@Override
	public String toString() {
		return "Organizacao [nome="+nome+", categoria="+obterDominio()+", endereco internet="+fullAddress+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		result = prime * result + ((fullAddress == null) ? 0 : fullAddress.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Organizacao other = (Organizacao) obj;
		if (domain == null) {
			if (other.domain != null)
				return false;
		} else if (!domain.equals(other.domain))
			return false;
		if (fullAddress == null) {
			if (other.fullAddress != null)
				return false;
		} else if (!fullAddress.equals(other.fullAddress))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}