/**
 * @(#)testjfcFrame.java
 *
 * JFC testjfc application
 *
 * @author 
 * @version 1.00 2014/4/19
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


public class Enclave extends JFrame {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private static final long serialVersionUID = 146785574365416676L;
	private AireDuJeu aireDuJeu=new AireDuJeu();
    /**
     * The constructor
     */
		public Enclave() {
		JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu();
        JMenuItem menuNouvellePartie = new JMenuItem();
        JMenuItem menuFileExit = new JMenuItem();
        
        menuFile.setText("File");
        menuNouvellePartie.setText("Nouvelle Partie");
        menuNouvellePartie.setAccelerator((KeyStroke) KeyStroke.getKeyStroke(KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuFileExit.setAccelerator((KeyStroke) KeyStroke.getKeyStroke(KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuFileExit.setText("Exit");
        
        // Add action listener.for the menu button
        menuFileExit.addActionListener
        (
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Enclave.this.windowClosed();
                }
            }
        );
        menuNouvellePartie.addActionListener
        (
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    aireDuJeu.clear();
                    aireDuJeu.nouvellePartie();
                }
            }
        );
        menuFile.add(menuNouvellePartie);
        menuFile.add(menuFileExit);
        menuBar.add(menuFile);
        
        setTitle("JEU ENCLAVE");
        setJMenuBar(menuBar);
       
	
    //Instanciation du plateau de je  
	//aireJeu pj=new aireJeu();
        
     	 
     		
	
	new AirDuJeuMouseListener(aireDuJeu);
    this.add(aireDuJeu);
    setSize(new Dimension(1100, 700));
    this.setMinimumSize(new Dimension(1100,700));
        
        // Add window listener.
        this.addWindowListener
        (
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    Enclave.this.windowClosed();
                }
            }
        );  
    }
    
    
    /**
     * Shutdown procedure when run as an application.
     */
    protected void windowClosed() {
    	
    	// TODO: Check if it is safe to close the application
    	
        // Exit application.
        System.exit(0);
    }
}