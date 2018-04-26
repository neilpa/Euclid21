import java.util.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class DisplayChooser extends JComponent implements ActionListener
{
	private static JButton[] buttons;
	private JButton[][] secondButtons;
	private JButton help;
	private static EuclidGraph graph;
	private static EuclidDisplay display;
	int bookNumber = 0;
	TestDraw secondWindow;

	public DisplayChooser(EuclidDisplay d, EuclidGraph g)
	{
		graph = g;

		display = d;

		help = new JButton();
		help.setSize(125, 50);
		help.setLocation(30, 0);
		add(help);
		help.addActionListener(this);
		help.setText("Help");
		
		buttons = new JButton[graph.getGraph().size()];

		setPreferredSize(new Dimension(200, (buttons.length + 1) * 50));

		for(int i = 0; i < graph.getGraph().size(); i++)
		{
			buttons[i] = new JButton();
			buttons[i].setSize(125, 50);
			buttons[i].setLocation(30, (i + 1) * 50);
			add(buttons[i]);
			buttons[i].addActionListener(this);
			if(i == 0)
				buttons[i].setText("Axioms");
			else
				buttons[i].setText("Book " + ((Integer)i).toString());
		}
	}

	public DisplayChooser(int bkNum, TestDraw window)
	{
		secondButtons = new JButton[2][];
		secondButtons[1] = new JButton[graph.getGraph().get(bkNum).getDefs().size()];
		secondButtons[0] = new JButton[graph.getGraph().get(bkNum).getProps().size()];
		secondWindow = window;


		if(secondButtons[1].length == 1)
		{
			setPreferredSize(new Dimension(200, (secondButtons[0].length) * 50));
		}
		else if(secondButtons[0].length > secondButtons[1].length)
		{
			setPreferredSize(new Dimension(340, (secondButtons[0].length) * 50));
		}
		else
		{
			setPreferredSize(new Dimension(340, (secondButtons[1].length) * 50));
		}

		for(int i = 1; i < secondButtons[0].length; i++)
		{
			secondButtons[0][i] = new JButton();
			secondButtons[0][i].setSize(140, 50);
			secondButtons[0][i].setLocation(10, (i - 1) * 50);
			add(secondButtons[0][i]);
			secondButtons[0][i].addActionListener(this);

			secondButtons[0][i].setText(graph.getGraph().get(bkNum).getProp(i).getType() + " " + ((Integer)i).toString());
		}

		for(int i = 1; i < secondButtons[1].length; i++)
		{
			secondButtons[1][i] = new JButton();
			secondButtons[1][i].setSize(140, 50);
			secondButtons[1][i].setLocation(160, ((i - 1) * 50));
			add(secondButtons[1][i]);
			secondButtons[1][i].addActionListener(this);
			
			secondButtons[1][i].setText(graph.getGraph().get(bkNum).getDef(i).getType() + " " + ((Integer)i).toString());
		}
		bookNumber = bkNum;
	}

	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource().equals(help))
		{
			try
			{
				Desktop.getDesktop().open(new File("Data/help.pdf"));
			}
			catch(IOException ex) {}
		}

		int buttonClicked = 0;
		
		if(buttons != null)
		{
			for(int i = 0; i < buttons.length; i++)
				if(evt.getSource().equals(buttons[i]))
				{
					buttonClicked = i;
					new TestDraw(buttonClicked);
				}
		}

		int secondButtonClicked = 1;
		
		if(secondButtons != null)
		{
			for(int i = 1; i < secondButtons[0].length; i++)
				if(evt.getSource().equals(secondButtons[0][i]))
				{
					secondButtonClicked = i;
					display.display(graph.getProp(bookNumber, secondButtonClicked));
					secondWindow.dispose();
				}

			for(int i = 1; i < secondButtons[1].length; i++)
				if(evt.getSource().equals(secondButtons[1][i]))
				{
					secondButtonClicked = i;
					display.display(graph.getDef(bookNumber, secondButtonClicked));
					secondWindow.dispose();
				}
		}
	}
}
