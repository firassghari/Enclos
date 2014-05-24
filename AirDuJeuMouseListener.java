import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
public class AirDuJeuMouseListener extends MouseAdapter {

	protected AireDuJeu airDuJeu;

	public AirDuJeuMouseListener(AireDuJeu canvas) {
		super();
		this.airDuJeu = canvas;
		canvas.addMouseListener(this);
	}
		
	public AireDuJeu getAirDuJeu() {
		return airDuJeu;
	}

	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			leftClickAction(e);
		} else {
			rightClickAction(e);
		}
	}

	
	protected void rightClickAction(MouseEvent e) {
		
		
	}

	protected void leftClickAction(MouseEvent e) {
		/*on gere les clics uniquement quand une partie est en cours*/
		if (this.airDuJeu.partieEnCours && !this.airDuJeu.partieTerminee){
		/*si un mouton est selectionn�*/
		if (!airDuJeu.selectedMouton.estvide){
			/*si le joueur clique sur le mouton selectionn� on le deselectionne sinon on deplace le mouton selectionn� vers l'hexagone 
			 * sur lequel on a cliqu�*/
			if (airDuJeu.selectedMouton.contains(e.getX(),e.getY())) airDuJeu.unselectMouton(); else airDuJeu.deplacerSelectedMouton(e.getPoint());
		/*si aucun mouton n'est selectionn�	: si je n'arrive pas a poser une barre je tente de selectionner le cas echeant le mouton 
		 * sur lequel on a cliqu�. drawBarre(Point p) renvoie true quand elle arrive a poser une barre. selectMouton(Point p) s'assure 
		 * qu'on n'a pas deja deplace un mouton pendant ce tour */
			
		} else {
					if (!airDuJeu.drawBarre(e.getPoint())) airDuJeu.selectMouton(e.getPoint());
				}
		}
					
		
	}
	
	
	
}


