import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;


public class Joueur  extends Mouton{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2914780726665019960L;
	/**
	 * 
	 */
	int numero;
	Color backGroundColor;
	public  boolean aPlaceBarre=false;
	public  boolean aDeplaceMouton=false;
	public Color cs=Color.LIGHT_GRAY;
	public boolean joueurActuel=false;
	public LinkedList<Mouton> moutons=new LinkedList<Mouton>();
	private void addMoutons(int id){
		if (id==1){
			moutons.add(new Mouton(color,new Point(8*Parametres.d,6*Parametres.h)));
			moutons.add(new Mouton(color,new Point(14*Parametres.d,6*Parametres.h)));
			moutons.add(new Mouton(color,new Point(11*Parametres.d,9*Parametres.h)));
	     	 } else {
	     	moutons.add(new Mouton(color,new Point(11*Parametres.d,5*Parametres.h)));
	     	moutons.add(new Mouton(color,new Point(8*Parametres.d,8*Parametres.h)));
	     	moutons.add(new Mouton(color,new Point(14*Parametres.d,8*Parametres.h)));
	     	 }
	}
	
	public Joueur(int n,Point p,Color c){
		super(c,p); 
		numero=n;
		this.addMoutons(numero);
		
	}
	public void reinitialiser(){
		aPlaceBarre=false;
		aDeplaceMouton=false;
		joueurActuel=false;
		moutons.clear();
		addMoutons(numero);
		
	}
	public void clear(){
		aPlaceBarre=false;
		aDeplaceMouton=false;
		joueurActuel=false;
	}
	public void draw(Graphics g){
		super.draw(g);
		for (Iterator<Mouton> iter = moutons.iterator(); iter.hasNext();) {
			IDrawable d = iter.next();
			d.draw(g);
		}
		
		if (this.joueurActuel) cs=Color.RED; else cs=Color.LIGHT_GRAY;
		Color cp=g.getColor();
		Font ft=g.getFont();
		g.setColor(cs);
		g.setFont(new Font(null, Font.ITALIC, 18));
		g.drawString(this.ToString(),750,(180+100*numero));
		if (joueurActuel){
			g.fillOval((int)x+15, (int)y+15,(int) this.width-30,(int) this.height-30);
			g.drawString("a placé une barre      : "+ this.aPlaceBarre ,750,(200+100*numero));
			g.drawString("a déplacé un mouton : "+ this.aDeplaceMouton ,750,(220+100*numero));
			} else {
				g.drawString("a placé une barre      : ---" ,750,(200+100*numero));
				g.drawString("a déplacé un mouton : ---",750,(220+100*numero));	
				}
		g.setColor(cp);
		g.setFont(ft);
		
	}
	public String ToString(){
		String s="Joueur"+String.valueOf(numero);
		if (joueurActuel) s+=" ( Joueur Actuel )";
	return s;
	}
}
