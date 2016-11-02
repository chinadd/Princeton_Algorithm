
import java.util.*;
import java.io.*;

public class FastCollinearPoints {
   
    private HashMap<Double, List<Point>> foundSegments = new HashMap<Double, List<Point>>();

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
    
    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if ( points == null ) throw new java.lang.NullPointerException();
        
        for ( int i = 0 ; i < points.length ; i ++ ) {
            if (points[i] == null) throw new java.lang.NullPointerException();
        }
        
        checkDuplicatedEntries(points);
        
        int num = points.length;
        
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        
        for (Point startPoint : points) {
            Arrays.sort(pointsCopy,startPoint.slopeOrder());
            
            List<Point> slopePoints = new ArrayList<Point>();
            double slope = 0;
            double previousSlope = Double.NEGATIVE_INFINITY;
            
            for ( int i = 1; i < num ; i++ ) {
                slope = startPoint.slopeTo(pointsCopy[i]);
                if (slope == previousSlope) {
                    slopePoints.add(pointsCopy[i]);
                } else {
                    if (slopePoints.size() >= 3) {
                        slopePoints.add(startPoint);
                        addSegmentIfNew(slopePoints, previousSlope);
                    }
                    slopePoints.clear();
                    slopePoints.add(pointsCopy[i]);
                }
                previousSlope = slope;
            }
            
            if (slopePoints.size() >= 3) {
                        slopePoints.add(startPoint);
                        addSegmentIfNew(slopePoints, slope);
            }      
        }
    }  
    
    private void addSegmentIfNew(List<Point> slopePoints, double slope) {
        List<Point> endPoints = foundSegments.get(slope);
        Collections.sort(slopePoints);

        Point startPoint = slopePoints.get(0);
        Point endPoint = slopePoints.get(slopePoints.size() - 1);

        if (endPoints == null) {
            endPoints = new ArrayList<Point>();
            endPoints.add(endPoint);
            foundSegments.put(slope, endPoints);
            segments.add(new LineSegment(startPoint, endPoint));
        } else {
            for (Point currentEndPoint : endPoints) {
                if (currentEndPoint.compareTo(endPoint) == 0) {
                    return;
                }
            }
            endPoints.add(endPoint);
            segments.add(new LineSegment(startPoint, endPoint));
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