import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
    private int experimentsCount;
    private Percolation pr;
    private double[] fractions;
    
    public PercolationStats(int n, int trials) {
        // perform trials independent experiments on an n-by-n grid
        if(n <= 0 || trials <= 0) {
             throw new IllegalArgumentException("Given N <= 0 || T <= 0");
        }
        experimentsCount = trials;
        fractions = new double[trials];
        
        for (int T = 0; T < trials; T++) {
            pr = new Percolation(n);
            int Opensites = 0;
            while(!pr.percolates()) {
                int i = StdRandom.uniform(1, n+1);
                int j = StdRandom.uniform(1, n+1);
                if (!pr.isOpen(i,j)) {
                    pr.open(i,j);
                    Opensites++;
                }
            }
            double fraction = (double) Opensites/(n*n);
            fractions[T] = fraction;
        }
    }
            
        
        public double mean() {
            // sample mean of percolation threshold
            return StdStats.mean(fractions);
        }
            
        public double stddev() {
            // sample standard deviation of percolation threshold
            return StdStats.stddev(fractions);
        }
        
        public double confidenceLo() {
            // low  endpoint of 95% confidence interval
            return mean()-((1.96 * stddev()) / Math.sqrt(experimentsCount));
        }
        public double confidenceHi() {
            // high endpoint of 95% confidence interval
            return mean()+((1.96 * stddev()) / Math.sqrt(experimentsCount));
        }
        
        public static void main(String[] args) {
            int N = Integer.parseInt(args[0]);
            int T = Integer.parseInt(args[1]);
            PercolationStats ps = new PercolationStats(N,T);
            
            String confidence = ps.confidenceLo()+", "+ps.confidenceHi();
            System.out.println("mean                    ="+ps.mean());
            System.out.println("stddev                  ="+ps.stddev());
            System.out.println("95% confidence interval ="+confidence);
        }
}