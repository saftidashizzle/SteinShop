package shop.local.ui.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;

import javax.swing.JPanel;

import shop.local.valueobjects.Ereignis;

public class ArtikelProtokollPanel extends JPanel {
	int[] data= {
	        21, 14, 18, 03, 86, 88, 74, 87, 54, 77,
	        61, 55, 48, 60, 49, 36, 38, 27, 20, 18,
	    };
//	int [] data;
	// hier kommen jeweils die artikelmenge an tag 1-30 rein
    final int PAD = 30;
	public ArtikelProtokollPanel(List<Ereignis> artikelVerlauf) {
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
		// solange ich mich in tag eins befinde: addiere/subtrahiere menge auf tagesmenge
		
		// lade liste von ereignissen betreffend artikel a
		// schau welche ereignisse alle zu einem tag gehoeren
		// bilde die summe über deren artikelmenge
		// trage die summe jeweils in data[i] ein
		// wenn danach kein ereignis mehr kommt lass die menge gleich
		
		
		
		
//		JLabel text = new JLabel("Hallo ich bin die gewünschte Grafik!");
//		this.add(text);
	} 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        // Draw ordinate.
        g2.draw(new Line2D.Double(PAD, PAD, PAD, h-PAD));
        // Draw abcissa.
        g2.draw(new Line2D.Double(PAD, h-PAD, w-PAD, h-PAD));
        double xInc = (double)(w - 2*PAD)/(data.length-1);
        double scale = (double)(h - 2*PAD)/getMax();
        // Mark data points.
        g2.setPaint(Color.red);
        for(int i = 0; i < data.length; i++) {
            double x = PAD + i*xInc;
            double y = h - PAD - scale*data[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
    } 
    private int getMax() {
        int max = -Integer.MAX_VALUE;
        for(int i = 0; i < data.length; i++) {
            if(data[i] > max)
                max = data[i];
        }
		        return max;
    }	    	
}
