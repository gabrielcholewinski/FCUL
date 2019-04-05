package src;

/**
 * 
 * @author 
 *
 */
public class Fracao {
	public int num;
	public int den;
	private double div;
    /**
     * 
     * @param num
     * @param den
     * @requires
     */
    public Fracao(int num, int den) {
    	this.num = num;
    	this.den = den;
    	this.div = (double)num/den;
    }

    /**
     * 
     * @return
     * @requires
     */
    public boolean ehZero() {
    	if(this.div != 0) {
    		return false;
    	} else {
    		return true;
    	}
    }

    /**
     * 
     * @return
     * @requires
     */
    public boolean ehInteira() {
    	if(this.num%this.den == 0) {
    		return true;
    	} else {
    		return false; 
    	}
    }

    /**
     * 
     * @return
     * @requires
     */
    public boolean ehPositiva() {
    	if(this.div > 0) {
    		return true;
    	} else {
    		return false;
    	}
    }

    /**
     * 
     * @return
     * @requires
     */
    public Fracao inversa() {
        Fracao result = new Fracao(this.den, this.num);
        return result;
    }	

    /**
     * 
     * @param f
     * @return
     * @requires
     */
    public Fracao soma(Fracao f) {
        int numResult = 0;
        int denResult = 0;
        if(this.den != f.den ) {
        	numResult = f.den * this.num + this.den * f.num;
        	denResult = this.den * f.den;
        } else {
        	numResult = f.num + this.num; 
        	denResult = this.den; 
        }
        Fracao result = new Fracao(numResult, denResult);
        return result;
   }

    /**
     * 
     * @param f
     * @return
     * @requires
     */
    public Fracao produto(Fracao f) {
        int numResult = this.num * f.num;
        int denResult = this.den * f.den;
        Fracao result = new Fracao (numResult, denResult);
        return result; 
    }


    /**
     * 
     * @param f
     * @return
     * @requires
     */
    public Fracao divisao(Fracao f) {
        int numResult = this.num * f.den;
        int denResult = this.den * f.num;
        Fracao result = new Fracao(numResult, denResult);
        return result;
    }

    /**
     * Criacao de uma copia da fracao
     * @return uma nova fracao igual ao this.
     */
    public Fracao copia() {
        Fracao result = new Fracao(this.num, this.den);
        return result;
    }

    /**
     * 
     * @param f
     * @return
     * @requires
     */
    public boolean equivalente (Fracao f) {
    	double teste1 =(double) this.num/this.den;
    	double teste2 = (double) f.num/f.den;
    	if(teste1 - teste2 == 0) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    //METODO AUXILIAR!!!
	private static int mdc (int num, int den) {
		if(num == den) {
			return num;
		}
		else if(den > num && num != 0) {
			return mdc(num,den-num);
		}
		else if(num > den && num != 0) {
			return mdc(num-den,den);
		}
		else if(num == 0) {
			return 1;
		}
		return 0;
	}

    /**
     * 
     */
    public String toString() {
        int num2 = Math.abs(this.num/mdc(Math.abs(this.num),Math.abs(this.den)));
        if(this.num == 0) num2 = 0;
        int den2 = Math.abs(this.den/mdc(Math.abs(this.num),Math.abs(this.den)));
        String teste = "";
        if(this.div > 0.0) {
        	teste = num2+"/"+den2;
        } else if (this.num == 0){
        	teste = "0";
        } else if (this.div < 0.0) {
        	teste = "-" + num2 + "/" + den2;
        }
        return teste;
    }

    /**
     * 
     * @param f
     * @return
     * @requires
     */
    public static Fracao makeFromString(String f) {
    	String[]teste = f.split("[/]");
    	int numResult = Integer.parseInt(teste[0]);
    	int denResult = 1;
    	if(teste.length == 2) denResult = Integer.parseInt(teste[1]);
        int a = mdc(numResult,denResult);
        numResult = numResult/a;
        denResult = denResult/a;
        Fracao result = new Fracao(numResult,denResult);
        return result;
    }

}
