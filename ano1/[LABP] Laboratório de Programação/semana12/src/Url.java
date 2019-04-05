
/**
 * 
 * @author 
 *
 */

public class Url {
	private String address;
	private int ip;
	
	/**
	 * Construtor.
	 * @param address - o endereco do url.
	 * @param x - o componente x do IP, 100.1.1.x 
	 * @requires address no formato xxx.yyy...zzz.gtld 
	 * @requires x > 0 && x < 255 
	 */
	public Url(String address, int ip) {
		this.address = address;
		this.ip = ip;
	} 

	/**
	 * Obter o ip deste Url
	 * @return o ip deste Url
	 */
	public String obterEnderecoIP() {
		return "100.1.1."+ip;
	}

	/**
	 * Obter o endereco do Url
	 * @return o endereco
	 */
	public String obterEndereco() {
		return address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ip;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Url))
			return false;
		Url other = (Url) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (ip != other.ip)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ENDERECO : "+address+", IP : "+obterEnderecoIP();
	}
}
