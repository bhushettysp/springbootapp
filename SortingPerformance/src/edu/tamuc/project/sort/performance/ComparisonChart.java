package edu.tamuc.project.sort.performance;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * 
 * @author Siva Prasad Bhushetty CWID:50145947
 * 
 * */
public class ComparisonChart extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double[] values;

	private String[] names;

	private String title;

	public ComparisonChart(double[] v, String[] n, String t) {
		names = n;
		values = v;
		title = t;
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (values == null || values.length == 0)
			return;
		double minValue = 0;
		double maxValue = 0;
		for (int i = 0; i < values.length; i++) {
			if (minValue > values[i])
				minValue = values[i];
			if (maxValue < values[i])
				maxValue = values[i];
		}

		Dimension d = getSize();
		;
		int clientWidth = d.width;
		int clientHeight = d.height;
		g.setColor(Color.CYAN);
		g.fillRect(0, 0,clientWidth , clientHeight);
		int barWidth = clientWidth / values.length;
		Font titleFont = new Font("SansSerif", Font.BOLD, 20);
		FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
		Font labelFont = new Font("SansSerif", Font.PLAIN, 10);
		FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

		int titleWidth = titleFontMetrics.stringWidth(title);
		int y = titleFontMetrics.getAscent();
		int x = (clientWidth - titleWidth) / 2;
		//int lateralShift=x;
		//int barWidth = titleWidth / values.length;
		g.setFont(titleFont);
		g.setColor(Color.black);
		g.drawString(title, x, y);

		int top = titleFontMetrics.getHeight();
		int bottom = labelFontMetrics.getHeight();
		if (maxValue == minValue)
			return;
		double scale = (clientHeight - top - bottom) / (maxValue - minValue);
		y = clientHeight - labelFontMetrics.getDescent();
		g.setFont(labelFont);

		for (int i = 0; i < values.length; i++) {
			int valueX = i * barWidth + 1;
			int valueY = top;
			int height = (int) (values[i] * scale);
			if (values[i] >= 0)
				valueY += (int) ((maxValue - values[i]) * scale);
			else {
				valueY += (int) (maxValue * scale);
				height = -height;
			}
			if (i == 0) {
				g.setColor(Color.RED);
				
			}else if(i == 1){
				g.setColor(Color.YELLOW);
			}else if(i == 2){
				g.setColor(Color.BLUE);
			}else if(i == 3){
				g.setColor(Color.GREEN);
			}else{
				g.setColor(Color.MAGENTA);
			}
			//g.fillRect(valueX+lateralShift, valueY, barWidth - 40, height);
			g.fillRect(valueX, valueY, barWidth - 2, height);
			g.setColor(Color.black);
			//g.drawRect(valueX+lateralShift, valueY, barWidth - 40, height);
			g.drawRect(valueX, valueY, barWidth - 2, height);
			int labelWidth = labelFontMetrics.stringWidth(names[i]);
			x = i * barWidth + (barWidth - labelWidth) / 2;
			//g.drawString(names[i], x+lateralShift-20, y);
			g.drawString(names[i], x, y);
		}
	}

	public static void draw(AlgorithmsTimeHolder record) {
		// int i=0;
		JFrame f = new JFrame();
		f.setSize(800, 600);
		f.setTitle("Sorting Algorithms Comparison");
		while (true) {
			// i++;
			double[] values = new double[record.getAlgorithmsSize()];
			String[] names = new String[record.getAlgorithmsSize()];
			values[0] = record.getQuick();
			names[0] = "QuickSort (" + record.getQuick() + ")";

			values[1] = record.getBubble();
			names[1] = "BubbleSort (" + record.getBubble() + ")";

			values[2] = record.getHeap();
			names[2] = "HeapSort (" + record.getHeap() + ")";
			
			values[3] = record.getInsertion();
			names[3] = "InsertionSort (" + record.getInsertion() + ")";
			
			values[4] = record.getSelection();
			names[4] = "SelectionSort (" + record.getSelection() + ")";

			f.getContentPane().add(
					new ComparisonChart(values, names, "Sorting Algorithms Comparison"));

			WindowListener wndCloser = new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			};
			f.addWindowListener(wndCloser);
			f.setVisible(true);
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] argv) throws Exception {
		long algorithmDurations[]=new long[5];
		for(int i=0;i<algorithmDurations.length;i++)
			algorithmDurations[i] = (i+1)*200;
		System.out.println("algorithmDurations: "+algorithmDurations.length);
		final AlgorithmsTimeHolder record = new AlgorithmsTimeHolder(10, algorithmDurations);
		Thread t = new Thread(new Runnable() {

			public void run() {
				draw(record);
			}
		});
		t.start();
		// draw(record);
		Thread.sleep(100);
		record.setQuick(1000);
		Thread.sleep(100);
		// draw(100, 200, 500);
		// draw(100, 200, 600);
		// draw(100, 200, 700);
	}
}



