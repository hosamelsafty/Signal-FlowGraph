package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JApplet;
import javax.swing.JPanel;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.event.VertexSetListener;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;

public class JGraphViewer extends JApplet {
	ListenableGraph g = new ListenableDirectedWeightedGraph<>(DefaultEdge.class);
	//
	private JGraphModelAdapter m_jgAdapter = new JGraphModelAdapter(g);
	private JGraph jgraph = new JGraph(m_jgAdapter);
	/**
	 * @see java.applet.Applet#init().
	 */
	public void init() {

		this.getContentPane().add(jgraph);
//		this.drawVertex("S");
//		this.drawVertex("a");
//		this.drawVertex("b");
//		this.drawVertex("c");
//		this.drawEdge("b", "c", "1");
//		this.drawEdge("b", "a", "2");


	}

	public void drawVertex(String vertex) {
		g.addVertex(vertex);
	}
	public void removeVertex(String vertex) {
		g.removeVertex(vertex);		
	}
	public void drawEdge(String vertex1, String vertex2, String gain) {
		g.addEdge(vertex1, vertex2, gain);
		
	}

	public void positionVertexAt(Object vertex, int x, int y, int width, int height) {
		DefaultGraphCell cell = m_jgAdapter.getVertexCell(vertex);
		Map attr = cell.getAttributes();
		Map cellAttr = new HashMap();
	
		GraphConstants.setBounds(attr, new Rectangle(x, y, width, height));
		
		cellAttr.put(cell, attr);
		m_jgAdapter.edit(cellAttr, null, null, null);
	}
	
}