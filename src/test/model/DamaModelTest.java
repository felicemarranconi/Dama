package test.model;

import static org.junit.Assert.*;
import java.awt.Point;
import java.util.ArrayList;
import model.DamaModel;
import org.junit.Test;

// classe utilizzata per testare i vari metodi  della classe DamaModel
public class DamaModelTest {

	
	@Test
	public void mosseBiancoTest(){
		DamaModel damaModel = new DamaModel();
		damaModel.nuovaPartita(1);
		ArrayList<Point> mosse =null;
		int[][] schac=damaModel.getScacchiera();
		// punto di origine
		// riga 0 colonna 2
		mosse = damaModel.mosseNero(new Point(0, 2));
		// assert true asserisco se il metodo è scritto bene nel punto 1-3 c'e' una pedina
		assertTrue( mosse !=null && mosse.size() == 1 && (mosse.get(0).equals( new Point(1,3))));
		// partendo dal punto 4-2 il risultato del metodo mosse nero mi ritorna 3-3
		mosse = damaModel.mosseNero(new Point(4, 2));
		assertTrue( mosse !=null && mosse.size() == 2 && (mosse.get(0).equals( new Point(3,3)) || mosse.get(0).equals( new Point(5,3))));
		
		damaModel.nuovaPartita(1);
		// setto la scacchiera nella posizione 3-3 ho la pedina bianca 
		// Il metodo sopra mi restituisce le posizioni dove posso muovermi
		schac[3][3]=3;
		schac[2][2]=0;
		schac[4][2]=0;
		schac[2][4]=0;
		schac[4][4]=0;
		mosse=damaModel.mosseNero(new Point(3,3));
		assertTrue(mosse != null && mosse.size()>0 && (mosse.get(0).equals(new Point(2,2))||
				(mosse.get(0).equals(new Point(2,4)))|| (mosse.get(0).equals(new Point(4,2)))
				|| (mosse.get(0).equals(new Point(4,4)))));
	}
	
}
