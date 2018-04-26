/*
	EuclidGraph class for Euclid 21 program; contains a graph of all nodes in the data.
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

public class EuclidGraph
{
	private ArrayList<Book> graph;
	
	public EuclidGraph()
	{
		graph = new ArrayList<Book>();
	}

	public EuclidGraph(EuclidGraph graphToCopy)
	{
		graph = graphToCopy.getGraph();
	}
	
	public ArrayList<Book> getGraph()
	{
		return graph;
	}
	
	public void addElement(int bkNumber, int propNumber, Node node)
	{
		if(graph.size() <= bkNumber)
			graph.add(new Book());
			
		if(node.getType().equals("Proposition") || node.getType().equals("Axiom"))
			graph.get(bkNumber).addProp(node);
		else if(node.getType().equals("Definition") || node.getType().equals("Common Notion"))
			graph.get(bkNumber).addDef(node);
	}
	
	public Node getProp(int bkNumber, int propNumber)
	{
		return graph.get(bkNumber).getProp(propNumber);
	}

	public Node getDef(int bkNumber, int defNumber)
	{
		return graph.get(bkNumber).getDef(defNumber);
	}
	
	public Book getBook(int bkNumber)
	{
		return graph.get(bkNumber);
	}
}
