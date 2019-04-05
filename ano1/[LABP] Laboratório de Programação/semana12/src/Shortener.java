import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.print.attribute.standard.NumberOfDocuments;

/**
 * 
 * @author 
 *
 */

public class Shortener {
    private List<String> domains;

    private BidirectionalHashMap<Url, Organizacao> gestor;
    private int numOrgs;
    private int numUrls;
    private ArrayList<Organizacao> orgs;
    private int[] numDomainsOrgs;
    private int[] numDomainsUrls;
    private int max;

    /**
    * Construtor
    * @param domains uma lista de dominios de topo genericos
    * @param numeroEnderecos o numero maximo de endereços alojados
    *
    */

    public Shortener(List<String> domains, int numeroEnderecos) {
        this.domains = domains;
        gestor = new BidirectionalHashMap<>();
        numOrgs = 0;
        numUrls = 0;
        orgs = new ArrayList<>();
        numDomainsOrgs = new int[domains.size()];
        numDomainsUrls = new int[domains.size()];
        max = numeroEnderecos/domains.size();
    }


    /**
    * Regista uma organização
    * @param nomeOrganizacao o nome da organização
    * @param address o endereço internet da organização
    * @return true se a operação tem sucesso
    * @requires nomeOrganizacao != null && address != null
    * @requires address no formato xxx.yyy...zzz.gtld
    * @requires gtld in http://data.iana.org/TLD/tlds-alpha-by-domain.txt
    *
    */
    public boolean registarOrganizacao(String nomeOrganizacao,  String address) {
        Organizacao orgAux = new Organizacao(nomeOrganizacao, address);
        if(!gestor.containsValue(orgAux) && domains.contains(orgAux.obterDominio())) {//tenho que saber se os dominios ja fora todos ocupados MAX_VEZES
        	numDomainsOrgs[domains.indexOf(orgAux.obterDominio())]++;
            orgs.add(orgAux);
            gestor.put(null, orgAux);
            numOrgs++;
            return true;
        }
        return false;
    }



    /**
    * Regista um Url
    * @param nomeOrganizacao o nome da Organizacao
    * @return o Url associado 'a Organização nomeOrganizacao, ou null
    *
    */
    public Url registarUrl(String nomeOrganizacao) {
        Organizacao orgAux = organizacaoPorNome(nomeOrganizacao);
        if(gestor.containsValue(orgAux) && urlGerado(orgAux.obterDominio()) < max  ) {
            Url urlAux = new Url(Integer.toHexString(orgAux.hashCode())+"."+orgAux.obterDominio(), numUrls % domains.size() + 1);
            gestor.put(urlAux, orgAux);
            numDomainsUrls[domains.indexOf(orgAux.obterDominio())]++;
            numUrls++;
            return urlAux;
        }
        return null;
    } 
    /**
    * Remove um Url
    * @param nomeOrganizacao o nome da organização
    * @return o Url associado 'a Organizacao organization, ou null
    *
    */
    public Url removerUrl(Organizacao organization){
        numUrls--;
        return gestor.containsValue(organization) ? gestor.removeByValue(organization) : null; 
    }

    /**
    * Devolve a Organizacao dona de uma posição num dominio de topo
    * @param domain o dominio
    * @param numero a posição
    * @return a instancia de Organização que detem a posicao numero
    * no dominio domain, ou null
    *
    *
    */
    public Organizacao obterDono(String domain, int numero) {
    		for(Url url: gestor.getKeys()) {
    			int aux = obterUrl(gestor.getValue(url)).obterEnderecoIP().charAt(8)-48;
    			if(gestor.getValue(url).obterDominio().equals(domain) &&
    					aux == numero)
    				return gestor.getValue(url);
    		}
    		return null;
    }

    /**
    * Devolve a Organizacao associada a um nome
    * @param nomeOrganizacao o nome
    * @return a Organizacao com nome nomeOrganizacao, ou null
    *
    *
    */
    public Organizacao organizacaoPorNome(String nomeOrganizacao){
        for(int i=0 ; i<orgs.size() ; i++)
            if(orgs.get(i).obterNome().equals(nomeOrganizacao))
                return orgs.get(i);
        return null;
    }

    /**
    * Devolve o Url associado a uma Organizacao
    * @param organizacao a Organizacao
    * @return o Url associado 'a organizacao, ou null
    * *
    */
    public Url obterUrl(Organizacao organizacao){
        return gestor.getKey(organizacao);
    }

    /**
    * Devolve o dominio (ou espaco) do Url
    * @param url o Url
    * @return o dominio do url, ou null
    *
    *
    */
    public String obterEspacoDoUrl(Url url){
        if(gestor.containsKey(url))
            return gestor.getValue(url).obterDominio();
        return null;
    }

    /**
    * Devolve o dominio  (ou espaco) do Url associado 'a organizacao
    * @param organization o nome da Organizacao
    * @return o dominio do Url da Organizacao organization, ou null
    *
    * *
    */
    public String obterEspacoDaOrg(Organizacao organization){
        if(gestor.containsValue(organization))
            return organization.obterDominio();
        return null;
    }

    /**
    * Devolve o numero de Url's alojados
    * @return o numero de Url's alojados

    */
    public int urlGerado(){
        return numUrls;
    }

    /**
    * Devolve o numero de Url's alojados num dominio de topo
    * @param domain o dominio de topo
    * @return o numero de Url's alojados no dominio domain
    *
    */
    public int urlGerado(String domain){ 
        for(int i=0 ; i<domains.size() ; i++)
            if(domains.get(i).equals(domain))
                return numDomainsUrls[i];
        return 0;
    }

    /**
    * Devolve o numero de organizaçoes registadas
    * @return o numero de organizaçoes registadas
    *
    *
    */
    public int nOrgsRegistadas(){
        return numOrgs;
    }

    /**
    * Devolve o numero de organizaçoes registadas num dominio de topo
    * @param domain o dominio de topo
    * @return o numero de organizaçoes registadas no dominio domain
    *
    *
    */
    public int nOrgsRegistadasPorDominio(String domain){
        if(!domains.contains(domain)) {
            return 0;
        }
        return numDomainsOrgs[domains.indexOf(domain)];
    }
}