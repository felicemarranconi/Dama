
//FELIX

package model;

import java.awt.Point;
import java.util.ArrayList;
import control.DamaControl;

public class DamaModel {
	
	private int pezziNeri=12;
	private int pezziBianchi=12;
	private int[][] scacchiera;
	private int turno; 
	
	public DamaModel(){	
		this.scacchiera=new int[8][8];
		turno=1;	
	}
	
	
	// metodo che setta una nuova partita, e riempie la scacchiera i valori sono 1 e 2 altrimenti zero
	public void nuovaPartita(int turno){	
		this.turno=turno;
		// scorre riga per colonna
		for( int i = 0; i < 8; i++){
			for( int j = 0; j < 8; j++ ){
				if(i < 3 ){
					if( (i + j )% 2 == 0 )
						scacchiera[i][j] = 1;
					else
						scacchiera[i][j] = 0;
				}			
				else if( i < 5 ){
					scacchiera[i][j] = 0;
				}
				else{
					if( (i + j )% 2 == 0 )
						scacchiera[i][j] = 2; 	
					else
						scacchiera[i][j] = 0;
				}
			}	
		}				
	}

	public int[][] getScacchiera(){
		return scacchiera;
	}
		
	// metodo che restituisce le mosse possibili 
	public ArrayList<Point> mosseBianco(Point origine){ 
		ArrayList<Point> mosse = new ArrayList<Point>();
		
		//coordinate x,y del punto di origine
		int x=origine.x;
		int y=origine.y;
		if( x < 0 || x > 7 || y < 0 ||y > 7 ||turno == 1|| scacchiera[y][x] == 0 ||
		// controllo il turno
			( scacchiera[y][x] == 1) ||( scacchiera[y][x] == 3))
			return mosse;
					
		
		if (scacchiera[y][x] == 2 ) {			
			// System.out.println(this.toString()+"\n");
			if(mosse.size() == 0){
				if(y > 0 && x > 0 &&((x-1)>=0 && (y-1)>=0) && scacchiera[y-1][x-1]==0){
					mosse.add(new Point(x-1,y-1));						
				}
				if(y > 0 && x  < 7 && ((x+1)<=7 && (y-1)>=0) && scacchiera[y-1][x+1]==0){
					mosse.add(new Point(x+1,y-1));
				}
				// controllo se c'e' da mangiare
				if(y > 0 && x > 0 &&((y-2)>=0 && (x-2)>=0) &&((y-1)>=0 && (x-1)>=0) &&scacchiera[y-1][x-1]==1 && scacchiera[y-2][x-2]==0){
					mosse.add(new Point(x-2,y-2));				
				}
				if(y > 0 && x  < 7 &&((y-1)>=0 && (x+1)<=7)&&((y-2)>=0 && (x+2)<=7) && scacchiera[y-1][x+1]==1 && scacchiera[y-2][x+2]==0){
					mosse.add(new Point(x+2,y-2));			
				}
			}		
		} // caso della dama
		else if(scacchiera[y][x] == 4){

			if(x < 7 && y > 0 && ((y-1)>=0 && (x+1)<=7) && this.scacchiera[y-1][x+1] == 0)
				mosse.add(new Point(x+1, y-1));

			if(x > 0 && y > 0 && ((y-1)>=0 && (x-1)>=0) && this.scacchiera[y-1][x-1] == 0)
				mosse.add(new Point(x-1, y-1));

			if(x < 7 && y < 7 && ((y+1)<=7 && (x+1)<=7) && this.scacchiera[y+1][x+1] == 0)
				mosse.add(new Point(x+1, y+1));

			if(x > 0 && y < 7 && ((y+1)<=7 && (x-1)>=0) && this.scacchiera[y+1][x-1] == 0)
				mosse.add(new Point(x-1, y+1));	
			
			if(x < 7 && y > 0 && (((y-2)>=0 && (x+2)<=7) )&&(this.scacchiera[y-1][x+1] == 1||this.scacchiera[y-1][x+1] == 3)&&this.scacchiera[y-2][x+2] == 0){
				mosse.add(new Point(x+2, y-2));		
			}		
			
			if(x > 0 && y > 0 && ((y-2)>=0 && (x-2)>=0) &&(this.scacchiera[y-1][x-1] == 1||this.scacchiera[y-1][x-1] == 3)&& this.scacchiera[y-2][x-2] == 0){
				mosse.add(new Point(x-2, y-2));		
			}	
			
			if(x < 7 && y < 7&&((y+2)<=7 && (x+2)<=7) && (this.scacchiera[y+1][x+1] == 1||this.scacchiera[y+1][x+1] == 3)&& this.scacchiera[y+2][x+2] == 0){
				mosse.add(new Point(x+2, y+2));		
			}	
			
			if(x > 0 && y < 7 &&(((y+2)<=7 && (x-2)>=0))&& (this.scacchiera[y+1][x-1] == 1||this.scacchiera[y+1][x-1] == 3)&&this.scacchiera[y+2][x-2] == 0){
				mosse.add(new Point(x-2, y+2));
				
			}
		}
		return mosse;
	}
	// metodo che aggiorna la scacchiera 
	public void muoviBianco(Point origine, Point destinazione){
		// coordinate x,y del punto di origine
		int xOrigine=origine.x;
		int yOrigine=origine.y;
		// coordinate x,y del punto destinazione
		int xDestinazione=destinazione.x;
		int yDestinazione=destinazione.y;
		
		if(turno==2){
			
			if((xDestinazione % 2)==0 && yDestinazione==0 && scacchiera[yOrigine][xOrigine] == 2){
				scacchiera[yDestinazione][xDestinazione]=4;
				// System.out.println(this.toString()+"\n");
			}else{
				scacchiera[yDestinazione][xDestinazione]=scacchiera[yOrigine][xOrigine];	
				// System.out.println(this.toString()+"\n");
			}
			scacchiera[yOrigine][xOrigine]=0;

			// verifico se ho mangiato una pedina
			if((Math.abs((xDestinazione-xOrigine))>1 && Math.abs(yDestinazione-yOrigine)>1)){
				scacchiera[(yDestinazione+yOrigine)/2][(xDestinazione+xOrigine)/2]=0;
				// System.out.println(this.toString()+"\n");
				pezziNeri--;
				System.out.println("Il bianco mangia, pezzi neri rimanenti: "+pezziNeri);
			}
			if(pezziNeri==0)
				finePartita();
			turno = 1;
			System.out.println("Scacchiera dopo che il bianco ha mosso\n");
			System.out.println(this.toString()+"\n");
		}
	}
	
	// metodo che aggiorna la scacchiera con la mossa dell'avversario	
	public void muoviNero(Point origine,Point destinazione){
		
		scacchiera[destinazione.y][destinazione.x]=scacchiera[origine.y][origine.x];
		scacchiera[origine.y][origine.x]=0;
		//System.out.println(this.toString()+"\n");
		// La pedina è arrivata alla base avversaria
		if(turno==1){

			if(destinazione.x % 2 != 0 && destinazione.y==7){ 
				scacchiera[destinazione.y][destinazione.x]=3;
				scacchiera[origine.y][origine.x]=0;			
			}
							
			if( (Math.abs(origine.x-destinazione.x)>1 && Math.abs(origine.y-destinazione.y)>1)){
				scacchiera[(origine.y+destinazione.y)/2][(origine.x+destinazione.x)/2]=0;
				// System.out.println(this.toString()+"\n");
				pezziBianchi--;		
				System.out.println("Il nero mangia, pezzi bianchi rimanenti: "+pezziBianchi);
			}
			if(pezziBianchi==0)
				finePartita();
			turno = 2;
		}
		
		System.out.println("Scacchiera dopo che il nero ha mosso\n");
		System.out.println(this.toString()+"\n");
	}
	
	public ArrayList<Point> mosseNero(Point origine){
		ArrayList<Point> mosse = new ArrayList<Point>();		
		// coordinate x,y del punto d'origine
		int x=origine.x;
		int y=origine.y;		
		
		// controllo che gli indici della matrice siano giusti
		if( x < 0 || x > 7 || y < 0 ||y > 7 || turno == 2 || scacchiera[y][x] == 0  ||
				( scacchiera[y][x] == 2) ||( scacchiera[y][x] == 4))
			return mosse;			
	
		if (scacchiera[y][x] == 1 ) {
			// se la pedina e' dell'avversario ok
			if(x<7 && y <7&&((y+1)<=7 &&(x+1)<=7)&& scacchiera[y+1][x+1]==0){
				mosse.add(new Point(x+1,y+1));
			}// ok
			if(x>0 && y<7 &&((y+1)<=7 &&(x-1)>=0)&& scacchiera[y+1][x-1]==0){
				mosse.add(new Point(x-1,y+1));
			}
			// controllo se posso mangiare
			if(x<7 && y <7 &&((y+2)<=7 &&(x+2)<=7)&& scacchiera[y+1][x+1]==2 && scacchiera[y+2][x+2]==0){
				mosse.add(new Point(x+2,y+2));		
			}// ok
			if(x>0 && y<7 &&((y+2)<=7 &&(x-2)>=0) && scacchiera[y+1][x-1]==2 && scacchiera[y+2][x-2]==0){
				mosse.add(new Point(x-2,y+2));	
			}
		}
		else if(scacchiera[y][x]==3){ 
		
			if(x < 7 && y > 0 && this.scacchiera[y-1][x+1] == 0)
				mosse.add(new Point(x+1, y-1));
		
			if(x > 0 && y > 0 && this.scacchiera[y-1][x-1] == 0)
				mosse.add(new Point(x-1, y-1));
		
			if(x < 7 && y < 7 &&((y+1)<=7 &&(x+1)<=7)&& this.scacchiera[y+1][x+1] == 0)
				mosse.add(new Point(x+1, y+1));
	
			if(x > 0 && y < 7 &&((y+1)<=7 &&(x-1)>=0)&& this.scacchiera[y+1][x-1] == 0)
				mosse.add(new Point(x-1, y+1));	
		
			if(x < 7 && y > 0 &&(((y-2)>=0 &&(x+2)<=7))&& (this.scacchiera[y-1][x+1] == 2||this.scacchiera[y-1][x+1] == 4) && this.scacchiera[y-2][x+2] == 0){
				mosse.add(new Point(x+2, y-2));
			}
		
			if(x > 0 && y > 0 &&((y-2)>=0 &&(x-2)>=0) && (this.scacchiera[y-1][x-1] == 2||this.scacchiera[y-1][x-1] == 4)&& this.scacchiera[y-2][x-2] == 0){
				mosse.add(new Point(x-2, y-2));	
			}
		
			if(x < 7 && y < 7 &&((y+2)<=7 &&(x+2)<=7)&& (this.scacchiera[y+1][x+1] == 2|| this.scacchiera[y+1][x+1] == 4)&& this.scacchiera[y+2][x+2] == 0){
				mosse.add(new Point(x+2, y+2));	
			}
			
			if(x > 0 && y < 7&&((((y+1)<=7 &&(x-2)>=0))&&(((y+1)>=0 &&(x-2)>=0))) &&(this.scacchiera[y+1][x-1] == 2||this.scacchiera[y+1][x-1] == 4)&& this.scacchiera[y+2][x-2] == 0){
				mosse.add(new Point(x-2, y+2));	
			}
		}
		
		return mosse;
	}
	// restituisce le posizioni della scacchiera
	public String toString(){
		String out = "";
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++)
				out +=" "+this.scacchiera[i][j];
			out+="\n";
		}
		return out;
	}
	
	public void finePartita(){
		DamaControl.finePartita(true);
	}
}
