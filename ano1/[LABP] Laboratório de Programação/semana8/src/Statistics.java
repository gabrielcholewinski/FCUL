package src;


public final class Statistics {

    public Statistics() { }

    
    /**
     * 
     *
     * @param 
     * @requires 
     * @return 
     */
    public int max(int[] a) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) max = a[i];
        }
        return max;
    }
    
    /**
     * 
     *
     * @param 
     * @requires 
     * @return 
     */
    public int min(int[] a) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) min = a[i];
        }
        return min;
    }

    /**
     * 
     *
     * @param 
     * @requires 
     * @return 
     */
    public double mean(int[] a) {
        if (a.length == 0) return Double.NaN;
        int sum = sum(a);
        return 1.0 * sum / a.length;
    }

    /**
     * 
     *
     * @param 
     * @requires 
     * @return 
     */
    public double var(int[] a) {
        if (a.length == 0) return Double.NaN;
        double avg = mean(a);
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += (a[i] - avg) * (a[i] - avg);
        }
        return sum / (a.length - 1);
    }

    /**
     * 
     *
     * @param 
     * @requires 
     * @return 
     */
    public double stddev(int[] a) {
        return Math.sqrt(var(a));
    }


    /**
     * 
     *
     * @param 
     * @requires 
     * @return 
     */
    private int sum(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum;
    }

}

