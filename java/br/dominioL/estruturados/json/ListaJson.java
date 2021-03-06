package br.dominioL.estruturados.json;

import java.util.Iterator;

import br.dominioL.estruturados.colecao.lista.ListaPosicional;
import br.dominioL.estruturados.elemento.extra.ConstrutorDeTexto;
import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.iteracao.Contador;
import br.dominioL.estruturados.iteracao.Iterador;
import br.dominioL.estruturados.iteracao.Iteravel;

public final class ListaJson extends ValorJson implements Iteravel<ValorJson> {

	private ListaPosicional<ValorJson> elementos;

	protected ListaJson() {
		elementos = ListaPosicional.criar();
	}

	public void inserir(ValorJson valor) {
		elementos.inserir(valor);
	}

	public void inserir(Texto valor) {
		elementos.inserir(Json.criarTexto(valor));
	}

	public void inserir(Numero valor) {
		elementos.inserir(Json.criarNumero(valor));
	}

	public void inserir(Booleano valor) {
		elementos.inserir(Json.criarBooleano(valor));
	}

	public ValorJson fornecer(Numero posicao) {
		return elementos.fornecerDaPosicao(posicao);
	}

	public Numero fornecerQuantidade() {
		return elementos.contarElementos();
	}

	@Override
	public Iterador<ValorJson> fornecerIterador() {
		return elementos.fornecerIterador();
	}

	@Override
	public Iterator<ValorJson> iterator() {
		return fornecerIterador();
	}

	@Override
	public ListaJson comoLista() {
		return this;
	}

	@Override
	public Texto comoTextoEmFormatoJson() {
		ConstrutorDeTexto textoJson = new ConstrutorDeTexto();
		textoJson.anexar(ABERTURA_DE_LISTA);
		Iterador<ValorJson> iterador = elementos.fornecerIterador();
		while (iterador.possuiProximo().avaliar()) {
			ValorJson elemento = iterador.iterarProximo();
			textoJson.anexar(elemento.comoTextoEmFormatoJson());
			if (iterador.possuiProximo().avaliar()) {
				textoJson.anexar(SEPARADOR);
			}
		}
		textoJson.anexar(FECHAMENTO_DE_LISTA);
		return textoJson.construir();
	}

	@Override
	public Booleano igual(ValorJson outro) {
		if (outro instanceof ListaJson) {
			Numero quatidade = fornecerQuantidade();
			ListaJson outroListaJson = (ListaJson) outro;
			if (quatidade.equals(outroListaJson.fornecerQuantidade())) {
				Contador contador = Contador.de(Numero.zero()).ate(quatidade);
				for (Numero contagem : contador) {
					ValorJson meuElemento = fornecer(contagem);
					ValorJson outroElemento = outroListaJson.fornecer(contagem);
					if (!meuElemento.equals(outroElemento)) {
						return Booleano.falso();
					}
				}
				return Booleano.verdadeiro();
			}
			return Booleano.falso();
		}
		return Booleano.falso();
	}

}
