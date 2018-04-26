/*
	EuclidDisplay class for Euclid 21 program; Draws the visible graph.
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
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;

public class EuclidDisplay extends JComponent implements MouseListener, MouseMotionListener
{
	private ArrayList<Vertex> displayedVertexes;
	private ArrayList<Edge> displayedEdges;

	private ArrayList<Edge> highlightedEdges;
	private Node highlightedNode;

	private String str = "";

	private boolean on;

	int width;

	//Create a EuclidDisplay object that will show the splash page.
	public EuclidDisplay()
	{
		setPreferredSize(new Dimension(1000, 550));
			
		displayedVertexes = new ArrayList<Vertex>();
		displayedEdges = new ArrayList<Edge>();

		highlightedEdges = new ArrayList<Edge>();
		highlightedNode = new Node();

		on = false;

		width = 1000;
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void paintComponent(Graphics g)
	{
		//set background color:
		g.setColor(new Color(203, 156, 95));
		g.fillRect(0, 0, getWidth(), getHeight());
		if(on)
		{
			setPreferredSize(new Dimension(5000, 550));
			
			//Draw the key.
			g.setColor(Color.BLACK);

			g.drawRect(10, 10, 120, 235);

			g.setColor(Color.YELLOW);

			g.drawOval(15, 15, 20, 20);
			g.fillOval(15, 15, 20, 20);

			g.setColor(Color.GREEN);

			g.drawOval(15, 45, 20, 20);
			g.fillOval(15, 45, 20, 20);

			g.setColor(Color.CYAN);

			g.drawOval(15, 75, 20, 20);
			g.fillOval(15, 75, 20, 20);

			g.setColor(Color.MAGENTA);

			g.drawOval(15, 105, 20, 20);
			g.fillOval(15, 105, 20, 20);

			g.setColor(Color.BLACK);

			int[] xPts = {35, 25, 30, 25};
			int[] byPts = {140, 135, 140, 145};

			//g.drawLine(15, 140, 35, 140);
			//g.drawPolygon(xPts, byPts, 4);
			//g.fillPolygon(xPts, byPts, 4);

			g.setColor(Color.RED);

			int[] ryPts = {150, 145, 150, 155};

			//g.drawLine(15, 150, 35, 150);
			//g.drawPolygon(xPts, ryPts, 4);
			//g.fillPolygon(xPts, ryPts, 4);

			g.setColor(Color.GRAY);

			int[] gyPts = {170, 165, 170, 175};
			
			//g.drawLine(15, 170, 35, 170);
			//g.drawPolygon(xPts, gyPts, 4);
			//g.fillPolygon(xPts, gyPts, 4);

			g.setColor(Color.PINK);

			int[] pyPts = {180, 175, 180, 185};

			//g.drawLine(15, 180, 35, 180);
			//g.drawPolygon(xPts, pyPts, 4);
			//g.fillPolygon(xPts, pyPts, 4);

			g.setColor(Color.BLACK);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 12));

			g.drawString("Proposition", 45, 30);

			g.drawString("Definition", 45, 60);

			g.drawString("Axiom", 45, 90);

			g.drawString("Common Notion", 45, 120);

			//g.drawString("Explicit", 45, 150);

			//g.drawString("Implicit", 45, 180);

			//Draw the currently displayed portion of the graph.
			g.setColor(Color.BLACK);
		
			for(int i = 0; i < displayedEdges.size(); i++)
			{
				Edge edge = displayedEdges.get(i);

				if(!edge.isImplicit())
					g.setColor(Color.BLACK);
				else
					g.setColor(Color.GRAY);
					
				edge.updateCoordinates();
				g.drawLine(edge.getStartX(), edge.getStartY(), edge.getEndX(), edge.getEndY());
				g.drawPolygon(edge.getArrowPolygon());
				g.fillPolygon(edge.getArrowPolygon());
			}

			for(int i = 0; i < highlightedEdges.size(); i++)
			{
				Edge edge = highlightedEdges.get(i);

				if(!edge.isImplicit())
					g.setColor(Color.RED);
				else
					g.setColor(Color.PINK);
				
				edge.updateCoordinates();
				g.drawLine(edge.getStartX(), edge.getStartY(), edge.getEndX(), edge.getEndY());
				g.drawPolygon(edge.getArrowPolygon());
				g.fillPolygon(edge.getArrowPolygon());
			}

			g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
			for(int i = 0; i < displayedVertexes.size(); i++)
			{
				Vertex vertex = displayedVertexes.get(i);

				if(vertex.getNode().getType().equals("Proposition"))
					g.setColor(Color.YELLOW);
				else if(vertex.getNode().getType().equals("Axiom"))
					g.setColor(Color.CYAN);
				else if(vertex.getNode().getType().equals("Definition"))
					g.setColor(Color.GREEN);
				else if(vertex.getNode().getType().equals("Common Notion"))
					g.setColor(Color.MAGENTA);
			
				g.drawOval(vertex.getX(), vertex.getY(), 50, 50);
				g.fillOval(vertex.getX(), vertex.getY(), 50, 50);

				g.setColor(Color.BLACK);
				int x = vertex.getX() + 25;
			
				g.drawString(vertex.getName(), x - ((int)(vertex.getName().length() / 2) * 6), vertex.getY() + 30);
			}
		}
		else
		{
			//If nothing has been selected to be displayed, show the splash page.

			g.setColor(Color.YELLOW);
			g.drawOval(300, 150, 400, 400);
			g.fillOval(300, 150, 400, 400);
			
			BufferedImage img = null;

			try{
				img = ImageIO.read(new File("Data/PythThm.png"));
			}
			catch(IOException e){}

			g.drawImage(img, 350, 150, 300, 375, null, null);

			g.setColor(Color.BLACK);
			g.setFont(new Font("Cambria Math", Font.PLAIN, 120));

			g.drawString("Euclid", 300, 100);

			g.setFont(new Font("Cambria Math", Font.PLAIN, 60));

			g.drawString("21", 625, 50);

			g.setColor(Color.CYAN);
			g.drawOval(100, 100, 200, 30);
			g.fillOval(100, 100, 200, 30);
			g.drawOval(700, 100, 200, 30);
			g.fillOval(700, 100, 200, 30);

			g.setColor(Color.MAGENTA);
			g.drawOval(25, 300, 200, 30);
			g.fillOval(25, 300, 200, 30);
			g.drawOval(775, 300, 200, 30);
			g.fillOval(775, 300, 200, 30);

			g.setColor(Color.GREEN);
			g.drawOval(70, 500, 200, 30);
			g.fillOval(70, 500, 200, 30);
			g.drawOval(730, 500, 200, 30);
			g.fillOval(730, 500, 200, 30);

			g.setColor(Color.YELLOW);
			g.drawRect(400, 575, 200, 50);
			g.fillRect(400, 575, 200, 50);

			g.setColor(Color.BLACK);

			g.setFont(new Font("Cambria Math", Font.PLAIN, 12));

			g.drawString("Dr. Eugene Boman", 160, 110);
			g.drawString("Penn State, Harrisburg", 150, 125);

			g.drawString("Tyler Brown", 85, 310);
			g.drawString("Penn State, Harrisburg", 75, 325);

			g.drawString("Siddharth Dahiya", 760, 110);
			g.drawString("Penn State, Harrisburg", 750, 125);

			g.drawString("Joseph Roberge", 835, 310);
			g.drawString("Penn State, Harrisburg", 825, 325);

			g.drawString("Alexandra Milbrand", 120, 510);
			g.drawString("Penn State, Harrisburg", 110, 525);

			g.drawString("Mary Boman", 790, 510);
			g.drawString("Bryn Mawr College", 780, 525);

			g.setFont(new Font("Times New Roman", Font.BOLD, 12));
			g.drawString("Based on the translation by:", 430, 590);
			g.drawString("Dr. Richard Fitzpatrick", 450, 605);
			g.drawString("The University of Texas", 440, 620);
			
		}
	}
	
	public void display(Node node)
	{	
		on = true;
		
		for(int i = 0; i < displayedVertexes.size(); i++)
		{
			Vertex vertex = displayedVertexes.get(i);
			vertex.getNode().getMenu().setVisible(false);
		}

		int length = displayedVertexes.size();

		for(int i = 0; i < length; i++)
			displayedVertexes.remove(0);

		length = displayedEdges.size();

		for(int i = 0; i < length; i++)
			displayedEdges.remove(0);

		unhighlight();

		int x = (width / 2) - 25;
		int y = (getHeight() / 2) + 10;
		
		displayedVertexes.add(node.getVertex());
		node.getVertex().setCoordinates(x, y);
		
		ArrayList<NodeCounter> list = node.getRoot();
		
		x = 10;
		y -= 80;
		
		for(int i = 0; i < list.size(); i++)
		{
			displayedVertexes.add(list.get(i).getNode().getVertex());
			list.get(i).getNode().getVertex().setCoordinates(x, y);
			x += 60;
		}
		
		list = node.getTree();
		
		x = 10;
		y += 160;
		
		for(int i = 0; i < list.size(); i++)
		{
			displayedVertexes.add(list.get(i).getNode().getVertex());
			list.get(i).getNode().getVertex().setCoordinates(x, y);
			x += 60;
		}
		
		ArrayList<Edge> edgeList = node.getVertex().getEdges();
		
		for(int i = 0; i < edgeList.size(); i++)
		{
			edgeList.get(i).updateCoordinates();
			displayedEdges.add(edgeList.get(i));
		}

		repaint();
	}

	public void addToDisplay(ArrayList<NodeCounter> nodes, Node node, boolean isRoot)
	{
		ArrayList<Edge> edges = node.getVertex().getEdges();

		int x = 10;
		int y;

		if(isRoot)
			y = node.getVertex().getY() - 80;
		else
			y = node.getVertex().getY() + 80;

		if(y < 0)
			y = 0;
		else if(y > getHeight() - 50)
			y = getHeight() - 50;

		for(int i = 0; i < nodes.size(); i++)
		{
			Vertex v = nodes.get(i).getNode().getVertex();
			
			if(!displayedVertexes.contains(v))
			{
				displayedVertexes.add(v);
				v.setCoordinates(x, y);
			}

			x += 60;

			for(int j = 0; j < edges.size(); j++)
			{
				if((edges.get(j).getStartVertex().equals(v) || edges.get(j).getEndVertex().equals(v)) &&
				   (edges.get(j).getStartVertex().getNode().equals(node) || edges.get(j).getEndVertex().getNode().equals(node)) &&
				    !displayedEdges.contains(edges.get(j)))
				{
					displayedEdges.add(edges.get(j));
				}
			}
		}
		
		repaint();
	}

	public void removeFromDisplay(Node node)
	{
		for(int i = 0; i < displayedVertexes.size(); i++)
		{
			if(displayedVertexes.get(i).getNode().equals(node))
				displayedVertexes.remove(i);
		}

		for(int i = 0; i < displayedEdges.size(); i++)
		{
			if(displayedEdges.get(i).getStartVertex().getNode().equals(node) || displayedEdges.get(i).getEndVertex().getNode().equals(node))
			{
				displayedEdges.remove(i);
				i--;
			}
		}

		for(int i = 0; i < highlightedEdges.size(); i++)
		{
			if(highlightedEdges.get(i).getStartVertex().getNode().equals(node) || highlightedEdges.get(i).getEndVertex().getNode().equals(node))
			{
				highlightedEdges.remove(i);
				i--;
			}
		}

		if(node.equals(highlightedNode))
		{
			unhighlight();
		}

		removeOrphanedVertexes();

		repaint();
	}

	public void removeOrphanedVertexes()
	{
		if(displayedEdges.size() == 0)
		{
			int size = displayedVertexes.size();
				
			for(int i = 0; i < size; i++)
			{
				displayedVertexes.remove(0);
			}
		}
		else
		{

			for(int i = 0; i < displayedVertexes.size(); i++)
			{
				Vertex vertex = displayedVertexes.get(i);

				for(int j = 0; j < displayedEdges.size(); j++)
				{
					if(displayedEdges.get(j).getStartVertex().equals(vertex) || displayedEdges.get(j).getEndVertex().equals(vertex))
					{
						break;
					}
					else if(j == displayedEdges.size() - 1)
					{
						displayedVertexes.remove(i);
						i--;
					}
				}
			}
		}
	}

	public void highlight(Node node)
	{
		unhighlight();
		
		node.setHighlighted(true);
		highlightedNode = node;
		
		Vertex vertex = node.getVertex();

		for(int i = 0; i < displayedEdges.size(); i++)
		{
			if(displayedEdges.get(i).getStartVertex().equals(vertex) || displayedEdges.get(i).getEndVertex().equals(vertex))
			{
				highlightedEdges.add(displayedEdges.get(i));
			}
		}

		repaint();
	}

	public void unhighlight()
	{
		highlightedNode.setHighlighted(false);
		
		while(highlightedEdges.size() > 0)
		{
			highlightedEdges.remove(0);
		}
		repaint();
	}

	public void mouseClicked(MouseEvent e)
	{
		//Make any visible popup menus disappear.
		for(int i = 0; i < displayedVertexes.size(); i++)
		{
			Vertex vertex = displayedVertexes.get(i);
			vertex.getNode().getMenu().setVisible(false);
		}

		//If the mouse is right-clicked on the screen.
		//Similarly, use MouseEvent.BUTTON1 for left-click.
		if(e.getButton() == MouseEvent.BUTTON3)
		{
			//Check if/which vertex was clicked in.
			for(int i = 0; i < displayedVertexes.size(); i++)
			{
				Vertex vertex = displayedVertexes.get(i);
				
				if((e.getX() > vertex.getX() && e.getX() < vertex.getX() + 50) &&
					(e.getY() > vertex.getY() && e.getY() < vertex.getY() + 50))
				{
					//Show the popup menu associated with the node of the clicked vertex.
					
					JPopupMenu menu = vertex.getNode().getMenu();
					menu.setLocation(e.getXOnScreen(), e.getYOnScreen());
					menu.setVisible(true);

					//If one vertex is on top of another, you don't want to manipulate them both, so . . .
					break;
				}
			}
		}
		else if(e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2)
		{
			//Check if/which vertex was clicked in.
			for(int i = 0; i < displayedVertexes.size(); i++)
			{
				Vertex vertex = displayedVertexes.get(i);
				
				if((e.getX() > vertex.getX() && e.getX() < vertex.getX() + 50) &&
					(e.getY() > vertex.getY() && e.getY() < vertex.getY() + 50))
				{
					if(vertex.getNode().getImage() != null && vertex.getNode().getImage().canRead())	
					{
						try
						{
							Desktop.getDesktop().open(vertex.getNode().getImage());
						}
						catch(IOException ex) {}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Error Reading Files\nClick 'OK' to open FAQ.");
						try
						{
							Desktop.getDesktop().open(new File(System.getProperty("user.dir") + "/FAQ.txt"));
						}catch(IllegalArgumentException ex)
						{
							JOptionPane.showMessageDialog(null, "Error opening FAQ.\nYou may have moved it or changed the name.");
						}catch(IOException ex)
						{
							JOptionPane.showMessageDialog(null, "Error opening FAQ.\nYou may have moved it or changed the name.");
						}
					}
				}
			}
		}
	}
	
	public void mouseReleased(MouseEvent e)
	{
		for(int i = 0; i < displayedVertexes.size(); i++)
			displayedVertexes.get(i).clicked = false;
	}
	
	public void mouseEntered(MouseEvent e){}
	
	public void mouseExited(MouseEvent e) {}
	
	public void mousePressed(MouseEvent e)
	{	
		for(int i = displayedVertexes.size() - 1; i >=0; i--)
		{
			Vertex vertex = displayedVertexes.get(i);
			if((e.getX() > vertex.getX() && e.getX() < vertex.getX() + 50) &&
				(e.getY() > vertex.getY() && e.getY() < vertex.getY() + 50))
			{	
				vertex.clicked = true;

				//If one vertex is on top of another, you don't want to move them both, so . . .
				break;
			}
		}
	}
	
	public void mouseMoved(MouseEvent e) 
	{
		for(int i = 0; i < displayedVertexes.size(); i++)
		{
			Vertex vertex = displayedVertexes.get(i);
			
			if((e.getX() > vertex.getX() && e.getX() < vertex.getX() + 50) &&
				(e.getY() > vertex.getY() && e.getY() < vertex.getY() + 50))
			{
				this.setToolTipText(vertex.getNode().toString());
				break;
			}
			else if(i == displayedVertexes.size() - 1)
			{
				this.setToolTipText(null);
			}
		}
	}
	
	public void mouseDragged(MouseEvent e)
	{
		for(int i = 0; i < displayedVertexes.size(); i++)
		{
			Vertex vertex = displayedVertexes.get(i);
			if(vertex.clicked)
			{
				vertex.setX(e.getX() - 25);
				vertex.setY(e.getY() - 25);
				repaint();
			}
		}
	}
}
