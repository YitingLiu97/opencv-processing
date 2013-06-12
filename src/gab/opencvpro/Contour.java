package gab.opencvpro;

import java.util.ArrayList;
import processing.core.*;

import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.imgproc.Imgproc;

import org.opencv.core.Point;

public class Contour {
	private ArrayList<PVector> points;
	private Point[] inputPoints;
	private double epsilon;
	
	public Contour(MatOfPoint mat){
		epsilon = mat.size().height * 0.01;
	    loadPoints(mat.toArray());
	}
	
	public Contour(MatOfPoint2f mat){
		epsilon = mat.size().height * 0.01;
	    loadPoints(mat.toArray());	    
	}
	
	public void loadPoints(Point[] pts){
	    points = new ArrayList<PVector>();
	    inputPoints = pts;
	    
		for(int i = 0; i < inputPoints.length; i++){
			points.add(new PVector((float)inputPoints[i].x, (float)inputPoints[i].y));
		}
	}
	
	public Contour getPolygonApproximation(){
		
		MatOfPoint2f approx = new MatOfPoint2f();
	    Imgproc.approxPolyDP(new MatOfPoint2f(inputPoints), approx, epsilon, true);
		
		
		return new Contour(approx);
	}
	
	public ArrayList<PVector> getPoints(){
		return points;
	}
	
	public int numPoints(){
		return points.size();
	}
	
}

