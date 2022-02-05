package biblioteca.utils.comparators;

import java.util.Comparator;

import biblioteca.models.leitores.Leitor;

public class NomeComparatorLeitor implements Comparator<Leitor> {
	public int compare(Leitor a, Leitor b) {
		String nomeA = a.getNome();
		String nomeB = b.getNome();
		return nomeA.compareTo(nomeB);
	}
}
