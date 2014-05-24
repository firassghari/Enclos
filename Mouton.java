import java.awt.geom.Ellipse2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

@SuppressWarnings("serial")
public class Mouton extends Ellipse2D.Double implements IDrawable {
	
	public Color color;
	private Color couleurrendu;
	public  boolean selected=false;
	private int rayon;
	private boolean first=true;
	public boolean estvide=false;
	
	public boolean contains(Point p){
		return this.contains(p.x, p.y);
	}
	public void  select()
	{
		selected=true;
		couleurrendu=color.darker(); 
	}
	public void  unSelect()
	{
		selected=false;
		couleurrendu=color;
	}
	public void deplacer(Point p){
		this.x=p.x-rayon;
		this.y=p.y-rayon;
		couleurrendu=color;
	}
	public Point getCentre(){
		return new Point((int)x+rayon,(int)y+rayon);
	}
	public Mouton() {
		super();
		color=Color.BLACK;
		estvide=true;
	}
	public Mouton(Color c, Point p)
	{
		color=c;
		couleurrendu=c;
		rayon=Parametres.rayon;
		this.x=p.x-rayon;
		this.y=p.y-rayon;
		this.height=2*rayon;
		this.width=2*rayon;
		estvide=false;
		
		
	}
	/**
	 * Method draw
	 *
	 *
	 * @param g
	 *
	 */
	public void draw(Graphics g) {
		Color c=g.getColor();
		Graphics2D g2d=(Graphics2D)g;
		//if (this.selected) g2d.draw(this);
		g.setColor(couleurrendu);
		g2d.fill(this);
		System.out.println("m"+couleurrendu.toString());
		g.setColor(c);
		
		if (first){
			(new SoundPlayer(Parametres.fa)).start();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			first=false;
		}
		
		
		
	}	
}
