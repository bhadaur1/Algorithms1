/* *****************************************************************************
 *  Name: Ravi Bhadauria
 *  Date: 10/21/2018
 *  Description: Percolation driver for Algorithms course
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[][] grid;
    private int nOpen;
    private WeightedQuickUnionUF objWQF;

    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        if (n > 0) {
            // grid array automatically initialized to zeros
            grid = new int[n + 1][n + 1]; // (n+1) due to not dealing with zero indices
            nOpen = 0;  // no open sites to begin with
            objWQF = new WeightedQuickUnionUF((n + 1) * (n + 1));
            // Virtual site [0][0] reserved for top,  [0][1] reserved for bottom
            for (int col = 1; col < grid.length; col++) {
                objWQF.union(0, grid.length + col);
                objWQF.union(1, grid.length * (grid.length - 1) + col);
            }
        }
        else {
            throw new java.lang.IllegalArgumentException(
                    "Cannot create grid with n<=0.");
        }
    }

    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        if (row < 1 || col < 1 || row > (grid.length - 1) || col > (grid.length - 1)) {
            throw new java.lang.IllegalArgumentException(
                    "Row or Columns cannot be less than 1, or greater than N.");
        }
        else {
            if (!isOpen(row, col)) {
                int sId = grid.length * row + col;
                int lId = sId - 1;
                int rId = sId + 1;
                int tId = sId + grid.length;
                int bId = sId - grid.length;

                grid[row][col] = 1;
                nOpen = nOpen + 1;

                if ((col < grid.length - 1) && isOpen(row, col + 1)) // right neighbor
                    objWQF.union(sId, rId);

                if ((col > 1) && isOpen(row, col - 1)) // left neighbor
                    objWQF.union(sId, lId);

                if ((row < grid.length - 1) && isOpen(row + 1, col)) // top neighbor
                    objWQF.union(sId, tId);

                if ((row > 1) && isOpen(row - 1, col)) // bot neighbor
                    objWQF.union(sId, bId);
            }
        }
    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        if (row < 1 || col < 1 || row > (grid.length - 1) || col > (grid.length - 1)) {
            throw new java.lang.IllegalArgumentException(
                    "Row or Columns cannot be less than 1, or greater than N.");
        }
        else {
            return (grid[row][col] == 1);
        }
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        if (row < 1 || col < 1 || row > (grid.length - 1) || col > (grid.length - 1)) {
            throw new java.lang.IllegalArgumentException(
                    "Row or Columns cannot be less than 1, or greater than N.");
        }
        else {
            return (isOpen(row, col) && objWQF.connected(0, grid.length * row + col));
        }
    }

    public int numberOfOpenSites()       // number of open sites
    {
        return nOpen;
    }

    public boolean percolates()              // does the system percolate?
    {
        return (objWQF.connected(0, 1));
    }

    public static void main(String[] args) {

    }
}
