/*
	Book class for Euclid 21 program; Contains all data on a book for the program.
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

public class Book
{
	private ArrayList<Node> propositions = new ArrayList<Node>();
	private ArrayList<Node> definitions = new ArrayList<Node>();

	public Book()
	{
		propositions.add(null);
		definitions.add(null);
	}

	public void addProp(Node prop)
	{
		propositions.add(prop);
	}

	public void addDef(Node def)
	{
		definitions.add(def);
	}

	public Node getProp(int i)
	{
		return propositions.get(i);
	}

	public Node getDef(int i)
	{
		return definitions.get(i);
	}

	public ArrayList<Node> getProps()
	{
		return propositions;
	}

	public ArrayList<Node> getDefs()
	{
		return definitions;
	}
}
