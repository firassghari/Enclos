import java.util.Iterator;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Panel;
import java.awt.geom.Line2D;
import java.io.File;



@SuppressWarnings("serial")
public class AireDuJeu extends Panel {

	private LinkedList<Hexagone> hexagones = new LinkedList<Hexagone>();
	private LinkedList<IDrawable> barres=new LinkedList<IDrawable>();
	private LinkedList<Joueur> joueurs=new LinkedList<Joueur>();
	public Mouton selectedMouton;
	//private Color couleurPolygone; 
	private static final   Mouton moutonvide=new Mouton();
	private Joueur joueur1;
	private Joueur joueur2;
	private Joueur joueurActuel;
	private Joueur joueurGagnant;
	//private File fn,fa;
	public boolean partieEnCours=false;
	public boolean partieTerminee=false;
	
    private void setJoueurActuel(Joueur joueur){
    	joueurActuel=joueur;
    	joueur.joueurActuel=true;	
    }
	public AireDuJeu()
	{
		super();
		
		System.out.println(Parametres.repCourant);
		//fn=new File("tone_8.wav");
		//fa=new File("c:\\tone_11.wav");
		setBackground(Parametres.couleurAirDuJeuJoueur1);
		//couleurPolygone=Color.BLUE;
		
        int [] px ={3,2,4,1,3,5,0,2,4,6,1,3,5,0,2,4,6,1,3,5,0,2,4,6,1,3,5,0,2,4,6,1,3,5,2,4,3};
     	int [] py = {0,1,1,2,2,2,3,3,3,3,4,4,4,5,5,5,5,6,6,6,7,7,7,7,8,8,8,9,9,9,9,10,10,10,11,11,12};
     	for (int i=0; i<37; i++)
     	{
     		this.addDrawable(new Hexagone(px[i]*Parametres.delta, py[i]*Parametres.h));
     		//this.addDrawable(new Hexagone(couleurPolygone , new Point(px[i]*Parametres.delta, py[i]*Parametres.h), Parametres.d));
     	}
     	
     	 
     	 }
	public void nouvellePartie(){
	if (partieEnCours) joueurs.clear();
		getPolygone(new Point(8*Parametres.d,6*Parametres.h)).isEmpty=false;
    	getPolygone(new Point(14*Parametres.d,6*Parametres.h)).isEmpty=false;
    	getPolygone(new Point(11*Parametres.d,9*Parametres.h)).isEmpty=false;
    	getPolygone(new Point(11*Parametres.d,5*Parametres.h)).isEmpty=false;
    	getPolygone(new Point(8*Parametres.d,8*Parametres.h)).isEmpty=false;
    	getPolygone(new Point(14*Parametres.d,8*Parametres.h)).isEmpty=false;
    	selectedMouton=moutonvide;
    	joueur1=new Joueur(1,new Point(700,300),Parametres.couleurMoutonJoueur1);
    	joueur2=new Joueur(2,new Point(700,400),Parametres.couleurMoutonJoueur2);
    	joueurs.add(joueur1);
    	joueurs.add(joueur2);
    	setJoueurActuel(joueur1);
    	partieEnCours=true;
    	this.repaint();
		
	}
	public void paint(Graphics g) {
		
	
		for (Iterator<Hexagone> iter = hexagones.iterator(); iter.hasNext();) {
			IDrawable d =  iter.next();
			d.draw(g);	
		}
		for (Iterator<IDrawable> iter = barres.iterator(); iter.hasNext();) {
			IDrawable d =  iter.next();
			d.draw(g);	
		}
		for (Iterator<Joueur> iter = joueurs.iterator(); iter.hasNext();) {
			IDrawable d = iter.next();
			d.draw(g);	
		}
	}
	private boolean isBarreAdded(Barre b){
		for (Iterator<IDrawable> iter = barres.iterator(); iter.hasNext();) {
			Barre d = (Barre) iter.next();
			System.out.println (b.x1+" "+d.x1+" "+b.x2+" "+d.x2+" "+b.y1+" "+d.y1+" "+b.y2+" "+d.y2+"isbarreadded");
			if (b.isEgal(d) ){System.out.println("barre trouve");return true;} 
		}
		System.out.println("barre non trouve");
		return false;
		
	}
	private  boolean canDrawBarre(Point p,Barre b){
		if (joueurActuel.aPlaceBarre) {return false;}
		for (Iterator<Hexagone> iter = hexagones.iterator(); iter.hasNext();) {
			Hexagone hex = (Hexagone) iter.next();
			/*if (hex.hasNearestCote(p) ){
				b.setLine(hex.getNearestCote(p));
				if (!isBarreAdded(b)) return true;
			}*/
			if (hex.getNearestCote(p, b) && !isBarreAdded(b) ) return true;
			//return true;
		}
		//new soundPlayer(fn).start();
		System.out.println("cant draw barre");
		return false;
			 	
	}
	private  void  changerJoueur(){
		if (verfierFinDePartie()) {
			javax.swing.JOptionPane.showMessageDialog(null,joueurGagnant.ToString()+ "  a gagne"); 
		
			System.out.println ("joueur"+joueurGagnant.numero+" a gagne");
			partieTerminee=true;
		}
		if (joueurActuel==joueur1){
			setJoueurActuel(joueur2);
			joueur1.clear();
			this.setBackground(Parametres.couleurAirDuJeuJoueur2);
			} else
				{ 
			 	setJoueurActuel(joueur1);
			 	joueur2.clear();
			 	this.setBackground(Parametres.couleurAirDuJeuJoueur1);
				}
	}
	public boolean  verfierFinDePartie(){
		boolean fin=true;
		for (Iterator<Mouton> iterm = joueur1.moutons.iterator(); iterm.hasNext();) {
			Mouton m = iterm.next();
			for (Iterator<Hexagone> iter = hexagones.iterator(); iter.hasNext();) {
				Hexagone hex = iter.next();
				if (canDeplacerMouton(m,hex.getCentre())) {
					fin=false;
					joueurGagnant=joueur1;
					}
				}
		}
		if (fin) return fin;
		for (Iterator<Mouton> iterm = joueur2.moutons.iterator(); iterm.hasNext();) {
			Mouton m = iterm.next();
			for (Iterator<Hexagone> iter = hexagones.iterator(); iter.hasNext();) {
				Hexagone d = iter.next();
				if (canDeplacerMouton(m,d.getCentre())) {
					fin=false;
					joueurGagnant=joueur2;
				}
			
			}
			
		}
		
		return fin;
	}
	private  boolean verifierTour(){
		if (joueurActuel.aDeplaceMouton && joueurActuel.aPlaceBarre){
			changerJoueur();
			}
			return false;
	}
	public boolean  drawBarre(Point p){
		System.out.println(barres.size());	
		Barre nb=new Barre(Color.RED,new Line2D.Double());
		if (canDrawBarre(p,nb)) {
			barres.add(nb);
			this.repaint();
			joueurActuel.aPlaceBarre=true;
			verifierTour();
			new SoundPlayer(Parametres.fa).start();
			return true;
			} else return false;
	}
	public void unselectMouton(){
		selectedMouton.unSelect();
		selectedMouton=moutonvide;
		this.repaint();
	}
	private void select(Mouton m){
		m.select();
		selectedMouton=m;
		}
	public void  selectMouton(Point p)
	{
		
		if (joueurActuel.aDeplaceMouton ||!selectedMouton.estvide ) return ;
		//selectedMouton=moutonvide;
		if (joueurActuel.numero==1){
			for (Iterator<Mouton> iter = joueur1.moutons.iterator(); iter.hasNext();) {
				Mouton m =  iter.next();
				if (m.contains(p)) select(m);
				}
		} else {
			for (Iterator<Mouton> iter = joueur2.moutons.iterator(); iter.hasNext();) {
				Mouton m =  iter.next();
				if (m.contains(p)) select(m);
				}
			}
		this.repaint();
		
	}
	/*private  boolean getPolygone(Point p,Hexagone hexagone){
		for (Iterator<Hexagone> iter = hexagones.iterator(); iter.hasNext();) {
				Hexagone hex = iter.next();
				if (hex.contains(p)) {hexagone=hex;return true;} 
				}
		return false;
		}*/
	private  Hexagone getPolygone(Point p){
	for (Iterator<Hexagone> iter = hexagones.iterator(); iter.hasNext();) {
			Hexagone hex = iter.next();
			if (hex.contains(p)) {return hex;} 
			}
	return new Hexagone();
	}
	private  boolean canDeplacerMouton(Mouton m,Point p){
		Hexagone hex=getPolygone(p);
		if ((!hex.isEmpty) || hex.getBounds().isEmpty() ) return false;
		if (hex.getCentre().distance(m.getCentre())>3*Parametres.h) return false;
		boolean candeplacer=true;
		Line2D.Double ln=new Line2D.Double(p,m.getCentre());
		for (Iterator<IDrawable> iter = barres.iterator(); iter.hasNext();) {
			Barre b = (Barre) iter.next();
			if (b.intersectsLine(ln) ) candeplacer=false;
			}
		return candeplacer;
		}
	private  boolean canDeplacerSelectedMouton(Point p){
		if (joueurActuel.aDeplaceMouton) return false;
		Hexagone hex=getPolygone(p);
		if ((!hex.isEmpty) || hex.getBounds().isEmpty() || selectedMouton.isEmpty()) return false;
		if (hex.getCentre().distance(selectedMouton.getCentre())>3*Parametres.h) return false;
		boolean candeplacer=true;
		Line2D.Double ln=new Line2D.Double(p,selectedMouton.getCentre());
		for (Iterator<IDrawable> iter = barres.iterator(); iter.hasNext();) {
			Barre b = (Barre) iter.next();
			if (b.intersectsLine(ln) ) candeplacer=false;
			}
		return candeplacer;
		}
	public boolean  deplacerSelectedMouton(Point p){
		
		if (canDeplacerSelectedMouton(p) && !joueurActuel.aDeplaceMouton){
			Hexagone hexDestination=getPolygone(p);
			Hexagone hexSource=getPolygone(selectedMouton.getCentre());
			hexSource.isEmpty=true;
			selectedMouton.deplacer(hexDestination.getCentre());
			hexDestination.isEmpty=false;
			selectedMouton=moutonvide;
			this.repaint();
			joueurActuel.aDeplaceMouton=true;
			verifierTour();
			new SoundPlayer(Parametres.fa).start();
			return true;
		} else { new SoundPlayer(Parametres.fn).start(); return false;}
	}
	private  void addDrawable(IDrawable d) {
		if (d.getClass().getName()=="Hexagone") hexagones.add((Hexagone)d);
		if ((d.getClass().getName()=="Barre")&&!((Barre)d).isEmpty()) barres.add(d);
		this.repaint();
	}
	public void removeDrawable(IDrawable d) {
		hexagones.remove(d);
		repaint();
	}
	public void clear() {
		//drawables.clear();
		if (partieEnCours){
			joueur1.reinitialiser();
			joueur2.reinitialiser();
			barres.clear();
			joueurActuel=joueur1;
			joueur1.joueurActuel=true;
			joueurGagnant=null;
			for (Iterator<Hexagone> iter = hexagones.iterator(); iter.hasNext();) {
				Hexagone hex = iter.next();
				hex.isEmpty=true;
				}
			getPolygone(new Point(8*Parametres.d,6*Parametres.h)).isEmpty=false;
			getPolygone(new Point(14*Parametres.d,6*Parametres.h)).isEmpty=false;
			getPolygone(new Point(11*Parametres.d,9*Parametres.h)).isEmpty=false;
			getPolygone(new Point(11*Parametres.d,5*Parametres.h)).isEmpty=false;
			getPolygone(new Point(8*Parametres.d,8*Parametres.h)).isEmpty=false;
			getPolygone(new Point(14*Parametres.d,8*Parametres.h)).isEmpty=false;
			selectedMouton=moutonvide;
			partieTerminee=false;
     	 
			this.repaint();
			}
	}
	
}
