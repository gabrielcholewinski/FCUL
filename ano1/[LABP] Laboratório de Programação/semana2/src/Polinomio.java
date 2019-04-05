package src;


/**
 * 
 * @author 
 *
 */
public class Polinomio {
	private Fracao[]coefs;
	private int maxGrau;

    /**
     * 
     * @param coefs
     * @requires
     */
    public Polinomio(Fracao[] coefs) {
    	this.coefs = coefs;
    	this.maxGrau = coefs.length-1;
    }

    /**
     * 
     * @return
     * @requires
     */
    public int grau() {
    	return this.maxGrau;
    }


    /**
     * 
     * @return
     * @requires
     */
    public boolean ehZero() {
        boolean result = false;
        for(int i = 0; i < this.coefs.length; i++) {
        	if(!coefs[i].ehZero()) {
        		return result;
        	}
        	result = true;
        }
        return result;
    }

    /**
     * 
     * @return
     * @requires
     */
    public boolean ehConstante() {
        return this.maxGrau == 0;
    }


    /**
     * 
     * @param p
     * @return
     * @requires
     */
    public boolean ehIgual(Polinomio p) {
       boolean result = false;
       if(this.grau() == p.grau()) {
    	   for(int i = 0; i < this.coefs.length; i++) {
    		   if(!coefs[i].equivalente(p.coefs[i])){
    			   return result;
    		   }
    		   result = true;
    	   }
       }
       return result;
    }   


    /**
     * 
     * @param f
     * @return
     * @requires
     */
    public Polinomio escalar(Fracao f) {
    	Fracao [] result = new Fracao[this.coefs.length];
        for(int  i = 0; i < this.coefs.length; i++) {
        	result[i] = coefs[i].produto(f);
        }
        Polinomio teste = new Polinomio(result);
        return teste;
    }

    /**
     * 
     * @return
     * @requires
     */
    public Polinomio simetrico() {
        Fracao[] result = new Fracao[this.coefs.length];
        Fracao teste = new Fracao(-1,1);
        for(int  i = 0; i < this.coefs.length; i++) {
        	result[i] = coefs[i].produto(teste);
        }
        Polinomio ruhan = new Polinomio(result);
        return ruhan;
    }

    /**
     * 
     * @param p
     * @return
     * @requires
     */
    public Polinomio soma (Polinomio p) {
        Fracao[] result = new Fracao[this.coefs.length];
        for(int  i = 0; i < this.coefs.length; i++) {
        	result[i] = coefs[i].soma(p.coefs[i]);
        }
        Polinomio teste = new Polinomio(result);
        return teste;
    }

    /**
     * 
     * @param p
     * @return
     * @requires
     */
    public Polinomio subtraccao (Polinomio p) {
    	Fracao[] result = new Fracao[this.coefs.length];
        for(int  i = 0; i < this.coefs.length; i++) {
        	result[i] = coefs[i].soma(p.simetrico().coefs[i]);
        }
        Polinomio teste = new Polinomio(result);
        return teste;
    }

    /**
     * 
     * @param p
     * @return
     * @requires
     */
    public Polinomio produto(Polinomio p) {
    	 Fracao[]result = new Fracao[this.coefs.length];
         for(int  i = 0; i < this.coefs.length; i++) {
         	result[i] = coefs[i].produto(p.coefs[i]);
         }
         Polinomio teste = new Polinomio(result);
         return teste;
    }
    
    private static Fracao elevada (Fracao a, int n) {
    	int numResult = (int) Math.pow(a.num, n);
    	int denResult = (int) Math.pow(a.den, n);
    	Fracao result = new Fracao(numResult,denResult);
    	return result;
    }

    /**
     * 
     * @param x
     * @return
     * @requires
     */
    public Fracao avalia(Fracao x) {
       Fracao[] frac = new Fracao[this.coefs.length];
       int grau = this.maxGrau;
       for(int i = this.coefs.length-1; i >= 0; i--) {
    	   frac[i] = coefs[i].produto(elevada(x,grau));
    	   grau--;
       }
       Fracao xota = frac[0];
       for(int i=1 ; i<frac.length ; i++){
    	   xota.soma(frac[i]);
       }
       return xota;
    }


    /**
     * 
     * @return
     * @requires
     */
    public Polinomio deriva() {
    	int numResult = this.coefs.length-1;
    	Fracao teste = new Fracao(numResult,1);
    	Fracao [] result = new Fracao[this.coefs.length];
    		for(int i = this.coefs.length-1; i >= 0; i--) {
    			result[i] = coefs[i].produto(teste);
    			numResult--;
    		}
    		Polinomio resultado = new Polinomio(result);
    		return resultado;
    		
    }


    /**
     * 
     * @return
     * @requires
     */
    public Polinomio copia() {
        Fracao[] result = new Fracao[this.coefs.length];
        Polinomio teste = new Polinomio(result);
        return teste;
    }


    /**
     * 
     */
    public String toString() {
        String result = "";
        for(int i = this.coefs.length-1; i >= 0; i--) {
        	if(!this.coefs[i].ehPositiva()) {
        		result += "-"+coefs[i].toString();
        	} else {
        		result += "+"+coefs[i].toString();
        		
        	}
        }
        return result;
    }



}

