/*
	Edge class for Euclid 21 program; contains all information for a visible edge.
    Copyright (C) 2014 Mary Boman

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
import java.util.*;
import java.lang.*;
import java.awt.*;

//Class holds all the information for a visible edge.
//Edge will be drawn by a display class that extends JComponent.
public class Edge
{
	private Vertex startVertex;
	private Vertex endVertex;
	private boolean isImp;
	
	private int startX;
	private int endX;
	private int startY;
	private int endY;
	
	public Edge(Vertex start, Vertex end, boolean implicit)
	{
		startVertex = start;
		endVertex = end;
		isImp = implicit;
		updateCoordinates();
	}
	
	public void updateCoordinates()
	{	
		startX = startVertex.getX() + 25;
		endX = endVertex.getX() + 25;
		startY = startVertex.getY() + 25;
		endY = endVertex.getY() + 25;
		
		int tempX = startX - endX;
		int tempY = startY - endY;
	}

	public boolean isImplicit()
	{
		return isImp;
	}


	//This method written by Dad's students.
	public Polygon getArrowPolygon() {
        Vertex u = startVertex;
        Vertex v = endVertex;

        int r = 25; //thickness of vertex
        int s = 5; //thickness of line
        double ax = u.getX() + 25 + 0.5;
        double ay = u.getY() + 25 + 0.5;
        double length = Math.sqrt(((v.getX() + 25) - (u.getX() + 25)) * ((v.getX() + 25) - (u.getX() + 25)) + ((v.getY() + 25) - (u.getY() + 25)) * ((v.getY() + 25) - (u.getY() + 25)));
        if (length == 0) {
            return new Polygon();
        }
        double sin = ((v.getY() + 25) - (u.getY() + 25)) / length;
        double cos = ((v.getX() + 25) - (u.getX() + 25)) / length;
        double px = length - (r + 1);
        double py = 0;
        double ly = s;
        double lx = length - (r + s + s);
        double rx = lx;
        double ry = -ly;
        double mx = length - (r + s + s / 2);
        double my = 0;
        double tx;
        double ty;
        tx = px * cos - py * sin;
        ty = px * sin + py * cos;
        px = tx;
        py = ty;
        tx = lx * cos - ly * sin;
        ty = lx * sin + ly * cos;
        lx = tx;
        ly = ty;
        tx = rx * cos - ry * sin;
        ty = rx * sin + ry * cos;
        rx = tx;
        ry = ty;
        tx = mx * cos - my * sin;
        ty = mx * sin + my * cos;
        mx = tx;
        my = ty;
        px += ax;
        py += ay;
        mx += ax;
        my += ay;
        lx += ax;
        ly += ay;
        rx += ax;
        ry += ay;
        int[] xvals = new int[4];
        int[] yvals = new int[4];
        xvals[0] = (int) px;
        xvals[1] = (int) lx;
        xvals[2] = (int) mx;
        xvals[3] = (int) rx;
        yvals[0] = (int) py;
        yvals[1] = (int) ly;
        yvals[2] = (int) my;
        yvals[3] = (int) ry;
        return new Polygon(xvals, yvals, 4);
    }
	
	public int getStartX()
	{
		return startX;
	}
	
	public int getStartY()
	{
		return startY;
	}
	
	public int getEndX()
	{
		return endX;
	}
	
	public int getEndY()
	{
		return endY;
	}
	
	public Vertex getStartVertex()
	{
		return startVertex;
	}
	
	public Vertex getEndVertex()
	{
		return endVertex;
	}

	public String toString()
	{
		return("Start: " + startVertex.getName() + ", End: " + endVertex.getName());
	}
}
