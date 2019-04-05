package src;
/**
 * Classe que representa um medico
 * @author
 *
 */
public class Medico implements Cloneable {
    private String nome;
    private Especialidade esp;
    private int nPacientes;
    private Queue<Paciente> filaPacAlto;
    private Queue<Paciente> filaPacNormal;

    /**
     * Constroi um medico dado o seu nome e especialidade
     * @param nome - o nome do medico
     * @param esp - a especialidade do medico
     * @requires nome, esp != null
     */
    public Medico(String nome, Especialidade esp) {
        this.nome = nome;
        this.esp = esp;
        this.nPacientes = 0;
        this.filaPacAlto = new Queue();
        this.filaPacNormal = new Queue();
    }

    /**
     * Devolve o nome deste medico
     * @return o nome deste medico
     */
    public String obtemNome () {
        return this.nome;
    }

    /**
     * Devolve a especialide deste medico
     * @return a especialidade deste medico
     */
    public Especialidade obtemEspecialidade () {
        return this.esp;
    }

    /**
     * Devolve o numero de pacientes ah espera para serem
     * vistos por este medico
     * @return o numero de pacientes ah espera deste medico
     */
    public int numeroPacientes () {
        return this.nPacientes;
    }

    /**
     * Atruibui um dado paciente a este medico
     * @param pac - o paciente a atribui a este medico para ser observado por ele
     * @requires pac != null
     */
    public void registaPaciente (Paciente pac) {
        if(pac.obtemPrioridade() == Prioridade.ALTA) {
            this.filaPacAlto.enqueue(pac);
        }else {
            this.filaPacNormal.enqueue(pac);
        }
        this.nPacientes++;
    }

    /**
     * Informa a carga de servico deste medico de acordo com a prioridade
     * Cada paciente com prioridade alta vale por 3 de prioridade normal
     * @param pri a prioridade da qual se pretende saber a carga
     * @return o numero ponderado de pacientes de acordo com a prioriade atribuida
     * @requires pri != null
     */
    public int cargaDeServico (Prioridade pri) {
        int n = this.filaPacAlto.size()*3;
        if(pri == Prioridade.ALTA) {
            return n;
        }else if(pri == Prioridade.NORMAL) {
            n += this.filaPacNormal.size();
        }
        return n;
    }

    /**
     * Ainda tem pacientes por ver?
     * @return true sse ainda nao observou todos os pacientes que lhe foram atribuidos
     */
    public boolean aindaTemPacientes () {
        return this.nPacientes != 0;
    }

    /**
     * Qual o proximo paciente a ser observado por este medico
     * @return copia do proximo paciente a ser visto por este medico
     * @requires aindaTemPacientes()
     */
    public Paciente proximoPaciente () {
        if(!this.filaPacAlto.isEmpty()) {
            return this.filaPacAlto.front();
        }else {
            return this.filaPacNormal.front();
        }
    }

    /**)
     * Finaliza o atendimento de um paciente retirando-o da sua lista de espera
     * @return o paciente que acabou de ser observado por este medico
     * @requires aindaTemPacientes()
     */
    public Paciente observaPaciente () {
        Paciente aux;
        if(!this.filaPacAlto.isEmpty()) {
            aux = this.filaPacAlto.front();
            this.filaPacAlto.dequeue();
        }else {
            aux = this.filaPacNormal.front();
            this.filaPacNormal.dequeue();
        }
        this.nPacientes--;
        return aux;
    }

    /**
     * Representacao textual deste medico
     */
    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();
        sb.append(this.nome+"\n");
        if(aindaTemPacientes()) {
            sb.append(observaPaciente().toString());
        }else {
            sb.append("Sem pacientes ah espera.");
        }
        return sb.toString();
    }

}

