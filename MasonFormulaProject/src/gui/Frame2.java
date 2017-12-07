package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Traverser.Graph;
import java.awt.BorderLayout;

public class Frame2 {

	private JFrame frame2;
	private int num;
	private Object[] nodes;
	JApplet visual;
	String nodeNum;
	private boolean flag = false;
	private JTextField from;
	private JTextField to;
	private JGraphViewer graphViewer;
	private Component Ingraph[];
	int x = 10;
	int y = 100;
	private ImageIcon title;
	Graph all;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame2 window = new Frame2();
					window.frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame2 = new JFrame("Calculate");
		frame2.setBounds(0, 0, 1200, 700);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame2.getContentPane().setBackground(Color.decode("#FFFF66"));

		DefaultTableModel model = new DefaultTableModel(); 
		JTable table = new JTable(model); 
		DefaultTableModel model1 = new DefaultTableModel(); 
		JTable table1 = new JTable(model1); 
		table.setRowHeight(30);
		table1.setRowHeight(30);

		JPanel panel= new JPanel();
		panel.setBounds(0, 0, 1200, 400);
		model.addColumn("Col1"); 
		model.addColumn("Col2"); 
		model.addColumn("Col3"); 
		model1.addColumn("Col1"); 
		model1.addColumn("Col2"); 
		model1.addColumn("Col3"); 

		
			model.addRow(new String[]{"Forward paths", "Gain","Deltas"});
			model1.addRow(new String[]{" Loops", "Gain","Individual or non-touching"});

		panel.setLayout(new GridLayout(1, 2));
		table.setBackground(Color.decode("#FFFF66"));;
		table.setGridColor(Color.BLACK);
	//	table1.setBackground(Color.decode("#FFFF66"));;
		table1.setGridColor(Color.BLACK);
	        frame2.getContentPane().setLayout(null);
	        JLabel lblDetla = new JLabel("Transfer function  = ");
	        lblDetla.setBounds(71, 500, 142, 50);
	        frame2.getContentPane().add(lblDetla);
	        JLabel lblAns= new JLabel("");
	        lblAns.setBounds(200, 500, 300, 50);
	        frame2.getContentPane().add(lblAns);

	        panel.add(table,BorderLayout.WEST);
	        panel.add(table1,BorderLayout.EAST);

			 frame2.getContentPane().add(panel);


		frame2.setVisible(true);
		
	}
}
