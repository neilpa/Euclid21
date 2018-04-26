/*
	Vertex class for Euclid 21 program. Holds all information for a visible vertex.
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
import java.io.*;

//Class holds all the information for a visible vertex.
//The actual vertex will be drawn by a display class that extends JComponent.
public class Vertex
{
	private int x = 0;
	private int y = 0;
	private String name;
	private Node node;
	public boolean clicked = false;
	
	private ArrayList<Edge> edgeList;
	
	public Vertex(Node n)
	{
		node = n;
		name = node.getBookNum() + "." + node.getPropNum();
		edgeList = new ArrayList<Edge>();
	}
	
	public Node getNode()
	{
		return node;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int newX)
	{
		x = newX;
	}
	
	public void setY(int newY)
	{
		y = newY;
	}
	
	public void setCoordinates(int newX, int newY)
	{
		x = newX;
		y = newY;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		return node.toString();
	}
	
	public void addEdge(Edge e)
	{
		edgeList.add(e);
	}
	
	public ArrayList<Edge> getEdges()
	{
		return edgeList;
	}
}
