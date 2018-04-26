/*
	Node class for Euclid 21 program; contains all necessary information for a node.
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
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;

public class Node implements ActionListener
{
	private int bkNum;
	private int propNum;
	private String type;
	File image;
	private String filePath;
	private String text;
	private EuclidDisplay display;
	private boolean isHighlighted;

	//The popup menu that will appear when the vertex is right-clicked.
	private JPopupMenu menu;
	private ArrayList<JMenuItem> menuItems = new ArrayList<JMenuItem>();
	
	private Vertex vertex;
	
	private ArrayList<NodeCounter> tree;
	private ArrayList<NodeCounter> root;
	
	public Node(){}
	
	public Node(int book, int prop, char ch, String fp)
	{
		bkNum = book;
		propNum = prop;
		
		if(ch == 'P')
			type = "Proposition";
		else if(ch == 'A')
			type = "Axiom";
		else if (ch == 'D')
			type = "Definition";
		else if (ch == 'N')
			type = "Common Notion";
		else
			type = "";
		
		tree = new ArrayList<NodeCounter>();
		root = new ArrayList<NodeCounter>();
		vertex = new Vertex(this);

		isHighlighted = false;

		////////////////////////////////set the 'image' File using the file path passed in and the known book and prop numbers///////////////////////
		filePath = fp;
		String path = filePath + "/Images";

		if(type.equals("Proposition") || type.equals("Definition"))//I probably need to use this for Definitions and maybe corralaries too.
		{
			image = new File(path + "/Book" + bkNum + "/" + type + propNum + ".pdf");
		}
		else
		{
			image = new File(path + "/" + type + "s/" + type + propNum + ".pdf");
		}

		//Create the text that will be associated with this node (toString()).
		try{
			FileReader reader = new FileReader(new File(filePath + "/Notes/Book" + getBookNum() + "/" + getType() + getPropNum() + ".txt"));

			text = "";

			int character = reader.read();
			
			while(character != -1)
			{
				text = text + (char)character;
				character = reader.read();
			}
			
		} catch(FileNotFoundException e){
			if(bkNum == 0)
				text = (type + " " + propNum);
			else
				text = ("Book: " + bkNum + " " + type + ": " + propNum);
		} catch(IOException e) {
			if(bkNum == 0)
				text = (type + " " + propNum);
			else
				text = ("Book: " + bkNum + " " + type + ": " + propNum);
		}

		//Create the popup menu associated with this Node.
		createMenu();
	}

	public int getBookNum()
	{
		return bkNum;
	}
	
	public int getPropNum()
	{
		return propNum;
	}
	
	public String getType()
	{
		return type;
	}
	
	public ArrayList<NodeCounter> getRoot()
	{
		return root;
	}

	public ArrayList<NodeCounter> getTree()
	{
		return tree;
	}

	public File getImage()
	{
		return image;
	}
	
	public void addToRoot(Node node, boolean isImp)
	{
		boolean exist = false;
		if(root.size() != 0)
		{
			for(int i = 0; i < root.size(); i++)
				if(root.get(i).getNode().equals(node))
				{
					exist = true;
					root.get(i).incrementCount();
				}
		}
		if(!exist)
		{
			root.add(new NodeCounter(node));
		}
	}
	
	public void addToTree(Node node, boolean isImp)
	{
		boolean exist = false;
		if(tree.size() != 0)
		{
			for(int i = 0; i < tree.size(); i++)
				if(tree.get(i).getNode().equals(node))
				{
					exist = true;
					tree.get(i).incrementCount();
				}
		}
		if(!exist)
		{
			tree.add(new NodeCounter(node));
			Edge e = new Edge(getVertex(), tree.get(tree.size() - 1).getNode().getVertex(), isImp);
			getVertex().addEdge(e);
			tree.get(tree.size() - 1).getNode().getVertex().addEdge(e);
		}
	}
	
	public Vertex getVertex()
	{
		return vertex;
	}

	public JPopupMenu getMenu()
	{
		return menu;
	}

	private void createMenu()
	{
		menu = new JPopupMenu();
		String txt;
		if(bkNum == 0)
			txt = (type + " " + propNum);
		else
			txt = ("Book: " + bkNum + " " + type + ": " + propNum);
		
		menuItems.add(new JMenuItem(txt));
		menuItems.get(0).setEnabled(false);

		menuItems.add(new JMenuItem("Display This Node"));
		menuItems.add(new JMenuItem("Root"));
		menuItems.add(new JMenuItem("Branch"));
		menuItems.add(new JMenuItem("Delete"));
		menuItems.add(new JMenuItem("Highlight/Unhighlight"));

		for(int i = 1; i < menuItems.size(); i++)
		{
			menuItems.get(i).addActionListener(this);
		}

		for(int i = 0; i < menuItems.size(); i++)
		{
			menu.add(menuItems.get(i));
		}
		
	}

	public void setDisplay(EuclidDisplay d)
	{
		display = d;
	}

	public EuclidDisplay getDisplay()
	{
		return display;
	}

	public String toString()
	{
		return text;
	}

	public void setHighlighted(boolean h)
	{
		isHighlighted = h;
	}

	public void actionPerformed(ActionEvent evt)
	{
		//Based on which menu item was clicked, do something . . .
		if(evt.getSource().equals(menuItems.get(1)))
			display.display(this);
		else if(evt.getSource().equals(menuItems.get(2)))
			display.addToDisplay(root, this, true);
		else if(evt.getSource().equals(menuItems.get(3)))
			display.addToDisplay(tree, this, false);
		else if(evt.getSource().equals(menuItems.get(4)))
			display.removeFromDisplay(this);
		else if(evt.getSource().equals(menuItems.get(5)))
		{
			if(!isHighlighted)
			{
				display.highlight(this);
			}
			else
			{
				display.unhighlight();
			}
		}
		//Make the menu invisible again.
		menu.setVisible(false);
	}
}
