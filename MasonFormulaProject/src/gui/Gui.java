package gui;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jgraph.JGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.event.GraphVertexChangeEvent;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

import Traverser.Calculate;
import Traverser.DFSCycle;
import Traverser.Graph;
import Traverser.Node;
import Traverser.Search;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.management.loading.PrivateClassLoader;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

public class Gui {

	private JFrame frame;
	private JFrame frame2;
	private JTextField textField_1;
	private JTextField textField_2;
	private int num;
	private boolean flag=false;

	private Object[] nodes;
	private JGraphViewer graphViewer;

	private ImageIcon title;
	private Graph 	all = new Graph();
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private String[][] forwardTable;
	private String[][] loopTable;
	private LinkedList<LinkedList<String>> adjForword;
	private LinkedList<Integer> gainForword;
	private LinkedList<Integer> gainLoop;
	private LinkedList<Integer> deltas;
	private LinkedList<LinkedList<String > > nonTouch1;
    private LinkedList<LinkedList<String > > nonTouch2;
    private LinkedList<Integer> nonTouchGain;

	private LinkedList<LinkedList<String>> adjloop;
	private DefaultTableModel model ;
	private JTable table;
	private DefaultTableModel model1 ;
	private JTable table1 ;
	private Search forward;
	private DFSCycle cycle;
	private JLabel lblDeltaAns;
	private JLabel lblTFAns;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Signal flow graph");
		frame.setBounds(0, 0, 1300, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graphViewer = new JGraphViewer();

		startViewer();
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(graphViewer);
		title = new ImageIcon(getClass().getResource("solver.png"));
		JLabel titleLabel = new JLabel(title);
		titleLabel.setBounds(458, 23, 637, 131);
		frame.getContentPane().add(titleLabel);

		JLabel lblFrom = new JLabel("from");
		lblFrom.setBounds(74, 96, 46, 14);
		frame.getContentPane().add(lblFrom);

		JLabel lblNewLabel = new JLabel("to");
		lblNewLabel.setToolTipText(" ");
		lblNewLabel.setBounds(235, 96, 28, 14);
		frame.getContentPane().add(lblNewLabel);

		textField_2 = new JTextField();
		textField_2.setBounds(165, 137, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(205, 43, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		comboBox = new JComboBox();
		comboBox_1 = new JComboBox();
		comboBox.setBounds(149, 93, 46, 20);
		comboBox_1.setBounds(273, 93, 46, 20);
		frame.getContentPane().add(comboBox_1);
		frame.getContentPane().add(comboBox);
		JButton btnEnter = new JButton("Enter");

		btnEnter.setBounds(327, 42, 89, 23);
		
		frame.getContentPane().add(btnEnter);
		JLabel lblEnterTheNumber = new JLabel("Enter the number of nodes");
		lblEnterTheNumber.setBounds(54, 46, 152, 14);
		frame.getContentPane().add(lblEnterTheNumber);
		
		JLabel lblEnter = new JLabel("Enter the gain ");
		lblEnter.setBounds(36, 140, 84, 14);
		frame.getContentPane().add(lblEnter);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nodeNum = textField_1.getText();
				if (nodeNum != null) {
					num = Integer.parseInt(nodeNum);
					if (num > 0) {
						if(nodes!=null){
							for(int j=0;j<nodes.length;j++){
								graphViewer.removeVertex(nodes[j].toString());
								all=new Graph();
							}
						}
						nodes = new String[num];
						comboBox.removeAllItems();
						comboBox_1.removeAllItems();
						int x=100;
						int y=100;
						int i;
						
						for ( i = 0; i < num; i++) {
							nodes[i] = "y" + String.valueOf(i + 1);
							comboBox.addItem(nodes[i]);
							comboBox_1.addItem(nodes[i]);
							graphViewer.drawVertex(nodes[i].toString());
							graphViewer.positionVertexAt(nodes[i].toString(), x, y, 40, 40);
							x=(i+1)*1000/num;
							if((i+1)%2==0)
								y-=100;
							else
								y+=100;
						}
						graphViewer.drawEdge("S", nodes[0].toString(), "1.");
						graphViewer.drawEdge(nodes[num-1].toString(),"C" , "1.  ");
						all.addEdge("S", nodes[0].toString(), 1);
						all.addEdge(nodes[num-1].toString(),"C", 1);

					}
				}
			}
		});
		JButton btnDone = new JButton("Add edge");
		frame.getContentPane().add(btnDone);
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
						String gain = textField_2.getText();
						String vertex1 = comboBox.getSelectedItem().toString();
						String vertex2 = comboBox_1.getSelectedItem().toString();

				if( !gain.isEmpty() ){
					if ((!all.isConnected(vertex1, vertex2))) {
					
						
							graphViewer.drawEdge(vertex1, vertex2, gain);
							
						
						vertex1.replace(" ", "");
						vertex2.replace(" ", "");
						gain =gain.replace(" ", "");

						all.addEdge(vertex1, vertex2, Integer.parseInt(gain));
						LinkedList<Node>adjNodes = new LinkedList<>();
						adjNodes=all.adjacentNodes(vertex1);
						for(int i=0;i<adjNodes.size();i++){
							System.out.println(vertex1+"  "+adjNodes.get(i).getValue()+" "+adjNodes.get(i).getGain());
						}
						System.out.println();


					}

				}
				textField_2.setText(null);
				

			}
		});
		btnDone.setBounds(327, 131, 89, 23);
		JButton btnCal = new JButton("Calculate T.F");
		btnCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Initialize2();
				LinkedList<String> visited = new LinkedList<>();
				visited.add("S");
				 forward = new Search();
				forward.depthFirst(all, visited);
				adjForword = forward.getAdjForword();
				//System.out.println(adjForword.size());
				gainForword = forward.getGainForword();
				visited = new LinkedList<>();
				visited.add("S");
				 cycle = new DFSCycle();

				cycle.depthFirst(all, new LinkedList<>(), visited);
				cycle.deleteDublicate(cycle.getAdjloop(), cycle.getGainloop());
				adjloop=cycle.getAdjloop();
				for(int i=0;i<adjloop.size();i++){
					System.out.println(adjloop.get(i));
				}
				gainLoop = cycle.getGainloop();
				System.out.println("hiiii");

				forwardTable=new String  [adjForword.size()+1][3];
				Calculate ans= new Calculate( cycle.getAdjloop(), cycle.getGainloop(), forward.getAdjForword(),
						forward.getGainForword());
				deltas=ans.calcDeltaForword();
				lblDeltaAns.setText(String.valueOf(ans.calcDelta()));
				lblTFAns.setText(String.valueOf(ans.getresault()));
				nonTouch1=ans.getNonTouch1();
				nonTouch2= ans.getNonTouch2();
				nonTouchGain=ans.getNonTouchGain();
				loopTable=new String  [adjloop.size()+1+nonTouch1.size()+1][3];
				putTable();

				frame2.setVisible(true);
			}
		});

		btnCal.setBounds(159, 165, 149, 23);
		frame.getContentPane().add(btnCal);
		frame.getContentPane().setBackground(Color.decode("#FFFF66"));

	}

	private void startViewer() {
		graphViewer.setBounds(100, 200, 1000, 550);
		graphViewer.drawVertex("S");
		graphViewer.positionVertexAt("S", 0, 100, 40, 40);
		graphViewer.drawVertex("C");
		graphViewer.positionVertexAt("C", 950, 100, 40, 40);
		graphViewer.init();

	}

	private void Initialize2() {
		frame2 = new JFrame("Calculate");
		frame2.setBounds(0, 0, 1200, 700);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame2.getContentPane().setBackground(Color.decode("#FFFF66"));
		  model = new DefaultTableModel();
		  table = new JTable(model);
		  model1 = new DefaultTableModel();
		  table1 = new JTable(model1);
		  table.setRowHeight(30);
			table1.setRowHeight(30);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 400);
		model.addColumn("Col1");
		model.addColumn("Col2");
		model.addColumn("Col3");
		model1.addColumn("Col1");
		model1.addColumn("Col2");
		model1.addColumn("Col3");
		model.addRow(new String[] { "Forward paths", "Gain", "Deltas" });
		model1.addRow(new String[] { " Loops", "Gain", "Individual or non-touching" });

		panel.setLayout(new GridLayout(1, 2));
		table.setBackground(Color.decode("#FFFF66"));
		;
		table.setGridColor(Color.BLACK);
		table1.setBackground(Color.decode("#FFFF66"));
		;
		table1.setGridColor(Color.BLACK);
		frame2.getContentPane().setLayout(null);
		JLabel lblDetla = new JLabel("Total Delta  = ");
		lblDetla.setBounds(71, 500, 142, 50);
		frame2.getContentPane().add(lblDetla);
		 lblDeltaAns = new JLabel("");
		lblDeltaAns.setBounds(200, 500, 300, 50);
		frame2.getContentPane().add(lblDeltaAns);

		JLabel lblTF = new JLabel("Transfer function  = ");
		lblTF.setBounds(71, 550, 142, 50);
		frame2.getContentPane().add(lblTF);
		
		 lblTFAns = new JLabel("");
		lblTFAns.setBounds(200, 550, 300, 50);
		frame2.getContentPane().add(lblTFAns);
		
		panel.add(table, BorderLayout.WEST);
		panel.add(table1, BorderLayout.EAST);

		frame2.getContentPane().add(panel);
	}
	private void putTable() {
		for(int i=0;i<adjForword.size();i++){
			forwardTable[i][0]="";
			for(int j=0;j<adjForword.get(i).size();j++){
				forwardTable[i][0]+=adjForword.get(i).get(j)+" ";
				System.out.print(adjForword.get(i).get(j)+" ");
			}
			System.out.println("");
			forwardTable[i][1]=String.valueOf(gainForword.get(i));
			forwardTable[i][2]=String.valueOf(deltas.get(i));
			model.addRow(forwardTable[i]);
		}
		for(int i=0;i<adjloop.size();i++){
			loopTable[i][0]="";
			for(int j=0;j<adjloop.get(i).size();j++){
				loopTable[i][0]+=adjloop.get(i).get(j)+" ";
			}
			System.out.println(adjloop.get(i));

			System.out.println("");
			loopTable[i][1]=String.valueOf(gainLoop.get(i));
			loopTable[i][2]=String.valueOf("Individual");
			model1.addRow(loopTable[i]);
		}
		for(int i=0;i<nonTouch1.size();i++){
			for(int j=0;j<nonTouch1.get(i).size();j++){
				loopTable[i][0]+=nonTouch1.get(i).get(j)+" ";

			}
			loopTable[i][0]+=", ";
			for(int j=0;j<nonTouch2.get(i).size();j++){
				loopTable[i][0]+=nonTouch2.get(i).get(j)+" ";

			}
			System.out.println("");
			loopTable[i][1]=String.valueOf(nonTouchGain.get(i));
			loopTable[i][2]=String.valueOf("Non - touching");
			model1.addRow(loopTable[i]);
		}
		
	}
}
