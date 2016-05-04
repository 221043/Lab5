package it.polito.tdp.ruzzle.model;

import java.util.Comparator;

public class ComparatorePerLunghezza implements Comparator<String> {

	@Override
	public int compare(String a, String b) {
		if(a.length()==b.length())
			return a.compareTo(b);
		return a.length()-b.length();
	}

}

