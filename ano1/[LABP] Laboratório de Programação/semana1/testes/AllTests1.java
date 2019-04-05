package testes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({  
				TesteRetiraCarateres.class,
				TesteFrequenciasLetras.class,
				TesteRotacao.class,
				TesteFrequenciasPadroes.class,
				})

public class AllTests1 {

}