/* *****************************************************************************
 *  Name: Ravi Bhadauria
 *  Date: 10/21/2018
 *  Description: Percolation statistics driver for Algorithms course
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] ratio;

    public PercolationStats(int n,
                            int trials)    // perform trials independent experiments on an n-by-n grid
    {
        if (n < 1 || trials < 1) {
            throw new java.lang.IllegalArgumentException(
                    "grid and trials should be at least 1.");
        }
        else {
            ratio = new double[trials];
            for (int t = 0; t < trials; t++) {
                Percolation perObj = new Percolation(n);
                while (!perObj.percolates()) {
                    perObj.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
                }
                ratio[t] = (double) perObj.numberOfOpenSites() / (double) (n * n);
            }
        }
    }

    public double mean()                          // sample mean of percolation threshold
    {
        return StdStats.mean(ratio);
    }

    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(ratio);
    }

    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return (mean() - 1.96 * stddev() / Math.sqrt(ratio.length));
    }

    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return (mean() + 1.96 * stddev() / Math.sqrt(ratio.length));
    }

    public static void main(String[] args) {
        PercolationStats PP = new PercolationStats(Integer.parseInt(args[0]),
                                                   Integer.parseInt(args[1]));
        System.out.printf("mean                    = %f\n", PP.mean());
        System.out.printf("stddev                  = %f\n", PP.stddev());
        System.out.printf("95%% confidence interval = [%f,%f]\n", PP.confidenceLo(),
                          PP.confidenceHi());
    }
}
