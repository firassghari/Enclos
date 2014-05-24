import java.awt.Polygon;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.geom.*;

@SuppressWarnings("serial")
public class Hexagone extends Polygon implements IDrawable{
	//private  Color color;
	private int x,y;
	//private int cote,hauteur;
	public boolean isEmpty=true;
	
	public Hexagone() {
		super();
		//color =Color.BLACK; 
		}
	public Hexagone(int a, int b){
		x=a;
		y=b;
		//ox=x+Parametres.d;
		this.npoints=6;
		this.xpoints=new int[] {x+Parametres.d, x+Parametres.cote+Parametres.d, x+Parametres.cote*2, x+Parametres.cote+Parametres.d,x+Parametres.d, x};
		this.ypoints=new int[] {y,y,y+Parametres.h,y+Parametres.h*2,y+Parametres.h*2,y+Parametres.h};
		}
	/*public Hexagone(Color	c,Point p, int demicote){
		color=c;
		x=p.x;y=p.y;
		cote=demicote*2;
		hauteur=(int)(Math.sqrt(3.0)*demicote);
		ox=x+demicote;
		this.npoints=6;
		this.xpoints=new int[] {ox, ox+cote, ox+cote+demicote, ox+cote, ox, x};
		this.ypoints=new int[] {y,y,y+hauteur,y+hauteur*2,y+hauteur*2,y+hauteur};
		}*/
	public Point getCentre(){		
		return new Point(x+Parametres.cote,y+Parametres.h);
		}
	
	public boolean  hasNearestCote(Point p){
		int n=-1;
		double  d=5;
		double di;
		for (int i=0;i<6;i++){
			di=Line2D.Double.ptSegDist(xpoints[i],ypoints[i],xpoints[(i+1)%6],ypoints[(i+1)%6],p.x,p.y);
			if (di<d)	{d=di;n=i;}
		}
		return (n>-1);
	}
	public boolean getNearestCote(Point p,Barre b){
		int n=-1;
		double  d=5;
		double di;
		for (int i=0;i<6;i++){
			di=Line2D.Double.ptSegDist(xpoints[i],ypoints[i],xpoints[(i+1)%6],ypoints[(i+1)%6],p.x,p.y);
			if (di<d)	{d=di;n=i;}
		}
		if (n>-1) 	b.setLine(xpoints[n],ypoints[n],xpoints[(n+1)%6],ypoints[(n+1)%6]);
		return (n>-1);
	}
	public Line2D.Double getNearestCote(Point p){
		if (!hasNearestCote(p)) return null;		
		int n=-1;
		double  d=5;
		double di;
		for (int i=0;i<6;i++){
			di=Line2D.Double.ptSegDist(xpoints[i],ypoints[i],xpoints[(i+1)%6],ypoints[(i+1)%6],p.x,p.y);
			if (di<d)	{d=di;n=i;}
		}
		return new Line2D.Double(xpoints[n],ypoints[n],xpoints[(n+1)%6],ypoints[(n+1)%6]);
	}
	public void draw(Graphics g) {
		Color c=g.getColor();
		g.setColor(Parametres.couleurPolygone);
		g.drawPolygon(this);
		g.setColor(c);
	}	
}
