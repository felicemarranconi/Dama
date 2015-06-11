package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.*;
public class DamaView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static JFrame frame = new JFrame();

	private final static ImageIcon pedinaBianca = new ImageIcon("src\\view\\bianca.png");
	private final static ImageIcon pedinaNera = new ImageIcon("src\\view\\nera.png");
	private final static ImageIcon damaNera = new ImageIcon("src\\view\\damaNera.png");
	private final static ImageIcon damaBianca = new ImageIcon("src\\view\\damaBianca.png");
	
	private ArrayList<Point> mosseBianco;
	private ArrayList<Point> mosseNero;
	private DamaControl damaControl;
	private JPanel jp;
	private JLabel[][] jlcaselle;
	
	
	private Point pressedPoint,relasedPoint;
	
	public DamaView(){	
		damaControl = new DamaControl();
		jp = new JPanel(new GridLayout(8,8));
		jlcaselle= new JLabel[8][8];
		mosseBianco=null;
		jp.addMouseListener(new Mouse(this, 100, 100));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(jp);
        // Altezza della finestra
        setSize(800,800);
        // Rendo la finestra visibile
	    setVisible(true);
	    // Dichiaro una dimensione fissa
	    this.setResizable(false);     
	}

	
	public void  iniziaPartita(){
		Object[] options = {"Gioca"};
		// nero=1, i questo caso rosso
		JOptionPane.showOptionDialog(frame, "Premi gioca per una nuova partita", "Dama",
		JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
	    null,options, options[0]);
		inizioPartita();
	}
		
	// metodo che crea la grafica della configurazione iniziale della partita
	public void inizioPartita(){
		// 0 giallo 1 rosso
		int i=0;int j = 0;
		for( i = 0; i < 8; i++ ){
			for( j = 0; j < 8; j++ ){		
					jlcaselle[i][j]=new JLabel();
					jlcaselle[i][j].setOpaque(true);
					jlcaselle[i][j].setVerticalAlignment(JLabel.CENTER);
					jlcaselle[i][j].setHorizontalAlignment(JLabel.CENTER);
					jp.add(jlcaselle[i][j]);					
					if(( i + j ) % 2 == 0 ){
						jlcaselle[i][j].setBackground(Color.black);
						 if(( i>=5 && i<=7)  ) {jlcaselle[i][j].setIcon(pedinaBianca);}
						 if(( i>=0 && i<=2)  ) {jlcaselle[i][j].setIcon(pedinaNera);}
					}
					else{jlcaselle[i][j].setBackground(Color.white);}	
			}
		}
	 }	
	
	// Questa funzione aggiorna la grafica a seconda del punto di origine e destinazione della mossa dell'utente
	private void spostaPedina(Point origine, Point destinazione){
				jlcaselle[destinazione.y][destinazione.x].setIcon(jlcaselle[origine.y][origine.x].getIcon());
				jlcaselle[origine.y][origine.x].setIcon(null);
				
				if(destinazione.y==0 &&(destinazione.x%2)==0){
						jlcaselle[destinazione.y][destinazione.x].setIcon(damaBianca);
				}

				if(destinazione.y==7 &&(destinazione.x%2)!=0){	
						jlcaselle[destinazione.y][destinazione.x].setIcon(damaNera);
				}
				
				// controllo se ho mangiato 
				if(Math.abs(origine.x-destinazione.x)>1 ||Math.abs(origine.y-destinazione.y)>1)
					jlcaselle[(origine.y+destinazione.y)/2][(origine.x+destinazione.x)/2].setIcon(null);	
	}
		
	public void finePartita(){
		JOptionPane.showMessageDialog(null, "Partita finita!");
		DamaControl.main(null);
	}
		
	public class Mouse implements MouseListener{
			
			public Mouse(DamaView s,int dimRow,int dimCol){	
				pressedPoint=new Point();
				relasedPoint=new Point();
			}
			@Override
			public void mouseClicked(MouseEvent e) {			
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				Point p;
				int pos=0;
				int pos1=0;
				
					for(int i=0;i< 689;i=i+86){
						if(e.getY()<=i){
							pos=i/86;
							break;
						}
					}
					for(int i=0;i< 798;i=i+99){
						if(e.getX()<=i){
							pos1=i/99;
							break;
						}
					}
					pressedPoint.setLocation((pos1-1),(pos-1));
					try{
						if(jlcaselle[pressedPoint.y][pressedPoint.x].getIcon()==pedinaBianca ||jlcaselle[pressedPoint.y][pressedPoint.x].getIcon()==damaBianca){		
							mosseBianco=damaControl.mosseBianco(pressedPoint);
							// coloro le caselle corrispondenti di arancione
							for(int i=0;i<mosseBianco.size();i++){
								p=mosseBianco.get(i);
								jlcaselle[p.y][p.x].setBackground(Color.orange);
							}
						}
						else if(jlcaselle[pressedPoint.y][pressedPoint.x].getIcon()==pedinaNera||jlcaselle[pressedPoint.y][pressedPoint.x].getIcon()==damaNera){
							mosseNero=damaControl.mosseNero(pressedPoint);
							// coloro le caselle corrispondenti di arancione
							for(int i=0;i<mosseNero.size();i++){
							p=mosseNero.get(i);
							jlcaselle[p.y][p.x].setBackground(Color.orange);
						    }
					    }
					}
					
					catch (Exception exc) {
						  System.out.println ("Spiacente");
					}			
			 }
			@Override
			public void mouseReleased(MouseEvent e) {
				Point p;
				int pos=0;
				int pos1=0;

				for(int i=0;i< 689;i=i+86){
					if(e.getY()<=i){pos=i/86;break;}
				}
				
				for(int i=0;i< 798;i=i+99){
					if(e.getX()<=i){pos1=i/99;break;}
				}
				
				// mi memorizzo dove ho rilasciato la pedina
				relasedPoint.setLocation((pos1-1),(pos-1) );
						
			
				try{
						if(jlcaselle[pressedPoint.y][pressedPoint.x].getIcon()==pedinaBianca||jlcaselle[pressedPoint.y][pressedPoint.x].getIcon()==damaBianca){
							for(int i=0;i<mosseBianco.size();i++){
								p=mosseBianco.get(i);
								jlcaselle[p.y][p.x].setBackground(Color.black);						
								
								if(relasedPoint.x==p.x && relasedPoint.y==p.y){
									damaControl.mossaGiocatoreBianco(pressedPoint, relasedPoint);
									spostaPedina(pressedPoint, relasedPoint);
								}	
							}
						}
					}					
				catch (Exception exc) {System.out.println ("Digita bene l'immagine!");}
					
					
					
				try{	
						if(jlcaselle[pressedPoint.y][pressedPoint.x].getIcon()==pedinaNera||jlcaselle[pressedPoint.y][pressedPoint.x].getIcon()==damaNera){
							for(int i=0;i<mosseNero.size();i++){
								p=mosseNero.get(i);
								jlcaselle[p.y][p.x].setBackground(Color.black);
								
								if(relasedPoint.x==p.x && relasedPoint.y==p.y){
									damaControl.mossaGiocatoreNero(pressedPoint, relasedPoint);
									spostaPedina(pressedPoint, relasedPoint);
								}		
							}
						}
				}
				catch (Exception exc) {System.out.println ("Digita bene l'immagine.");}
				
			}	
   }
		
}
