package src;


public class Paciente implements Cloneable{
    private String nome;
    private int ordemChegada;
    private Especialidade esp;
    private Prioridade pri;
   
    /**
     * Construtor de um paciente
     * @param nome - o nome do paciente
     * @param ordemChegada - o numero de registo no sistema
     * @requires  nome != null, ordemChegada > 0
     */
    public Paciente(String nome, int ordemChegada) {
        this.nome = nome;
        this.ordemChegada = ordemChegada;
        this.pri = Prioridade.NORMAL;
    }

    /**
     * Devolve o nome deste paciente
     * @return o nome do paciente
     */
    public String obtemNome () {
        return this.nome;
    }

    /**
     * Devolve a ordem deste paciente
     * @return a ordem deste paciente
     */
    public int obtemOrdem () {
        return this.ordemChegada;
    }

    /**
     * Atribui uma especiadade a este paciente
     * @param esp a especialidade a atribuir
     * @requires esp != null
     */
    public void atribuiEspecialidade (Especialidade esp) {
        this.esp = esp;
    }

    /**
     * Obtem a especialidade atribuida a este paciente
     * @return a especialidade atribuida ao paciente
     */
    public Especialidade obtemEspecialidade () {
        return this.esp;
    }

    /**
     * Atribui uma prioridade a este paciente
     * @param pri a prioridade a atribuir
     * @requires pri != null
     */
    public void atribuiPrioridade (Prioridade pri) {
        this.pri = pri;
    }

    /**
     * Obtem a prioridade atribuida a este paciente
     * @return a prioridade atribuida ao paciente
     */
    public Prioridade obtemPrioridade () {
        return this.pri;
    }


    /**
     * Representacao textual deste paciente
     * Exemplos:    (se ainda nao tem especialidade)   
     *                      Pacinte Marco, ordem 3
     *              (se ja tem especialidade)          
     *                      Pacinte Marco, ordem 3, CIRURGIA, ALTA
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Paciente "+this.nome+", ordem "+this.ordemChegada);
        if(this.esp != null) {
            sb.append(", "+this.esp+", "+this.pri);
        }
        sb.append("\n");
        return sb.toString();
    }


    /**
     * Diz se um dada objecto eh igual a este paciente
     * @requires other != null
     */
    @Override
    public boolean equals(Object other){
        boolean result = false;
        Paciente otherP = (Paciente)other;
        if(otherP.esp == this.esp && otherP.nome == this.nome &&
           otherP.ordemChegada == this.ordemChegada &&
           otherP.pri == this.pri) {
            result = true;
        }
        return result;
    }


    /**
     * Devolve uma copia deste paciente
     * @throws CloneNotSupportedException
     */
    @Override
    public Paciente clone() throws CloneNotSupportedException {
        Paciente p = null;
        try {
            p = (Paciente)super.clone();
            p.atribuiEspecialidade(this.esp);
            p.atribuiPrioridade(this.pri);
            return p;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }

}
