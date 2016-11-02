 
import java.util.*;
import java.io.*;


public class BruteCollinearPoints {
    
    private List<LineSegment> segments = new ArrayList<LineSegment>();
    
    private void checkDuplicatedEntries(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicated entries in given points.");
                }
            }
        }
    }
    
    private static boolean isLine(final Point p, final Point q, final Point r,
                                  final Point s) {
        double slope = p.slopeTo(q);
        return p.slopeTo(r) == slope && p.slopeTo(s) == slope;
    }
    
    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if ( points == null ) throw new java.lang.NullPointerException();
        
        for ( int i = 0 ; i < points.length ; i ++ ) {
            if (points[i] == null) throw new java.lang.NullPointerException();
        }
        
        checkDuplicatedEntries(points);
        
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        
        int countSegments = 0;
        int num = points.length;
        
        HashMap<Integer,Integer> m = new HashMap<Integer,Integer>();
        
        for ( int i = 0; i < num-3 ; i++ ) {
            
            for (int j = i + 1; j < num - 2; j++) {
                
                for (int k = j + 1; k < num - 1; k++) {
                    
                    for (int l = k + 1; l < num; l++) {
                        
                        if (isLine(pointsCopy[i], pointsCopy[j], pointsCopy[k],
                                   pointsCopy[l])) {
                            
                            segments.add(new LineSegment(pointsCopy[i],pointsCopy[l]));
                        }
                    }
                }
            }
        }
        
    }     
   
    public int numberOfSegments() {
           // the number of line segments
           return segments.size();
    }
    public LineSegment[] segments() {
        // the line segments
        return segments.toArray(new LineSegment[segments.size()]);
    }
}