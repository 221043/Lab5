package it.polito.tdp.ruzzle.model;

public class Posizione {

	private int riga;
	private int col;
	private String lettera;
	
	public Posizione(int riga, int col) {
		this.riga = riga;
		this.col = col;
		lettera = "";
	}

	public int getRiga() {
		return riga;
	}

	public void setRiga(int riga) {
		this.riga = riga;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	public String getLettera() {
		return lettera;
	}

	public void setLettera(String lettera) {
		this.lettera = lettera;
	}

	@Override
	public String toString() {
		return "Posizione [riga=" + riga + ", col=" + col + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + riga;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posizione other = (Posizione) obj;
		if (col != other.col)
			return false;
		if (riga != other.riga)
			return false;
		return true;
	}
	
}
