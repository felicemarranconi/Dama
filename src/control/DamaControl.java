package control;

import java.awt.Point;
import java.util.ArrayList;
import model.DamaModel;
import view.DamaView;

public class DamaControl {
		
	// dichiariamo le classi con le quali comunica il control
	private static DamaModel damaModel;	
	private static DamaView damaView;		

	public static void main(String args[]){
		// inizio partita Model
		damaModel = new DamaModel();
		// 2 è il bianco
		damaModel.nuovaPartita(2);		
		// inizio partita View
		damaView = new DamaView();
		damaView.iniziaPartita();	
	}
		
	// invio al model il punto iniziale da cui calcolare tutte le mosse possibili
	public ArrayList<Point> mosseBianco(Point origine){
		return damaModel.mosseBianco(origine);
	}
	
	// invio al model il punto iniziale da cui calcolare tutte le mosse possibili
	public ArrayList<Point> mosseNero(Point origine){
		return damaModel.mosseNero(origine);
	}
	
	public void mossaGiocatoreBianco(Point origine, Point destinazione){
		damaModel.muoviBianco(origine, destinazione);
	}
	public void mossaGiocatoreNero(Point origine,Point destinazione){
		damaModel.muoviNero(origine, destinazione);
	}
	
	public static void finePartita(boolean cond){
		if(cond==true)
			damaView.finePartita();
	}		
}


