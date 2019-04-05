package fase4;
import java.util.Random;
public class Ambiente {
  private Posicao [] tabuleiro;
  private Random ger;
  private int linhasA;
  private int colunasA;
  public Ambiente (int linhas, int colunas, int[] monius, int[] jellies, int[] bunkers, int[] buracos, Random gerador) {
    this.tabuleiro = new Posicao [linhas*colunas];
    for (int i = 0; i < tabuleiro.length; i++) {
    boolean encontrou = false;
      for (int j = 0; j < monius.length && !encontrou; j++) {
        if (i == monius[j] - 1) {
          this.tabuleiro[i] = Posicao.MONIUS;
          encontrou = true;
        }
        else if (i == jellies[j] - 1) {
          this.tabuleiro[i] = Posicao.JELLYDZILLA;
          encontrou = true;
        }
        else if (i == bunkers[j] - 1) {
          this.tabuleiro[i] = Posicao.BUNKER;
          encontrou = true;
        }
        else if (i == buracos [j] - 1) {
          this.tabuleiro[i] = Posicao.BURACO_NEGRO;
          encontrou = true;
        }
        else {
          this.tabuleiro[i] = Posicao.NORMAL;
        }
      }
    }
    this.ger = gerador;
    this.linhasA = linhas;
    this.colunasA = colunas;
  }
  public int dimensao () {
    return this.tabuleiro.length;
  }
  public int linhas () {
    return this.linhasA;
  }
  public int colunas () {
    return this.colunasA;
  }
  public Posicao tipoDeCasa (int pos) {
    return this.tabuleiro[pos-1];
  }
  public boolean foiAtingido (int pos) {
    boolean atingido = false;
    for(int i = 0; i < this.tabuleiro.length && !atingido; i++) {
    	if (this.tabuleiro[i] == Posicao.MONIUS) {
    		atingido = FuncoesMonius4.foiAtingido(i + 1, pos, this.colunas()/3, 180.0, this.colunas(), this.linhas());
    	}
    }
    return atingido;
  }
  public boolean mesmaPosMonius (int pos) {
    boolean mesmaPos = false;
    for (int i = 0; i < this.tabuleiro.length && !mesmaPos; i++) {
      mesmaPos = (this.tabuleiro[pos - 1] == Posicao.MONIUS);
    }
    return mesmaPos;
  }
  public Posicao[] posicoes () {
    Posicao [] pos = new Posicao [this.tabuleiro.length];
    for(int i = 0; i < pos.length; i++) {
    	pos[i]=this.tabuleiro[i];
    }
    return pos;
  }
  public boolean haMonius () {
    boolean moniusVivos = false;
    for (int i = 0; i < this.tabuleiro.length && !moniusVivos; i++) {
      moniusVivos = (this.tabuleiro[i] == Posicao.MONIUS);
    }
    return moniusVivos;
  }
  public void fazJogadaAmbiente () {
    int salto;
    for (int i = 0; i < this.tabuleiro.length; i++) {
    	if (this.tabuleiro[i] == Posicao.MONIUS) {
    		salto = (ger.nextInt((2*colunas() + 1)) - colunas());
    		int novaPosMonius = i + salto;
    		if (this.tabuleiro[novaPosMonius] == Posicao.BURACO_NEGRO || novaPosMonius > this.dimensao() - 1 || novaPosMonius < 0) {
    			this.tabuleiro[i] = Posicao.NORMAL;
    		}
    		else {
    			this.tabuleiro[i] = Posicao.NORMAL;
    			this.tabuleiro[novaPosMonius] = Posicao.MONIUS;
    		}
    	}
    }
     
  }
  public int aleatorios(int inf, int sup, Random g){
    int aleatorio = g.nextInt(sup - inf + 1) + inf;;
    return aleatorio; 
  }
  public String toString () {
    StringBuilder sb = new StringBuilder("dimensao = " + this.dimensao() +
    "linhas = " + this.linhas() + "colunas = " + this.colunas() + "atingido = "
    + this.foiAtingido(1) + "mesmaPosMonius = " + this.mesmaPosMonius(1) + "ha Monius = " + this.haMonius());
    return sb.toString();

  }
}
