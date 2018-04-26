/*
	TestDraw class for Euclid 21 Program; draws and sets up windows for the program.
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
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestDraw extends JFrame
{
	private static EuclidDisplay display;
	private static EuclidGraph graph;

	public TestDraw(EuclidGraph eGraph)
	{
		setBounds(200, 0, 1000, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		display = new EuclidDisplay();
		JScrollPane scroll = new JScrollPane(display);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		add(scroll);
		
		setVisible(true);

		graph  = eGraph;

		for(int i = 0; i < graph.getGraph().size(); i++)
		{
			for(int j = 1; j < graph.getGraph().get(i).getProps().size(); j++)
			{
				graph.getProp(i, j).setDisplay(display);
			}
			for(int j = 1; j < graph.getGraph().get(i).getDefs().size(); j++)
			{
				graph.getDef(i, j).setDisplay(display);
			}
		}

		new TestDraw();
	}

	public TestDraw()
	{
		setSize(200, 600);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		DisplayChooser chooser = new DisplayChooser(display, graph);
		JScrollPane scroll = new JScrollPane(chooser);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.	HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll);

		setTitle("Books");
		
		setVisible(true);	
	}

	public TestDraw(int bkNum)
	{
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		DisplayChooser chooser = new DisplayChooser(bkNum, this);
		JScrollPane scroll = new JScrollPane(chooser);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.	HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll);

		setSize(scroll.getPreferredSize());

		if(bkNum == 0)
			setTitle("Axioms");
		else
			setTitle("Book " + bkNum);
		
		setVisible(true);
		
	}
}
