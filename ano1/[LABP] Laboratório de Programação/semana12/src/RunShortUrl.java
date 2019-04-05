import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; 

/**
 * 
 * @author labp
 *
 */

public class RunShortUrl {

	private static final String SEPARADOR = "-";
	private static Shortener gestor;

	public static void main(String[] args) throws FileNotFoundException, IllegalArgumentException {
		String input = args[0];
		int maxUrls = Integer.parseInt(args[1]);
		List<String> dominios = new ArrayList<>();
		for (int i = 2; i< args.length; i++){
			dominios.add(args[i]);
		} 

		System.out.println("---INICIO---");
		
		System.out.println("---MAX URL ALOJADOS: " + Integer.valueOf(args[1]) + "---");
		System.out.print("---GTLD (DOMINIOS DE TOPO)--: ");
		for (int i = 2; i< args.length; i++){
			System.out.print(args[i]+" ");
		}
		System.out.println();
		gestor = new Shortener(dominios, maxUrls);
		System.out.println("---"); 
		System.out.println("---COMANDOS---");
		
		Scanner scanner = new Scanner(new FileReader(input));
		while (scanner.hasNextLine()) {
			String comando = scanner.nextLine();
			if (!comando.startsWith("-"))
				switchCommand(comando);
		}
		scanner.close();
		System.out.println("---FIM---");
	}

	private static void switchCommand(String comando) {
		String[] itens = comando.split(SEPARADOR); 
		switch (itens[0]) {
		case "registarOrganizacao":
			registarOrganizacao(itens[1].trim(), itens[2].trim());
			break;
		case "gerarUrl":  
			gerarUrl(itens[1].trim());
			break;
		case "obterUrl":
			obterURL(itens[1].trim());
			break;
		case "total":
			calcularTotal(itens);
			break;
		case "obterDono":
			obterDono(itens[1].trim(), Integer.valueOf(itens[2].trim()));
			break;		
		case "obterDominio":
			obterDominio(itens[1].trim());
			break;
		case "removerUrlOrg":
			removerPorNome(itens[1].trim());
			break;
		case "removerUrlDominio":
			removerPorDominio(itens[1].trim(), Integer.valueOf(itens[2].trim()));
			break;
		default:
			System.out.println("OPCAO INVALIDA: " + comando);
			break;
		}
	} 

	
	// Tenta registar uma nova organizacao
	private static void registarOrganizacao(String nomeOrganizacao, String endereco) {
		if (gestor.registarOrganizacao(nomeOrganizacao, endereco))
			System.out.println("REGISTO da organizacao " + nomeOrganizacao + 
					" com endereco Internet " + endereco);
		else 
			System.out.println("ERRO: Nao foi possivel registar a organizacao " + nomeOrganizacao);
	}

	// Tenta gerar um URL para uma organizacao
	private static void gerarUrl(String nomeOrganizacao) {
		Url url = null;
		if ((url =gestor.registarUrl(nomeOrganizacao)) != null)
			System.out.println("GERACAO do URL de " + nomeOrganizacao +  ", " +  url);
		else
			System.out.println("ERRO: Nao foi possivel gerar um URL para " + nomeOrganizacao);
	}
	
	// Tenta determinar o URL atribuido a uma organizacao
	private static void obterURL(String nomeOrganizacao) {
		Organizacao organization = gestor.organizacaoPorNome(nomeOrganizacao);
		Url url  = gestor.obterUrl(organization);
		if (url != null)
			System.out.println("A ORGANIZACAO " + nomeOrganizacao + 
					" tem atribuido o URL : " + url.obterEndereco());
		else
			System.out.println("ERRO: nao existe URL atribuido a " + nomeOrganizacao);
	}
	
	// Tenta obter organização que tem atribuída determinada posição num domínio
	private static void obterDono(String dominio, int numero) {
		Organizacao organization = gestor.obterDono(dominio, numero);
		if (organization != null){
			System.out.println("O DONO da posicao no." + numero + " no dominio " +  
					dominio + " e' : " + organization.obterNome());
		} else { 
			System.out.println("URL no." + numero + ", dominio " + 
					dominio  + ", SEM ORGANIZACAO atribuida");
		}
	}
	
	// Calcula valores totais de organizações registadas e de URL alojados
		private static void calcularTotal(String[] campos) {
			if (campos.length == 1){
				System.out.println("TOTAL de organizacoes registadas: " + gestor.nOrgsRegistadas());
				System.out.println("TOTAL de URL alojados: " + gestor.urlGerado());
			} else {
				String domain = campos[1];
				System.out.println("TOTAL de organizacoes registadas de dominio " 
						+ campos[1] + " : " + gestor.nOrgsRegistadasPorDominio(domain));
				System.out.println("TOTAL de URL alojados de dominio " + campos[1] + 
						" : " + gestor.urlGerado(domain));
			}
		}

	// Tenta determinar o dominio de uma organizacao
	private static void obterDominio(String nomeOrganizacao) {
		Organizacao organization = gestor.organizacaoPorNome(nomeOrganizacao);
		String domain  = gestor.obterEspacoDaOrg(organization);
		if (domain != null)
			System.out.println("A ORGANIZACAO " + nomeOrganizacao + 
					" esta' no DOMINIO : " + domain);
		else
			System.out.println("ERRO: Nao existe URL atribuido a " + nomeOrganizacao);
	}

	// Tenta remover um URL dado o nome da organizacao
	private static void removerPorNome(String nomeOrganizacao) {
		Organizacao org = gestor.organizacaoPorNome(nomeOrganizacao);
		String domain = null;
		if (org != null){
			domain = gestor.obterEspacoDaOrg(org);
			Url url = gestor.removerUrl(org); 
			if (url != null && domain != null){
				System.out.println("REMOCAO URL " + url.obterEndereco() + 
						", dominio " + domain + 
						", de " + nomeOrganizacao);
			} else {
				System.out.println("ERRO: Organizacao " + nomeOrganizacao + 
						" nao tem URL associado");
			}
		} else {
			System.out.println("ERRO: Organizacao " + nomeOrganizacao + 
					" desconhecida");
		}
	}  
	
	// Tenta remover um URL dados o domínio e a posicao dentro deste
	private static void removerPorDominio(String domain, int numero) {
		Organizacao organization = gestor.obterDono(domain, numero);
		Url url = null;
		if (organization != null && (url = gestor.removerUrl(organization)) != null){
			System.out.println("REMOCAO da posicao no." + numero + ", dominio " +  
					domain + ",  URL : " + url.obterEndereco() + 
					", de " + organization.obterNome());
		} else { 
			System.out.println("ERRO, URL no." + numero + ", espaco " + 
					domain  + ", SEM ORGANIZACAO atribuida");

		}
	}

}
