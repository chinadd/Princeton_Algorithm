import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   private boolean[][] gridArray;
   private int top = 0;
   private int bottom;
   private int size;
   private WeightedQuickUnionUF qf,qf1;

    
   public Percolation(int n)               // create n-by-n grid, with all sites blocked
   {
       size = n;
       bottom = size*size+1;
       qf = new WeightedQuickUnionUF(size*size+1);

       if (size > 0) {
           gridArray = new boolean[size][size];
   
       } else {
           throw new IllegalArgumentException();
       }
   }
   
   public void open(int i, int j) {
       // open site (row i, column j) if it is not open already
       if (i >= 1 && j >= 1) {
           gridArray[i-1][j-1] = true;
       } else {
           
           throw new IndexOutOfBoundsException("Given i <=0 || j <= 0");
       }
       if (i == 1) {
            qf.union(getQFIndex(i, j), top);
            qf1.union(getQFIndex(i, j), top);
        }

        if (j > 1 && isOpen(i, j - 1)) {
            qf.union(getQFIndex(i, j), getQFIndex(i, j - 1));
            qf1.union(getQFIndex(i, j), getQFIndex(i, j - 1));
        }
        if (j < size && isOpen(i, j + 1)) {
            qf.union(getQFIndex(i, j), getQFIndex(i, j + 1));
            qf1.union(getQFIndex(i, j), getQFIndex(i, j + 1));
        }
        if (i > 1 && isOpen(i - 1, j)) {
            qf.union(getQFIndex(i, j), getQFIndex(i - 1, j));
            qf1.union(getQFIndex(i, j), getQFIndex(i - 1, j));
        }
        if (i < size && isOpen(i + 1, j)) {
            qf.union(getQFIndex(i, j), getQFIndex(i + 1, j));
            qf1.union(getQFIndex(i, j), getQFIndex(i + 1, j));
        }
   }
       
   public boolean isOpen(int i, int j) {
       // is site (row i, column j) open? 
       if (0 < i && i <= size && 0 < j && j <= size) {
           return gridArray[i-1][j-1];    
       } else {
           throw new IndexOutOfBoundsException();
       }
   }
   
   public boolean isFull(int i, int j) {
       // is site (row i, column j) full?
       if (0 < i && i <= size && 0 < j && j <= size) {
           return qf1.connected(top,getQFIndex(i,j));
       } else {
           throw new IndexOutOfBoundsException();
       } 
   }
   
   public boolean percolates() {
       // does the system percolate?
       return qf.connected(top,bottom);
   }
   
   private int getQFIndex(int i, int j) {
        return size * (i - 1) + j;
    }

}

    
    