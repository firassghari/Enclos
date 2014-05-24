import java.awt.Color;
import java.io.File;


public final class Parametres {
	public static int 
			cote=52, 
			h=(int)(Math.sqrt(3.0)*cote/2),
			d=cote/2,
			delta=(int)(3*cote/2),
			rayon=d;
	public static Color 
			couleurPolygone=Color.BLUE,
			couleurMoutonJoueur1=Color.YELLOW,
			couleurMoutonJoueur2=Color.MAGENTA,
			couleurAirDuJeuJoueur1=Color.CYAN,
			couleurAirDuJeuJoueur2=Color.GREEN;
	public static String repCourant = (new java.io.File("")).getAbsolutePath();
	public static File 
	fn=new File(repCourant+"\\son\\"+"tone_8.wav"),
	fa=new File(repCourant+"\\son\\"+"tone_11.wav");
	
	

}
