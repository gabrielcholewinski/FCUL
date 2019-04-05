package fase4;
import java.util.Random;
public class Jogo {
  public String nome;
  public char simb;
  public double stamina;
  public Ambiente jogo;
  public int dimensao;
  public Outro [] vetorOutros;
  public static final int MAX_OUTROS = 5;
  public static final int MAX_DIMENSAO = 40;
  public static final int MIN_DIMENSAO = 4;
  public int numOutros;
  public EstadoJogo estadoJogo;
  public int numVencedores;
  public Jogo (int linhas, int colunas, int[] posMonius, int[] posJZ,
  int[] posBU, int[] posBN, Random gerador) {
    this.jogo = new Ambiente(linhas, colunas, posMonius, posJZ, posBU, posBN, gerador);
    this.dimensao = linhas*colunas;
    this.vetorOutros = new Outro[MAX_OUTROS];
    this.numOutros = 0;
    this.estadoJogo = EstadoJogo.EM_CURSO;
  }
  public int dimensao () {
    return this.dimensao;
  }
  public Posicao [] posicoesCaminho () {
    return this.jogo.posicoes();
  }
  public EstadoJogo estado() {
    return this.estadoJogo;
  }
  public int outrosVivos () {
    int outrosVivos = this.numOutros;
    for (int i = 0; i < this.numOutros; i++) {
      if (!this.vetorOutros[i].emJogo()) {
        outrosVivos--;
      }
    }
    return outrosVivos;
  }
  public void registaOutro (String nome, char simb, double stamina) {
    this.vetorOutros[this.numOutros] = new Outro (nome, simb, stamina);
    numOutros++;
  }
  public boolean jahTerminou () {
    return this.estado() != EstadoJogo.EM_CURSO;
  }
  public int encontraNome (String nome) {
	int contador = 0;
    for (int i = 0; i < this.numOutros; i++) {
      if (this.vetorOutros[i].nome() == nome) {
        contador = i;
      }
    }
    return contador;
  }
  public int posicaoOutro (String nome) {
    int indice = encontraNome(nome);
    return this.vetorOutros[indice].posicao();
  }
  public char simboloOutro (String nome) {
    int indice = encontraNome(nome);
    return this.vetorOutros[indice].simbolo();
  }
  public double staminaOutro (String nome) {
    int indice = encontraNome(nome);
    return this.vetorOutros[indice].stam;
    
  }
  public EstadoOutro estadoOutro (String nome) {
    int indice = encontraNome(nome);
    return this.vetorOutros[indice].estado();
  }
  public boolean chegouFim () {
    for (int i = 0; i < this.numOutros; i++) {
      if (this.vetorOutros[i].posicao() == dimensao()) {
        numVencedores++;
        return true;
      }
    }
    return false;
  }
  public boolean protegido (int pos) {
    return ((this.jogo.tipoDeCasa(pos)== Posicao.JELLYDZILLA) || (this.jogo.tipoDeCasa(pos) == Posicao.BUNKER));
  }
  public String[] outrosEmJogo() {
    String[] outrosJogando = new String[outrosVivos()];
    int contador = 0;
    for(int j = 0; j < this.numOutros; j++) {
        if(this.vetorOutros[j].emJogo()) {
          outrosJogando[contador] = this.vetorOutros[j].nome();
          contador++;
        }
      }
    return outrosJogando;
  }
  /**
   * @return vector de Strings cujo conteudo sao
   *    os Outros que foram registrados
   */
  public String[] todosOsOutros() {
    String[] outrosNoJogo = new String[this.numOutros];
    for(int i = 0; i < this.numOutros; i++) {
      outrosNoJogo[i] = this.vetorOutros[i].nome();
    }
    return outrosNoJogo;
  }
  /**
   * @requires vs != null
   * @param vs
   * @return vector com os Outros que morreram
   */
   public String[] baixasOutros(String[] vs) {
    String [] outrosMortos = new String[this.numOutros - outrosVivos()];
    int indice;
    int contador = 0;
    if (outrosMortos.length != 0) {
      for (int i = 0; i < vs.length; i++){
        indice = encontraNome(vs[i]);
        if (!this.vetorOutros[indice].emJogo()) {
          outrosMortos[contador] = this.vetorOutros[indice].nome();
          contador++;
        }
      }
    }
    return outrosMortos;
 }
  /**
   * @return vector do tipo EstadoOutro contido todos os Estados
   *    dos outros registrados
   */
  public EstadoOutro[] estadoDosOutros() {
   EstadoOutro[] EstadoOutros = new EstadoOutro[this.numOutros];
   for(int i = 0; i < EstadoOutros.length ; i++) {
    for(int j = 0 ; j < this.numOutros; j++) {
     EstadoOutros[i] = this.vetorOutros[j].estadoOutro;
    }
   }
   return EstadoOutros;
  }
  /**
   * @return Outros que venceram
   * @requires fim de jogo
   */
  public String[] vencedores() {
   String[] winners = new String[this.outrosEmJogo().length];
    for(int i = 0; i<this.numOutros; i++) {
     if(this.vetorOutros[i].estado() == EstadoOutro.CHEGOU) {
      winners[i] = this.vetorOutros[i].nome();
     }else {
      winners = new String[0];
     }
    }
    return winners;
  }
  public void fazJogada(int[] saltos) {
    this.jogo.fazJogadaAmbiente();
    for (int i = 0; i < saltos.length; i++) {
    	int indice = encontraNome(outrosEmJogo()[i]);
    	this.vetorOutros[indice].salta(saltos[indice]);
    	this.vetorOutros[indice].mudaStamina(saltos[indice]);
    	if(this.jogo.foiAtingido(this.vetorOutros[indice].posicao()) && protegido(this.vetorOutros[indice].posicao())) {
            this.vetorOutros[indice].atingidoPorSabre();
          }
          else if (this.jogo.mesmaPosMonius(this.vetorOutros[indice].posicao()) && !protegido(this.vetorOutros[indice].posicao())) {
            this.vetorOutros[indice].chocouMonius();
          }
          else if (chegouFim()) {
            this.vetorOutros[indice].chegou();
          }
          else if (this.jogo.tipoDeCasa(vetorOutros[indice].posicao()) == Posicao.BURACO_NEGRO) {
            this.vetorOutros[indice].caiuBuracoNegro();
          }
          else if (this.jogo.tipoDeCasa(vetorOutros[indice].posicao()) == Posicao.JELLYDZILLA) {
            this.vetorOutros[indice].mudaStamina(-2*saltos[indice]);
          }
    }
    
    if(!this.jogo.haMonius() && outrosVivos() != 0) {
      this.estadoJogo = EstadoJogo.NAO_HA_MONIUS;
    }
    else if(outrosVivos() == 0 && this.jogo.haMonius()) {
      this.estadoJogo = EstadoJogo.NAO_HA_OUTROS;
    }
    else if(!this.jogo.haMonius() && outrosVivos() == 0) {
      this.estadoJogo = EstadoJogo.NAO_HA_NINGUEM;
    }
    else if (chegouFim()) {
      this.estadoJogo = EstadoJogo.OUTRO_TERMINOU;
    }
  }
}
