/*
	NodeCounter class for Euclid 21 program.
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

public class NodeCounter
{
	private Node node;
	private int counter;
	
	public NodeCounter(Node n)
	{
		node = n;
		counter = 1;
	}
	
	public Node getNode()
	{
		return node;
	}
	
	public int getCount()
	{
		return counter;
	}
	
	public void incrementCount()
	{
		counter++;
	}
}
