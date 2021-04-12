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
	private List<List<Integer>> soluzioni;
	
	public List<List<Integer>> risolvi (int N) {
		this.N=N;
		List<Integer> parziale = new ArrayList<>(); //FACCIO .get QUINDI ANZI LA ARRAYLIST CHE LA LINKEDLIST
		this.soluzioni = new ArrayList<>();
		//qui faro' la chiamata ricorsiva:
		cerca(parziale,0);
		return this.soluzioni;
	}
	
	//cerca=true -> soluz trovata ; cerca=false -> cerca ancora
	private void cerca(List<Integer>parziale, int livello) {
		if(livello==N) {
			// caso terminale
			this.soluzioni.add(new ArrayList<>(parziale));
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
					cerca(parziale,livello+1);
					parziale.remove(parziale.size()-1); //backtracking
				}
			}
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
