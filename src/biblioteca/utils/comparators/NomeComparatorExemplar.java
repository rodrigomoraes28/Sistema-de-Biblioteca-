package biblioteca.utils.comparators;

import java.util.Comparator;

import biblioteca.models.exemplares.Exemplar;

public class NomeComparatorExemplar implements Comparator<Exemplar> {
	public int compare(Exemplar a, Exemplar b) {
		String nomeA = a.getNome();
		String nomeB = b.getNome();
		return nomeA.compareTo(nomeB);
	}
}
