package br.dominioL.estruturados.json;

import br.dominioL.estruturados.excecoes.ExcecaoJsonDeAnalise;
import java.math.BigDecimal;

public final class NumeroJson extends ValorJson {
	BigDecimal valor;
	
	protected NumeroJson(String valor) {
		try {
			this.valor = new BigDecimal(valor);
		} catch (NumberFormatException excecao) {
			throw new ExcecaoJsonDeAnalise();
		}
	}
	
	@Override
	public Double fornecerComoNumero() {
		return valor.doubleValue();
	}
	
	@Override
	public String fornecerComoJson() {
		return valor.toString();
	}
}
