package gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.function.Function2D;
import org.jfree.data.function.NormalDistributionFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;

import valueobjects.Ereignis;

public class ArtikelProtokollPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5624925222881327740L;
	int[] data= {
	        21, 14, 18, 03, 86, 88, 74, 87, 54, 77,
	        61, 55, 48, 60, 49, 36, 38, 27, 20, 18,
	    };
//	int [] data;
	// hier kommen jeweils die artikelmenge an tag 1-30 rein
    final int PAD = 30;
	public ArtikelProtokollPanel(List<Ereignis> artikelVerlauf, String artikelName) {
		super();
		this.setLayout(new GridLayout(1,1));
//		data = new int[PAD];
//		
//		for(Ereignis e:artikelVerlauf) {
//			data[i] = e.getMenge();
//		}
//		
//		for (int i=0;i<PAD;i++) {
//			
//			
//		}
		for(Ereignis e:artikelVerlauf) {
			System.out.println("" + e);
		}
//		int[] x = { 1,2,3,4,5,6,7,9,10 }; // von eins bis 30
//		int [] y = 
		Function2D normal = new NormalDistributionFunction2D(0.0, 1.0);
		XYDataset dataset = DatasetUtilities.sampleFunction2D(normal, -0.5, 5.0, 100, artikelName);
		final JFreeChart chart= ChartFactory.createXYLineChart(null, "Zeitpunkt", "Lagerbestand", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500,270));
		this.add(chartPanel);
	} 
}