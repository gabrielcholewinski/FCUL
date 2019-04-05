import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class RunEmpacotamento {

	public static void main(String[] args) throws IOException 
	{			
		// Recebe os dados de input
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduza o ficheiro com a lista de items a empacotar:");
		String fich = sc.nextLine();
		BufferedReader reader = new BufferedReader(new FileReader(fich));
		System.out.println("Introduza a capacidade dos pacotes:");
		int capacidade = Integer.parseInt(sc.nextLine());
		 
		// Cria os pacotes
		List<Pacote<Par<String, Double>>> pacotes = 
				GestorPacotes.criaPacotes(reader, capacidade);
		reader.close();
		
		// Mostra a informacao dos pacotes
		System.out.println("Pacotes formados:");
		int index = 0;
		for( Pacote<Par<String, Double>> pacote : pacotes ) 
		{
			System.out.println("Pacote " + index++ + ":");

			// Lista o conteudo do pacote
			List<Par<String, Double>> listaItems = pacote.items();
			System.out.print("-- ");
			imprimeItems(listaItems);
			
			// Pesa o pacote
			double peso = GestorPacotes.pesaPacote( pacote );
			DecimalFormat df = new DecimalFormat( "0.000" );
			System.out.println("-- Peso total: " + df.format(peso) + " kg.");
			
			// Verifica se o pacote esta cheio
			boolean cheio = pacote.estaCheio();
			System.out.println(cheio ? "-- Esta cheio!" : "-- Nao esta cheio!");
		}
		
		// Pesquisa de pacotes por peso
		boolean acaba = false;
		while(!acaba) 
		{
			System.out.println("Para procurar um pacote pelo peso, " +
					"introduza o peso, ou insira q para sair.");
			
			String lido = sc.nextLine();
			if(lido.equals("q"))	// Acaba a pesquisa		
				acaba = true;
			else 
			{
				// Procura pacotes pelo seu peso
				double target = Double.parseDouble(lido);
				boolean encontrado = false;
				
				for( int i = 0, l = pacotes.size(); i < l; i++ ) 
				{
					Pacote<Par<String, Double>> pacote = pacotes.get(i);
					if( GestorPacotes.pesoIgual(pacote, target) ) 
					{
						System.out.println("Encontrado: Pacote " + i);
						encontrado = true;
					}
				}
				if( !encontrado )
					System.out.println("Nenhum pacote encontrado.");
			}
		}
		
		// Pesquisa de pacotes por fruta
		acaba = false;
		while(!acaba) 
		{
			System.out.println("Para procurar os pacotes que contêm um " +
					"determinado tipo de fruta , introduza o nome da fruta,"
					+ " ou insira q para sair.");
			
			String lido = sc.nextLine();
			if(lido.equals("q")) // Acaba a pesquisa
				acaba = true;
			else 
			{
				// Procura pacotes por fruta				
				List<Pacote<Par<String, Double>>> result = 
						GestorPacotes.pesquisaPacotes(pacotes, lido); 
				
				int numPacotes = result.size();				
				if( numPacotes == 0 )
					System.out.println( "Nenhum pacote criado tem "+lido );
				else
				{
					System.out.println("-- Pacotes com "+lido+": ");
					for( Pacote<Par<String, Double>> pacote : result )
					{
						List<Par<String, Double>> listaItems = pacote.items();
						System.out.print("-- ");
						imprimeItems( listaItems );
					}
				}								
			}
		}
		
		System.out.println("Terminado.");
		sc.close();
	}
	
	private static void imprimeItems( List<Par<String, Double>> listaItems )
	{
		for( Par<String, Double> item : listaItems )
			System.out.print(item.primeiro() + " (" + item.segundo() + "kg)\t");
		
		System.out.println();
	}
}
