package src;

import java.util.ArrayList;

public class GestorDeConsultas {
    private Medico[] equipe;
    ArrayList<Paciente> pacEspera;
    private int nPaciente;
    int ordemPac;

    /**
     * Inicializa o um servico de consultas para um dia
     * @param equipe - a equipe de medicos que estara de servico
     * @requires equipe != null && para qualquer i, equipe[i] != null
     */
    public GestorDeConsultas (Medico[] equipe) {
        this.equipe = equipe;
        this.pacEspera = new ArrayList<>();
        this.nPaciente = 0;
        this.ordemPac = 1;
    }


    /**
     * Regista um paciente na clinica
     * @param nome - o nome do paciente
     * @requires nome != null && nome nao existe ja na clinica
     */
    public void registaPaciente(String nome) {
        this.nPaciente++;
        this.pacEspera.add(new Paciente(nome, this.ordemPac++));
    }

    /**
     * Regista a passagem do paciente pela triagem e atribui-o a um medico que o atendera mais rapidamente
     * de acordo comindex o criterio definido no enunciado
     * @param nome o nome do paciente
     * @param esp a especialidade ja atribuida
     * @param pri a prioridade ja atribuida
     * @requires nome, esp, pri != null
     */
    //regista o valor da triagem e atribui a um medico
    public void registaTriagem (String nome, Especialidade esp, Prioridade pri) {
        Paciente pac = findPac(nome);
        pac.atribuiEspecialidade(esp);
        pac.atribuiPrioridade(pri);
        ArrayList <Medico> med= medicosEsp(esp);
        Medico medico = medicosEsp(esp).get(0);

        for(int i=1 ; i<med.size() ; i++) {
            if(medicosEsp(esp).get(i).cargaDeServico(pri) < medico.cargaDeServico(pri)) {
                medico = medicosEsp(esp).get(i);
            }
        }
        medico.registaPaciente(pac);
    }

    private ArrayList <Medico> medicosEsp(Especialidade esp) {
        ArrayList <Medico> med= new ArrayList <>();
        int index = 0;
        for(int i=0 ; i<this.equipe.length ; i++) {
            if(this.equipe[i].obtemEspecialidade() == esp) {
                med.add(index, this.equipe[i]);
                index++;
            }
        }
        index = 0;
        if(med.isEmpty()) {
            for(int i=0 ; i<this.equipe.length ; i++) {
                if(this.equipe[i].obtemEspecialidade() == Especialidade.CLINICA_GERAL) {
                    med.add(index, this.equipe[i]);
                    index++;
                }
            }
        }
        return med;
    }

    private Paciente findPac(String nome) {
        Paciente aux = null;
        for(int i=0 ; i<this.pacEspera.size() ; i++) {
            if(this.pacEspera.get(i).obtemNome().equals(nome)) {
                aux = this.pacEspera.get(i);
            }
        }
        return aux;
    }

    /**
     * Devolve o numero de ordem de um dado paciente
     * @param nome o nome do paciente
     * @return o numero que o paciente com o nome dado tem atribuido
     * @requires nome != null && existe na lista de pacientes da clinica
     */
    public int ordemPaciente (String nome) {
        return findPac(nome).obtemOrdem();
    }



    /**
     * Diz quantos paciente ainda ha na clinica
     * @return o numero de pacientes ainda na clinica
     */
    public int numeroPacientesNaClinica() {
        return this.nPaciente;
    }


    /**
     * Informa o nome dos medicos com mais que i pacientes
     * (notar que um paciente com prioridade Alta vale por 3 Normais)
     * @param i o numero minimo de pacientes
     * @return o nome dos medico com mais servico
     * @requires i >= 0
     */
    public String[] medicosComoMaisDoQueIPacientes(int i){
    	int c = 0;
        for (int j=0 ; j<equipe.length ; j++) {
            if (equipe[j].cargaDeServico(Prioridade.NORMAL) > i) {
                c++;
            }
        }
        String[] result = new String[c];
        c = 0; //reutiliza a variável
        for (int j = 0; j < equipe.length; j++) {
            if (equipe[j].cargaDeServico(Prioridade.NORMAL) > i) {
                result[c] = equipe[j].obtemNome();
                c++;
            }
        }
        return result;
    }

    /**
     * Dado o nome do medico retira o paciente que por ordem de atendimento sera visto
     * pelo medico
     * @param nome - o nome do medico que terminou a observacao a um paciente
     * @requires nome != null e corresponde a um dos medicos da equipe
     */
    public int proximoPaciente(String nome){
        int result = 0;
        for(int i=0 ; i<this.equipe.length ; i++) {
            if(this.equipe[i].obtemNome() == nome) {
                result = this.equipe[i].proximoPaciente().obtemOrdem();
                this.nPaciente--;
                equipe[i].observaPaciente();
                removePac(result);
            }
        }
        return result;

    }

    private Medico medPorNome(String nome) {
        Medico result = null;
        for(int i=0 ; i<this.equipe.length ; i++) {
            if(this.equipe[i].obtemNome() == nome) {
                result = this.equipe[i];
            }
        }
        return result;
    }

    private void removePac(int ordem) {
        for(int i=0 ; i<this.pacEspera.size() ; i++) {
            if(this.pacEspera.get(i).obtemOrdem() == ordem) {
                this.pacEspera.remove(i);
            }
        }
    }

    /**
     * Devolve o nome do paciente ah espera ha mais tempo
     * @return o nome do paciente mais tempo a espera
     * @requires numeroPacientesNaClinica > 0
     */
    public String pacienteMaisTempoAEspera() {
        return pacEspera.get(0).obtemNome();
    }

    /**
     * Um dado medico ainda tem pacientes por ver?
     * @param nome - o nome do medico
     * @return true sse o medico nome ainda tem pacientes por ver
     * @requires nome!= null e nome pertence ah equipe
     */
    public boolean medicoTemPacientes(String nome) {
        boolean result = false;
        for(int i=0 ; i<this.equipe.length ; i++) {
            if(this.equipe[i].obtemNome() == nome) {
                result = equipe[i].aindaTemPacientes();
            }
        }
        return result;
    }

    /**
     * Devolve o nome dos medicos que estao de servico
     * @return o vector com o nome dos medicos
     */
    public String[] medicos () {
    	int c = 0;
        for(int i=0 ; i<equipe.length ; i++) {
        	if(equipe[i].numeroPacientes() > 0) {
        		c++;
        	}
        }
        
        String[] result = new String[c];
        c = 0;
        for(int i=0 ; i<equipe.length ; i++) {
        	if(equipe[i].numeroPacientes() > 0) {
        		result[i] = equipe[i].obtemNome();
        	}
        }
        return result;
    }

    /**
     * Devolve o nome dos pacientes que estao na clinica
     * @return o vector com o nome dos pacientes
     */
    public String[] pacientes () {
    	String[] result = new String[pacEspera.size()];
    	for(int i=0 ; i<pacEspera.size() ; i++) {
    		result[i] = pacEspera.get(i).toString();
    	}
    	return result;
    }


    /**
     * Representacao textual do Gestor de consultas.
     */
    @Override
    public String toString(){
    	StringBuilder sb = new StringBuilder("########\n");
    	for(int i=0 ; i<equipe.length ; i++) {
    		sb.append(equipe[i].obtemEspecialidade()+"\n"+
    					medicos()[i]+"\n"+
    					pacientes()[i]);
    	}
    	return sb.toString();
    }
}
