package it.polito.tdp.regine.model;

import java.util.ArrayList;
import java.util.List;

public class Regine {

	// N è il numero di righe e colonne della scacchiera. (righe e colonne numerate da 0 a N-1)
	// ad ogni livello posizioniamo una regina in una nuova riga
	
	// soluzione parziale: lista delle colonne in cui mettere le regine (prime righe).   List<Integer>
	// livello = quante righe sono già piene
	// livello = 0 => nessuna riga piena (devo mettere la regina nella riga 0)
	// livello = 3 => 3 righe piene (0, 1, 2), devo mettere la regina nella riga 3
	// [0]
	//     [0, 2]
	//            [0, 2, 1]
	
	private int N;
	private List<Integer> soluzione;
	
	public List<Integer> risolvi (int N) {
		this.N=N;
		List<Integer> parziale = new ArrayList<>(); //FACCIO .get QUINDI ANZI LA ARRAYLIST CHE LA LINKEDLIST
		//qui faro' la chiamata ricorsiva:
		cerca(parziale,0);
		return this.soluzione;
	}
	
	//cerca=true -> soluz trovata ; cerca=false -> cerca ancora
	private boolean cerca(List<Integer>parziale, int livello) {
		if(livello==N) {
			// caso terminale
			this.soluzione= new ArrayList<>(parziale); //cosi' restituisco l'ultima soluzione valida trovata (risolvo con boolean anziche void)
			return true; //grazie a questo non stampo piu' l'ultima soluzione, ma restituisco la prima soluzione
		} else {
			for(int colonna=0; colonna<N; colonna++) {
				// if la possa nella casella [livello][colonna] è valida
				// se sì, aggiungi a parziale e fai ricorsione
				if ( posValida(parziale,colonna) ) {
					
					//potrei fare cosi' con newParziale anziche fare backtracking, pero' e' meno efficente (creo sempre una nuova lista)
					/*List<Integer> newParziale = new ArrayList(parziale);
					newParziale.add(colonna);
					cerca(newParziale,livello+1);*/
					
					parziale.add(colonna);
					boolean trovato =cerca(parziale,livello+1);
					if (trovato)
						return true;
					parziale.remove(parziale.size()-1); //backtracking
				}
			}
			return false;
		}
	}

	
	private boolean posValida(List<Integer> parziale, int colonna) {
		int livello= parziale.size();
		// controlla se viene mangiata in verticale
		if (parziale.contains(colonna))
			return false;
		// controlla se viene mangiata in diagonale
		// nelle diagonali di matrice -> riga+colonna=costante ; -> riga-colonna=costante (in base a quale delle due diagonali vedo)
		for (int r=0; r<livello; r++ ) {
			int c = parziale.get(r);
			if (r+c==livello+colonna || r-c==livello-colonna) {
				return false;
			}
		}
		return true;
	}
	
	
	
}
