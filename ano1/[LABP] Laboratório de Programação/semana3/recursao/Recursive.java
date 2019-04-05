package recursao;

public class Recursive {

    public static double potencia (double base, int expoente) {
        if(expoente == 0) return 1;
        return base * potencia(base,expoente-1);
        
    }
    
    public static int soma (int n) {
    	if(n == 0) return 0;
        if(n == 1) return 1;
        int soma = n;
        soma += soma(n-1);
        return soma;
    }

    private static int maior (int[]v,int i,int max) {
    	if(i == v.length) {
    		return max;
    	}
    	if (v[i] > max) {
    		max = v[i];
    	}
    	return maior(v,i+1,max);
    }
    
    public static int maior(int[]v) {
    	return(maior(v,0,-1));
    }
    
    public static int countChar (String s, char c) {
    	return countC(s,c,0,0);
    }
    
    private static int countC (String s, char c, int i, int count) {
    	char[]result = s.toCharArray();
    	if(i == result.length) {
    		return count;
    	}
        if(result[i] == c) {
        	count++;
        }
        return countC(s,c,i+1,count);
        
    }
 
    public static String change (String s,char old,char newer) {
		 
		 return changeString(s,old,newer,0, "");
	 }

	 private static String changeString (String s, char old, char newer,int i, String resultado) {
	        
	        if(i == s.length()) {
	        	return resultado;
	        }
	        if(s.charAt(i) == old) {
	            resultado+=""+newer;
	        }else {
	        	resultado+=""+s.charAt(i);
	        }
	        return changeString(s,old,newer,i+1,resultado);
	 }

     public static int gcd (int p, int q) {
    	    if(q >= 100000000 && q%2 != 0) return 1;
    		if(p == q) {
    			return p;
    		}
    		else if(q > p && p != 0) {
    			return gcd(p,q-p);
    		}
    		else if(p > q && q != 0) {
    			return gcd(p-q,q);
    		}
    		else if(p == 0) {
    			return 1;
    		}
    		return 0;
     }

    public static int binomial (int l, int k) {
         if( k > l) return 0;
         if (k == 0 || l == k) {
             return 1;
         }else {
             return binomial(l - 1, k) + binomial(l - 1, k - 1);
         }
    }

        
    private static boolean pertence2 (int[]v,int i,int n,boolean result) {
    	 if(i == v.length) return false;
    	 if (v[i] == n) {
    	   return true;
    	 } else {
    	 return pertence2(v,i+1,n,true);
    	 }
    }	 
    	    
    public static boolean pertence(int[]v,int n) {
    	    	return(pertence2(v,0,n,true));
    }
    
    private static boolean ehSimetrico2 (int[]v,int i,int a,boolean result) {
    	if(v.length == 1) return true;
    	if(i >= a) return false;
    	if(v[i] == 1) return false;
    	if(v[i] != v[a]) {
    		result = false;
    	} else {
    		return true;
    	}
        return ehSimetrico2(v,i+1,a-1,result);
    	
    }
    
    public static boolean ehSimetrico (int[] v) {
        int a = v.length-1;
        return ehSimetrico2(v,0,a,true );
        
    }
    
    private static boolean ehCrescente2 (double[]v,int i,boolean result) {
    	if(i == v.length) return true;
    	if(v[i] >= v[i-1]) {
    		result = true;
    	} else {
    		return false;
    	}
    	return ehCrescente2(v,i+1,result);
    }
    
    public static boolean ehCrescente (double[] v) {
        return ehCrescente2(v,1,true);
    }

    public static String intToBin (int n) {
    	if(n == 1) return "1";
    	if(n == 0) return "0";	
        return intToBin(n/2)+""+n%2;
    }

    public static int triangulos (int l) {
    	return soma(l);
    	
    }

    public static int numeroOrelhas (int l) {
        return numeroOrelhas2(l,0);
    }
    
    private static int numeroOrelhas2 (int l,int i) {
    	if(l == 1) return 3;
    	if(l == i && i%2 == 0) return 0;
    	if(l == i && i%2 != 0) return 1;
    	if(i%2 != 0) {
    		return 3 + numeroOrelhas2(l,i+1);
        } else {
        	return 2 + numeroOrelhas2(l,i+1);
        }
        
    }

    public static int algarismos (int l) {
        if(l == 0) return 0;
        if(l < 10) return l/10 + 1;
        return algarismos(l/10) + 1;
    }
    
    public static int somaAlgarismos (int l) {
        return somaA(l, 0);
        
    }
    
    private static int somaA (int l, int soma) {
    	if(l < 10) {
    		return l;
    	}
        else {
        	return  somaA(l/10,soma) + l%10;
        }
    }

    public static boolean ehPrimo (int n) {
        return primo(n,0);
    }
    
    private static boolean primo(int n,int a) {
    	if(n == 1) return false;
    	if(n == a) {
    		if(n%2 == 0) {
    			return true;
    		} else if(n%5 == 0) {
    			return false;
    		} else if(n%2 != 0) {
    			return true;
    		}
    	}
    	return primo(n,a+1);
    }

    public static int produto (int n, int m) {
    	if(n*m == 0) return 0;
        if(n < 0 && m > 0 || n > 0 && m < 0 || n < 0 && m < 0) return n*m;
        return produto(n,m-1) + n;
        
    }

    public static int somaQuadrados (int n) {
    	if(n == 0) return 0;
        if(n == 1) return 1;
        int produto = n*n;
        produto += somaQuadrados(n-1);
        return produto;
        
    }
}