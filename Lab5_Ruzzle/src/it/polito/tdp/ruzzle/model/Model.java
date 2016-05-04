package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import it.polito.tdp.ruzzle.db.DizionarioDAO;

public class Model {
	
	private final int N;
	private List<Posizione> alfabeto;
	private Map<String, List<Posizione>> parole;
	private DizionarioDAO dao = new DizionarioDAO();
	
	List<String> taboo = new LinkedList<String>();
	
	public Model(){
		N = 4;
		alfabeto = new ArrayList<>();
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				alfabeto.add(new Posizione(i, j));
			}
		}
		parole = new HashMap<String, List<Posizione>>();
	}
	
	
	public int getDimensione(){
		return N;
	}
	 
	public Posizione getPosizione(int riga, int colonna){
		for(Posizione p:alfabeto){
			if(p.getCol()==colonna && p.getRiga()==riga)
				return p;
		}
		return null;
	}
	
	public String generaLettera(int riga, int colonna){
		Random r = new Random();
		String lettera = ""+(char)(r.nextInt(26) + 'a');
		this.getPosizione(riga, colonna).setLettera(lettera);
		return lettera;
	}
	
	public List<Posizione> getPosizioniDaColorare(String parola){
		return parole.get(parola);
	}
	
	public List<String> solution(){
		for(Posizione p:alfabeto){
			run("", p, new ArrayList<Posizione>());
		}
		List<String> lista = new LinkedList<String>();
		for(String s:parole.keySet()){
			for(String t:taboo){
				if(s.contains(t)){
					taboo.add(s);
					break;
				}
			}
			if(!taboo.contains(s)){
				if(dao.check(s))
					lista.add(s);
				else
					taboo.add(s);
			}		
		}
		Collections.sort(lista, new ComparatorePerLunghezza());
		return lista;
	}
	
	public void rigaSopra(int riga, int col, List<Posizione> usate, String s){
		if(!usate.contains(this.getPosizione(riga-1, col))){
			run(s, this.getPosizione(riga-1, col), usate);
		}
		if(col<N-1){
			if(!usate.contains(this.getPosizione(riga-1, col+1))){
				run(s, this.getPosizione(riga-1, col+1), usate);
			}
		}
		if(col>0){
			if(!usate.contains(this.getPosizione(riga-1, col-1))){
				run(s, this.getPosizione(riga-1, col-1), usate);
			}
		}
	}
	
	public void rigaSotto(int riga, int col, List<Posizione> usate, String s){
		if(!usate.contains(this.getPosizione(riga+1, col))){
			run(s, this.getPosizione(riga+1, col), usate);
		}
		if(col<N-1){
			if(!usate.contains(this.getPosizione(riga+1, col+1))){
				run(s, this.getPosizione(riga+1, col+1), usate);
			}
		}
		if(col>0){
			if(!usate.contains(this.getPosizione(riga+1, col-1))){
				run(s, this.getPosizione(riga+1, col-1), usate);
			}
		}
	}
	
	public void laterale(int riga, int col, List<Posizione> usate, String s){
		if(col>0){
			if(!usate.contains(this.getPosizione(riga, col-1))){
				run(s, this.getPosizione(riga, col-1), usate);
			}
		}
		if(col<N-1){
			if(!usate.contains(this.getPosizione(riga, col+1))){
				run(s, this.getPosizione(riga, col+1), usate);
			}
		}
	}
	
	public void run(String s, Posizione p, List<Posizione> usate){
		if(s.length()>1){
			List<Posizione> temp = new ArrayList<Posizione>(usate);
			parole.put(s, temp);
		}
		int riga = p.getRiga();
		int col = p.getCol();
		s += p.getLettera();
		usate.add(p);
		if(s.length()==N*N)
			parole.put(s, usate);
		if(riga>0){
			this.rigaSopra(riga, col, usate, s);
		}
		if(riga<N-1){
			this.rigaSotto(riga, col, usate, s);
		}
		if(col>0 || col<N-1){
			this.laterale(riga, col, usate, s);
		}
		else
			return;
	}
	
//	public static void main(String[] args){
//		Model m = new Model();	
//		System.out.println(m.solution());
//	}

}
