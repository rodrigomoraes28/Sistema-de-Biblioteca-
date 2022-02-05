package biblioteca.models.emprestimos;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import biblioteca.models.exemplares.Exemplar;
import biblioteca.models.leitores.Estudante;
import biblioteca.models.leitores.Leitor;

public class Emprestimo implements Serializable {
	private Leitor leitor;
	private Exemplar exemplar;
	private Date dataEmprestimo;
	private Date dataDevolucao;
	private static final double VALOR_MULTA = 3.5;

	public Emprestimo(Leitor leitor, Exemplar exemplar, Date dataEmprestimo) {
		this.leitor = leitor;
		this.exemplar = exemplar;
		this.dataEmprestimo = dataEmprestimo;
	}
	
	public Leitor getLeitor() {
		return this.leitor;
	}

	public Exemplar getExemplar() {
		return this.exemplar;
	}

	public Date getDataEmprestimo() {
		return this.dataEmprestimo;
	}
	
	public Date getDataDevolucao() {
		return this.dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
	public String toString() {
		String emprestimo = "";
		emprestimo += "Leitor: " + this.leitor.getNome() + " | Exemplar: " + this.exemplar.getNome();
		emprestimo += " | Emprestimo " + this.formatarData(this.dataEmprestimo);

		if(this.dataDevolucao == null) emprestimo += " | Devolução prevista: " + this.formatarData(this.getDataPrevista());
		else emprestimo += " | Devolucao: " + this.formatarData(dataDevolucao);

		if(this.possuiMulta()) emprestimo += " | Multa: R$ " + new DecimalFormat("###,###,###,##0.00").format(this.calcularMulta());
		return emprestimo;
		
	}
	
	private Date getDataPrevista() {
		int diaEmMilisegundos = 86400;	
		
		int diasParaRetorno = this.ehEstudante() ? 14 : 7;
		
		long dataEmprestimoEmMilisegundos = this.dataEmprestimo.getTime() / 1000;
		
		long diasParaRetornoEmMilisegundos = diaEmMilisegundos * diasParaRetorno;  
		
		return new Date((dataEmprestimoEmMilisegundos + diasParaRetornoEmMilisegundos) * 1000);
	}
	
	private String formatarData(Date data) {
		return new SimpleDateFormat("dd/MM/yyyy").format(data);
	}
	
	private boolean ehEstudante() {
		return this.leitor instanceof Estudante;
	}
	
	private boolean possuiMulta() {
		if(this.getDataDevolucao() == null) return false;
		
		return this.getDataDevolucao().after(this.getDataPrevista());
	}

	private double calcularMulta() {
		long diffInMillies = Math.abs(this.getDataDevolucao().getTime() - this.getDataPrevista().getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
		return diff * VALOR_MULTA;
	}

	

}
