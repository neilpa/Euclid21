/*
	Main class for Euclid 21 program; reads data files and creates the data structures for the program.
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
import javax.swing.JOptionPane;
import java.awt.Desktop;

public class EuclidReader
{
	private static String filePath;
	
	public static void main(String[] args)
	{
		EuclidGraph graph = new EuclidGraph();

		try
		{
			filePath = System.getProperty("user.dir") + "/Data";

			File firstFile = new File(filePath + "/file.txt");//To initally read from a different file, change "file.txt" to the name of the file.
			FileReader reader = new FileReader(firstFile);
		
			int character = reader.read();
			while(character != -1)
			{
				String fileName = "";
			
				while(!Character.isWhitespace((char)character))
				{
					fileName = fileName.concat(((Character)(char)character).toString());
					character = reader.read();
					if(character == -1)
						break;
				}
				File file = new File(filePath + "/" + fileName);
			
				while(character != -1 && Character.isWhitespace((char)character))
				{
					character = reader.read();
				}
				readFile(file, graph);
			}
		}catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "Error Reading Files\nClick 'OK' to open FAQ.");
			try
			{
				Desktop.getDesktop().open(new File(System.getProperty("user.dir") + "/FAQ.txt"));
			}catch(IllegalArgumentException ex)
			{
				JOptionPane.showMessageDialog(null, "Error opening FAQ.\nYou may have moved it or changed the name.\nProgram will exit.");
			}catch(IOException ex)
			{
				JOptionPane.showMessageDialog(null, "Error opening FAQ.\nYou may have moved it or changed the name.\nProgram will exit.");
			}
			System.exit(0);
		}
		
		new TestDraw(graph);
	}
	
	public static void readFile(File file, EuclidGraph graph) throws java.io.IOException
	{
		FileReader reader = new FileReader(file);
		int character = reader.read();
		Node currentNode = new Node();
		
		//Read in the file, one character at a time, and create the nodes appropriately.
		while(character != -1)
		{	
            while((character != -1 && Character.isWhitespace((char)character)) || (char)character == '#')
			{
				if((char)character == '#')
					while(character != 13)
						character = reader.read();
				else
					character = reader.read();
			}
			
			if((char)character == '[')
			{
				character = reader.read();
				
				String info = "";
		
				//Put all the data within the [] into a string.
				while((char)character != ']')
					{
						info = info.concat(((Character)(char)character).toString());
						character = reader.read();
					}
				currentNode = createNode(info, graph);
			}
			
			while((character != -1 && Character.isWhitespace((char)character)) || (char)character == '#')
			{
				if((char)character == '#')
					while(character != 13)
						character = reader.read();
				else
					character = reader.read();
			}
						
			while(character != -1 && (char)character != ';' && (char)character != ':')
			{
				if((char)character == '[')
				{
					character = reader.read();
					
					String info = "";
			
					//Put all the data within the [] into a string.
					while((char)character != ']')
						{
							info = info.concat(((Character)(char)character).toString());
							character = reader.read();
						}
					createDependency(info, currentNode, graph, false);
				}
				character = reader.read();
			}			
			if((char)character == ':')
			{
				while((char)character != -1 && (char)character != ';')
				{
					if((char)character == '[')
					{
						character = reader.read();
					
						String info = "";
			
						//Put all the data within the [] into a string.
						while((char)character != ']')
							{
								info = info.concat(((Character)(char)character).toString());
								character = reader.read();
							}
						createDependency(info, currentNode, graph, true);
					}
					character = reader.read();
				}
			}
			character = reader.read();
		}
	}
	
	private static Node createNode(String info, EuclidGraph graph)
	{	
		//Separate the necessary information from the string
		int index = info.indexOf('.');
		char character = info.charAt(0);
		String bookNum = info.substring(1, index);
		String propNum;
		int bookNumber = 0;
		int propNumber = 0;
		
		propNum = info.substring(index + 1, info.length());
		
		bookNumber = Integer.parseInt(bookNum);
		
		propNumber = Integer.parseInt(propNum);
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		//Create the node.
		Node node = new Node(bookNumber, propNumber, character, filePath);
		
		graph.addElement(bookNumber, propNumber, node);
		
		return node;
	}
	
	private static void createDependency(String info, Node currentNode, EuclidGraph graph, boolean isImp)
	{
		//Separate the necessary information from the string.
		int index = info.indexOf('.');
		char character = info.charAt(0);
		String bookNum = info.substring(1, index);
		String propNum;
		int bookNumber = 0;
		int propNumber = 0;
		
		propNum = info.substring(index + 1, info.length());
		
		bookNumber = Integer.parseInt(bookNum);
		
		propNumber = Integer.parseInt(propNum);

		Node newNode = new Node();

		if(character == 'P' || character == 'A')
		{
			newNode = graph.getProp(bookNumber, propNumber);
		}
		else if(character == 'D' || character == 'N')
		{
			newNode = graph.getDef(bookNumber, propNumber);
		}
		
		currentNode.addToRoot(newNode, isImp);
		newNode.addToTree(currentNode, isImp);
	}
	
}
