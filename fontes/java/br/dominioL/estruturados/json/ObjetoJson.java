package br.dominioL.estruturados.json;

import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.mapa.Mapa;
import br.dominioL.estruturados.mapa.MapaLista;
import br.dominioL.estruturados.mapa.Par;
import br.dominioL.estruturados.iteracao.Iterador;

public final class ObjetoJson extends ValorJson {
	private Mapa<IdentificadorJson, ValorJson> elementos;

	protected ObjetoJson() {
		elementos = MapaLista.criar();
	}

	public void inserir(String identificador, ValorJson valor) {
		elementos.inserir(Json.criarIdentificador(identificador), valor);
	}

	public ValorJson fornecer(String identificador) {
		return elementos.fornecer(Json.criarIdentificador(identificador));
	}

	public void inserir(IdentificadorJson identificador, ValorJson valor) {
		elementos.inserir(identificador, valor);
	}

	public ValorJson fornecer(IdentificadorJson identificador) {
		return elementos.fornecer(identificador);
	}

	@Override
	public ObjetoJson comoObjeto() {
		return this;
	}

	@Override
	public String comoTextoJson() {
		StringBuilder textoJson = new StringBuilder();
		textoJson.append(ABERTURA_DE_OBJETO);
		Iterador<Par<IdentificadorJson, ValorJson>> iterador = elementos.fornecerIterador();
		while (iterador.possuiProximo()) {
			Par<IdentificadorJson, ValorJson> elemento = iterador.iterarProximo();
			textoJson.append(elemento.fornecerChave().comoTextoJson());
			textoJson.append(elemento.fornecerValor().comoTextoJson());
			if (iterador.possuiProximo()) {
				textoJson.append(SEPARADOR);
			}
		}
		textoJson.append(FECHAMENTO_DE_OBJETO);
		return textoJson.toString();
	}
}
