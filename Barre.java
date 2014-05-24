import java.awt.Graphics;
import java.awt.geom.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Barre extends Line2D.Double implements IDrawable {
	
	private Color color;
	public Barre(Color c,Line2D.Double l) {
	super(l.getP1(),l.getP2());
	color = c;
	}
	public boolean isEgal(Barre b){
		if (  (getP1().equals(b.getP1()) && getP2().equals(b.getP2())) || (getP1().equals(b.getP2()) && getP2().equals(b.getP1()))     ) return true; else return false;
	}
	public void draw(Graphics g) {
		Graphics2D g2d=(Graphics2D)g;
		Color c=g.getColor();
		Stroke bs=g2d.getStroke();
		Stroke bs3=new BasicStroke(4);
		g2d.setStroke(bs3);		
		g2d.setColor(color);
		g2d.draw(this);
		g2d.setColor(c);
		g2d.setStroke(bs);
	}
	public boolean isEmpty()
	{
		if (this.getBounds().getWidth()==0 && this.getBounds().getHeight()==0) return true; else return false;
		
	}	
}
