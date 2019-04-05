package testes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({  
				CarroConstructorTest.class,
				CarroFazRevisaoTest.class,
				CarroObtemKmEActualizarKm.class,
				CarroPrecisaRevisaoTest.class,
				CarroRevisaoTest.class,
				CarroToStringTest.class,
				GaragemCarrosRegistadosTest.class,
				GaragemEmServicoTest.class,
				GaragemEscolhaTest.class,
				GaragemRecolheTest.class,
				GaragemToStringTest.class,
				PistaAdicionaTest.class,
				PistaCarroNoTopoTest.class,
				PistaConstructorTest.class,
				PistaGetComprimentoOcupadoTest.class,
				PistaIsEmptyTest.class,
				PistaToStringTest.class
				})

public class AllTests4 {

}