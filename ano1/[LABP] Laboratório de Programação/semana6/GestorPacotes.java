import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorPacotes {

	/**
	 * Recebe uma string correspondente a uma linha do ficheiro de items e
	 * devolve um par do tipo String e Double onde a String é o nome do item e o
	 * Double corresponde ao seu peso.
	 * @param str string onde um item está codificado
	 * @return Um par representando o item e o seu peso
	 */
	public static Par<String, Double> parseItem(String str){
		String[] parseString = str.split(";");
		Par<String,Double> fruta = new Par<String,Double>( 
				parseString[0],Double.parseDouble(parseString[1]));
		return fruta;
	}

	/**
	 * Lê items de um ficheiro dado um BufferedReader e cria uma lista de
	 * pacotes com a capacidade máxima recebida
	 * @param fileReader BufferedReader para o ficheiro onde os items estão
	 * armazenados
	 * @param capacidadePacotes capacidade máxima de cada pacote a ser criado
	 * @return Uma lista com os pacotes criados
	 * @throws IOException
	 */
	public static List <Pacote <Par <String, Double>>> criaPacotes(
			BufferedReader fileReader, int capacidadePacotes)
					throws IOException {

		String frutaLida;
		int index = -1;
		int npacotes = capacidadePacotes;

		ArrayList <Pacote <Par <String, Double>>> listaPacotes = 
				new ArrayList<Pacote <Par <String, Double>>>();
		while((frutaLida = fileReader.readLine()) != null){

			if(capacidadePacotes == npacotes){
				index++;
				listaPacotes.add(index,(
						new Pacote <Par <String, Double>>(capacidadePacotes)));
				listaPacotes.get(index).empacota(parseItem(frutaLida));
				npacotes = 1;

			}
			else{

				listaPacotes.get(index).empacota(parseItem(frutaLida));
				npacotes++;
			}



		}
		return listaPacotes;


	}

	/**
	 * Retorna o peso total do pacote
	 * @param pacote pacote a ser pesado
	 * @return peso total do pacote
	 */
	public static double pesaPacote(Pacote<Par<String, Double>> pacote){
		double pesoTotal = 0;
		for (int i = 0; i < pacote.getCapacidade(); i++) {
			pesoTotal = pesoTotal + pacote.items().get(i).segundo();
		}
		return pesoTotal;

	}

	/**
	 * Verifica se um dado pacote tem um determinado peso
	 * @param pacote O pacote a verificar
	 * @param peso O peso alvo
	 */
	public static boolean pesoIgual(Pacote<Par<String, Double>> pacote,
			double peso){
		for (int i = 0; i < pacote.getCapacidade(); i++) {
			if(pacote.items().get(i).segundo().equals(peso)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Devolve uma lista de pacotes que tenham uma fruta do tipo 'fruta'
	 * @param pacotes Lista de pacotes a serem pesquisados
	 * @param fruta Tipo de fruta a ser pesquisada
	 * @return Lista com pacotes que contenham frutas do tipo fruta
	 */
	public static List<Pacote<Par<String, Double>>> pesquisaPacotes(
			List<Pacote<Par<String, Double>>> pacotes,
			String fruta){

		ArrayList<Pacote<Par<String, Double>>> explorador = 
				new ArrayList<Pacote<Par<String, Double>>>();

		for (int i = 0; i < pacotes.size(); i++) {
			for(int j = 0; j < pacotes.get(i).getNumItems(); j++){
				if(pacotes.get(i).items().get(j).primeiro().equals(fruta)){
					explorador.add(pacotes.get(i));
				}
			}
		}
		return explorador;
	}





}
