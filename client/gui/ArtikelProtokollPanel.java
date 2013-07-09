package gui;

import java.awt.GridLayout;
import java.util.Calendar;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import valueobjects.Ereignis;

public class ArtikelProtokollPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5624925222881327740L;
	public ArtikelProtokollPanel(List<Ereignis> artikelVerlauf, String artikelName) {
		super();
		this.setLayout(new GridLayout(1,1));
		for(Ereignis e:artikelVerlauf) {
			System.out.println(e);			
		}
		drawChart(artikelVerlauf, artikelName);
	}
    public static XYDataset createDataset(List<Ereignis> artikelVerlauf, String artikelName) {
	    XYSeries xyseries = new XYSeries(artikelName);
	    

		Calendar heute = Calendar.getInstance();
		for(Ereignis e:artikelVerlauf) {
			// in ms
			// xyseries.add(e.getDate().getTime(), e.getArtikel().getMenge());
			long zeitVergangen = e.getDate().getTime() - heute.getTime().getTime();  // Differenz in ms
			long inTagen = Math.round( (double)zeitVergangen / (24. * 60.*60.*1000.) ); // Zeit Differenz in Tagen
			xyseries.add(inTagen, e.getArtikel().getMenge());
		}
		
//		for(int i=0;i<30;i++) {
//			// ereignis passend zu dem tag heraussuchen falls es ein gibt
//			Ereignis e = 
			// menge an dem tag heraussuchen
//			xyseries.add(i, e.getArtikel().getMenge());
//		}
		
		
		
	    XYSeriesCollection xyseriescollection = new XYSeriesCollection();   
	    xyseriescollection.addSeries(xyseries);    
	    xyseriescollection.setIntervalWidth(0.0D);   
	    return xyseriescollection;   
	} 
    public void drawChart(List<Ereignis> artikelVerlauf, String artikelName) {
    	// laeuft
        XYDataset dataset = createDataset(artikelVerlauf, artikelName);  
		final JFreeChart chart= ChartFactory.createXYLineChart(null, "Zeitpunkt", "Lagerbestand", dataset, PlotOrientation.VERTICAL, true, true, false);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500,270));
		this.removeAll();
		this.add(chartPanel);
		
//        XYDataset dataset = createDataset(artikelVerlauf, artikelName);  
//		JFreeChart chart= ChartFactory.createXYLineChart(null, "Zeitpunkt", "Lagerbestand", dataset, PlotOrientation.VERTICAL, true, true, false);
//		
//		XYPlot plot = (XYPlot) chart.getPlot();
//		XYSplineRenderer renderer = new XYSplineRenderer();
//		
//        plot.setRenderer(renderer);
//		
//		final ChartPanel chartPanel = new ChartPanel(chart);
//		chartPanel.setPreferredSize(new java.awt.Dimension(500,270));
//		this.removeAll();
//		this.add(chartPanel);

        
    }
}