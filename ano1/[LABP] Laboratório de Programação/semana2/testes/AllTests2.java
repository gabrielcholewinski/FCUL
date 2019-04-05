package testes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({  
				FraccaoTestesEhInteira.class,
				FraccaoTestesEhPositiva.class,
				FraccaoTestesEhZero.class,
				FraccaoTestesEquivalente.class,
				FraccaoTestesInversa.class,
				FraccaoTestesProduto.class,
				FraccaoTestesDivisao.class,
				FraccaoTestesSoma.class,
				FraccaoTestesMakeFromString.class,
				FraccaoTestestoString.class,
				PolinomioTestesAvalia.class,
				PolinomioTestesDeriva.class,
				PolinomioTestesEhConstante.class,
				PolinomioTestesEhIgual.class,
				PolinomioTestesEhZero.class,
				PolinomioTestesEscalar.class,
				PolinomioTestesGrau.class,
				PolinomioTestesSimetrico.class,
				PolinomioTestesSoma.class,
				PolinomioTestesSubtraccao.class,
				PolinomioTestesToString.class
				})

public class AllTests2 {

}