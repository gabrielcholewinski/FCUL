//package TPC1;

import java.util.Random;

public class AED_TPC_1 {

    public static void main(String[] args) {
        int poia = 0;
        int size = 17000;
        for(int i = 0; i < 1000; i++) {
            int[] v = makeRandomVector(size);
            poia += existeDecrescimo(v);
        }
        System.out.print(poia/1000.0);

    }

    public static int[] makeRandomVector(int size) {
        int[]result = new int[size];
        Random r = new Random();
        for(int i = 0; i < size; i++) {
            result[i] = 1 + r.nextInt();
        }
        return result;
    }


    public static int existeDecrescimo (int[]v) {
        int count = 0;
        boolean encontrou = false;
        for(int i = 1; i < v.length-1; i++) {
            if(v[i] < v[i-1] && !encontrou) {
                count = i;
                encontrou = true;
            }
        }
        return count;
    }

}