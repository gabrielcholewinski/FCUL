package fase4;
import java.util.Random;
import java.util.Scanner;
/**
 * Esta classe permite jogar o jogo Lightmare in Moniusland
 * Usa as classes que foram pedidas aos alunos na fase 4 do 
 * trabalho de IP
 * @author Isabel Nunes
 * @date Dezembro 2017
 */
public class LightmareMoniusland4 {

    // Simbolos usados no output do caminho
    public static final String SIMBOLOS_USADOS = "M@#_";
    // Simbolo que representa um Monius no caminho
    public static final char MONIUS = 'M';
    // Simbolo que representa um Monius na mesma casa que um ou mais Outros
    public static final char MONIUS_OUTRO = '@';
    // Simbolo que representa um ou mais Outros na mesma casa
    public static final char VARIOS_OUTROS = '#';
    // Simbolo que representa uma casa sem Monius nem Outros
    public static final char SEM_JOGADORES = '_';

    // Numero maximo de jogadas
    public static final int MAX_JOGADAS = 10;


    /**
     * Neste metodo eh lancado um jogo com varios Outros e um caminho
     * populado por varios Monius.
     * @param args Nao eh usado
     */
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Random gerador = new Random(1);

        /***** Definicao de valores iniciais para o jogo *****/
        // Numero de linhas e de colunas do caminho onde se desenrolarah o jogo
        int linhas = qualDimensao(leitor, "linhas");
        int colunas = qualDimensao(leitor, "colunas");
        int dim = linhas * colunas;
        // Valor de stamina igual para todos os Outros
        double stamina = dim * 2;
        // Salto maxima para os Outros 
        int saltoMax = colunas; 
        // Quantos de cada Monius, Jellydzilas, Bunkers e Buracos Negros
        int nEspeciais = Math.max(1, dim / 12);
        // Posicoes de todas as casas nao vazias (a primeira e a ultima ficam vazias)
        int[] posEspeciais = FuncoesMonius4.aleatoriosDiferentes(dim / 3, 2, dim - 1, gerador);
        // Distribuicao das posicoes especiais por posicoes de Monius, de Jellydzilas, 
        // de Bunkers e de Buracos Negros
        int[] posMonius = FuncoesMonius4.copiaParcial(posEspeciais, 0, nEspeciais);
        int[] posJZ = FuncoesMonius4.copiaParcial(posEspeciais, nEspeciais, nEspeciais);
        int[] posBU = FuncoesMonius4.copiaParcial(posEspeciais, 2 * nEspeciais, nEspeciais);
        int[] posBN = FuncoesMonius4.copiaParcial(posEspeciais, 3 * nEspeciais, nEspeciais);

        // informa quais as casas especiais
        System.out.print("Jellydzillas  ");
        FuncoesMonius4.imprimeVetor(posJZ);
        System.out.print("Bunkers  ");
        FuncoesMonius4.imprimeVetor(posBU);
        System.out.print("Buracos Negros  ");
        FuncoesMonius4.imprimeVetor(posBN);
        System.out.print("Monius ");
        FuncoesMonius4.imprimeVetor(posMonius);

        /***** Criacao de um jogo *****/
        Jogo meuJogo = new Jogo (linhas, colunas, posMonius,
                posJZ, posBU, posBN, gerador);
        // Pede ao user a info sobre os Outros e regista-a no jogo
        quemSaoOutros(meuJogo, stamina, leitor);

        /***** Mostrar representacao do jogo no standard output *****/
        // Nomes dos Outros em jogo
        String [] emJogo = meuJogo.outrosEmJogo();
        // Mostra o ambiente e as staminas ao user
        System.out.println(representacaoTextual(meuJogo, emJogo, linhas, colunas));
        imprimeStaminas(meuJogo, emJogo);

        /***** Jogar o jogo *****/
        System.out.println("VAMOS COMECAR O JOGO!");

        // Enquanto o jogo nao tiver terminado e nao forem feitas o maximo de
        // jogadas
        for (int i = 1 ; i < MAX_JOGADAS && !meuJogo.jahTerminou() ; i++){
            // Pede os saltos a todos os Outros em jogo
            int [] saltos = pedeSaltos(emJogo,meuJogo,saltoMax,dim,leitor);
            // Faz jogada
            meuJogo.fazJogada(saltos);

            // quais os Outros que morreram: atingidos pelo sabre ou que 
            // chocaram com um Monius ou que cairam num Buraco Negro
            String [] baixasOutros = meuJogo.baixasOutros(emJogo);
            informaBaixas(baixasOutros);

            // quais os Outros ainda em jogo 
            emJogo = meuJogo.outrosEmJogo();
            // mostra o ambiente ao utilizador
            System.out.println(representacaoTextual(meuJogo, emJogo, linhas, colunas));
            // mostra ao user a stamina de cada Outro em jogo 
            imprimeStaminas(meuJogo, emJogo);

        }

        // Mostra os resultados finais
        mostraResultados(meuJogo);
    }


    /***************************************************************************/
    /**************  METODOS PARA PEDIR VALORES AO UTILIZADOR  *****************/
    /***************************************************************************/	

    /**
     * Ler a dimensao do caminho do jogo
     * @param sc Canal de leitura
     * @return Dimensao do caminho (entre Jogo.MIN_DIMENSAO e Jogo.MAX_DIMENSAO)
     * @requires sc != null
     */
    private static int qualDimensao(Scanner sc, String dimensao){ 
        // Pede ao user a dimensao do caminho	    
        System.out.println("Qual o numero de " + dimensao + " do caminho? " +
                "(valor entre " + Jogo.MIN_DIMENSAO + " e " + 
                Jogo.MAX_DIMENSAO + ")");
        String erro = "Inteiro entre " + Jogo.MIN_DIMENSAO + " e " + 
                Jogo.MAX_DIMENSAO + "!";
        int result = FuncoesMonius4.lerValorNoIntervalo(Jogo.MIN_DIMENSAO,  
                Jogo.MAX_DIMENSAO, 
                erro, sc);
        sc.nextLine();
        return result;
    }

    /**
     * Pede ao user os nomes e as posicoes iniciais dos Outros e regista-os
     * no jogo
     * @param g O jogo
     * @param dim Numero de posicoes do caminho
     * @param stamina Valor inicial de stamina para os Outros
     * @param sc Canal de leitura
     * @requires g != null && sc != null
     */
    private static void quemSaoOutros(Jogo g, double stamina, Scanner sc) {

        System.out.println("Quantos Outros?");
        int nOutros = 
                FuncoesMonius4.lerValorNoIntervalo(1, Jogo.MAX_OUTROS, 
                        "Inteiro entre " + 1 + " e " + 
                                Jogo.MAX_OUTROS + "!", 
                                sc);

        // primeiro consome o fim de linha que ficou no canal de leitura
        sc.nextLine();

        // Para cada Outro em jogo, pede nome e simbolo que o representa e 
        // regista essas infos no jogo
        for (int i = 1 ; i <= nOutros ; i++) {
            // Pede ao jogador o nome e o simbolo que o representa
            System.out.println("Outro " + i + ": qual o seu nome?");
            String nome = sc.nextLine();
            char simbolo = pedeSimboloOutro (nome,sc);
            g.registaOutro (nome, simbolo, stamina);
        }
    }

    /**
     * Simbolo que representarah um dado Outro, lida atraves do standard input
     * Terah que ser diferente dos simbolos em SIMBOLOS_USADOS
     * @param nome Nome do Outro a quem eh pedida a informacao
     * @param sc Canal de leitura
     * @return Um carater que representarah esse Outro no Caminho
     * @requires nome != null && sc != null
     */
    private static char pedeSimboloOutro(String nome, Scanner sc){
        System.out.println(nome.toUpperCase() + ", qual o simbolo que o vai representar? ");
        char simbolo;
        do {
            simbolo = sc.nextLine().charAt(0);
        } while(SIMBOLOS_USADOS.indexOf(simbolo) != -1);

        return simbolo;
    }

    /**
     * Pede e valida os saltos dos Outros e devolve-os
     * @param emJogo Os nomes dos Outros que ainda estao em jogo
     * @param g O jogo
     * @param saltoMax Valor maximo para o salto dos Outros
     * @param dim Dimensao do Caminho
     * @param sc Canal de leitura
     * @requires emJogo != null && g != null && sc != null
     * @return Um array com os saltos que deverao dar todos os Outros ainda em jogo
     */
    private static int[] pedeSaltos (String[] emJogo, Jogo g, int saltoMax, 
            int dim, Scanner sc){
        // ficam todos a zero
        int[] saltos = new int[emJogo.length];  
        System.out.println("Outros, decidam os vossos saltos! ");
        // Para cada Outro em jogo	    
        for (int i = 0 ; i < emJogo.length ; i++){
            int posO = g.posicaoOutro(emJogo[i]);
            // Pede salto ao Outro   
            System.out.println(emJogo[i].toUpperCase() + " - posicao atual: " + posO);

            // Valores minimo e maximo para movimento horizontal
            int inf = -(posO <= saltoMax ? posO - 1 : saltoMax);
            int sup = dim - posO <= saltoMax ? dim - posO : saltoMax;
            System.out.println("Salto? (Valor entre " + inf + " e " + sup + ")");
            int salto = 
                    FuncoesMonius4.lerValorNoIntervalo(inf, sup,
                            "Salto? (Valor entre " + inf + " e " + sup + ")",
                            sc);
            saltos[i] = salto;
        }
        return saltos;
    }


    /***************************************************************************/
    /******** METODOS PARA IMPRIMIR A REPRESENTACAO TEXTUAL DO JOGO ************/
    /***************************************************************************/		

    /**
     * Representacao textual do caminho com as posicoes dos Monius,
     * das Jellydzillas, dos Bunkers e dos Buracos Negros
     * @param jogo - O jogo
     * @param emJogo - Nomes dos Outros ainda em jogo
     * @param linhas - O numero de linhas do caminho
     * @param colunas - O numero de colunas do caminho
     * @return Uma string com a representacao do caminho
     * @requires jogo != null && emJogo != null
     */
    private static String representacaoTextual (Jogo jogo, String[] emJogo, 
            int linhas, int colunas) {

        // pede ao jogo as posicoes do caminho (incluem as dos Monius)
        Posicao[] caminho = jogo.posicoesCaminho();
        // constroi um caminho preenchido com posicoes normais
        StringBuilder caminhoOutput = tudoNormal(linhas * colunas);

        // preenche as posicoes dos Monius vivos
        for(int i = 0 ; i < caminho.length ; i++) {
            if(caminho[i] == Posicao.MONIUS) {
                caminhoOutput.setCharAt(i, MONIUS);
            }
        }

        // agora preenche os Outros ainda em jogo
        for(String nome : emJogo) {
            int posicao = jogo.posicaoOutro(nome);
            if(caminhoOutput.charAt(posicao - 1) == '_'){
                caminhoOutput.setCharAt(posicao - 1, jogo.simboloOutro(nome));
            } else if(caminhoOutput.charAt(posicao - 1) == 'M'){
                caminhoOutput.setCharAt(posicao - 1,MONIUS_OUTRO);
            } else {
                caminhoOutput.setCharAt(posicao - 1,VARIOS_OUTROS);
            }
        }

        // agora insere os fins de linha e inverte as linhas pares
        StringBuilder resultado = new StringBuilder();
        for (int l = 1 ; l <= linhas ; l++ ){
            StringBuilder linha = new StringBuilder();
            linha.append(caminhoOutput.substring((l - 1) * colunas, l * colunas));
            if(l % 2 == 0){
                linha.reverse();
            }
            resultado.append(linha.toString() + "\n");
        }

        return resultado.toString();
    }

    /**
     * Um StringBuilder contendo um dado numero de casas sem jogadores
     * @param n
     * @return String formada por n simbolos SEM_JOGADORES
     */
    private static StringBuilder tudoNormal(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1 ; i <= n ; i++) {
            sb.append(SEM_JOGADORES);
        }
        return sb;
    }

    /**
     * Imprime os Outros que cairam num Buraco Negro ou que foram atingidos 
     * ou chocaram com Monius na presente jogada
     * @param baixas Os nomes dos Outros que foram atingidos
     * @requires baixas != null
     */
    private static void informaBaixas(String[] baixas) {
        for (String nome: baixas){
            System.out.println("====== O outro " + nome.toUpperCase() + 
                    " jah nao estah em jogo ======");  		    
        }

    }

    /**
     * Imprime os valores de stamina para cada Outro em jogo
     * @param g O jogo
     * @param emJogo Os nomes dos Outros que ainda estao em jogo
     * @requires g != null && emJogo != null
     */
    private static void imprimeStaminas (Jogo g, String[] emJogo){
        // Para cada Outro em jogo	    
        for (String nome: emJogo){
            System.out.println("Stamina de: " + nome.toUpperCase() + 
                    " = " + g.staminaOutro(nome));  		    
        }
        System.out.println("==============================================");
    }

    /***************************************************************************/
    /****************** METODO PARA IMPRIMIR RESULTADOS DO JOGO ****************/
    /***************************************************************************/
    /**
     * Imprime os resultados do jogo
     * @param g O jogo
     * @requires g != null
     */
    private static void mostraResultados (Jogo g){

        System.out.println("Acabou o jogo!");
        EstadoJogo estadoFinal = g.estado();

        switch (estadoFinal) {

        case NAO_HA_OUTROS:
            System.out.println("Parabens aos Monius! Os outros morreram!");
            break;
        case OUTRO_TERMINOU: 
            String[] vencedores = g.vencedores();
            System.out.print("Parabens ");  
            for(String nome : vencedores){
                System.out.print(nome + ", ");
            }
            System.out.println((vencedores.length > 1 ? "chegaram" : "chegou") + " ao fim!");  
            break;
        case MAX_JOGADAS: case NAO_HA_MONIUS:
            String[] emJogo = g.outrosEmJogo();
            System.out.print("Parabens ");  
            for(String nome : emJogo){
                System.out.print(nome + ", ");
            }
            System.out.println((emJogo.length > 1 ? "ganharam" : "ganhou") + "!");  
            break;
        case NAO_HA_NINGUEM:
            System.out.println("Ninguem ganhou!");
            break;
        default:  
            System.out.println("Algo de estranho se passou...");
            break;

        }

        // Nomes de todos os Outros que estiveram a jogar
        String [] nomes = g.todosOsOutros();
        // Qual o estado de cada um desses Outros no fim do jogo
        EstadoOutro[] estados = g.estadoDosOutros();

        for (int i = 0 ; i < estados.length ; i++) {
            System.out.println("O Outro " + nomes[i] + " ficou no estado " + 
                    estados[i] + " no fim do jogo.");
        }
    }

}