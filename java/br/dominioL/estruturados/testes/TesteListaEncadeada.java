package br.dominioL.estruturados.testes;

import static org.hamcrest.core.Is.is;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.excecoes.ExcecaoDeElementoNulo;
import br.dominioL.estruturados.excecoes.ExcecaoDeEstruturaVazia;
import br.dominioL.estruturados.excecoes.ExcecaoDeIteracaoInvalida;
import br.dominioL.estruturados.iteracao.Iterador;
import br.dominioL.estruturados.testes.figuracao.Numero;

public class TesteListaEncadeada {
	private Numero primeiroNumero = new Numero(1);
	private Numero segundoNumero = new Numero(2);
	private Numero terceiroNumero = new Numero(3);
	private Numero quartoNumero = new Numero(4);

	@Test
	public void fornecerUmElementoDoInicio() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoInicio(primeiroNumero);
		assertSame(primeiroNumero, lista.fornecerDoInicio());
		lista.inserirNoInicio(segundoNumero);
		assertSame(segundoNumero, lista.fornecerDoInicio());
	}

	@Test
	public void fornecerUmElementoDoFim() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoFim(primeiroNumero);
		assertSame(primeiroNumero, lista.fornecerDoFim());
		lista.inserirNoFim(segundoNumero);
		assertSame(segundoNumero, lista.fornecerDoFim());
	}

	@Test
	public void fornecerUmElemento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoInicio(primeiroNumero);
		assertSame(primeiroNumero, lista.fornecerDoInicio());
		assertSame(primeiroNumero, lista.fornecerDoFim());
		lista.inserirNoInicio(segundoNumero);
		assertSame(segundoNumero, lista.fornecerDoInicio());
		assertSame(primeiroNumero, lista.fornecerDoFim());
		lista.inserirNoFim(terceiroNumero);
		assertSame(segundoNumero, lista.fornecerDoInicio());
		assertSame(terceiroNumero, lista.fornecerDoFim());
	}

	@Test(expected = ExcecaoDeEstruturaVazia.class)
	public void fornecerUmElmentoDoInicioDeUmaListaVazia() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.fornecerDoInicio();
	}

	@Test(expected = ExcecaoDeEstruturaVazia.class)
	public void fornecerUmElmentoDoFimDeUmaListaVazia() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.fornecerDoFim();
	}

	@Test
	public void inserirUmElementoNoInicio() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		assertThat(lista.fornecerQuantidade(), is(0));
		lista.inserirNoInicio(primeiroNumero);
		assertThat(lista.fornecerQuantidade(), is(1));
		lista.inserirNoInicio(segundoNumero);
		assertThat(lista.fornecerQuantidade(), is(2));
	}

	@Test
	public void inserirUmElementoNoFim() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		assertThat(lista.fornecerQuantidade(), is(0));
		lista.inserirNoFim(primeiroNumero);
		assertThat(lista.fornecerQuantidade(), is(1));
		lista.inserirNoFim(segundoNumero);
		assertThat(lista.fornecerQuantidade(), is(2));
	}

	@Test(expected = ExcecaoDeElementoNulo.class)
	public void inserirUmElementoNuloNoInicio() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoInicio(null);
	}

	@Test(expected = ExcecaoDeElementoNulo.class)
	public void inserirUmElementoNuloNoFim() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoInicio(null);
	}

	@Test
	public void removerElementoDoInicio() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoInicio(primeiroNumero);
		Numero primeiroNumeroRemovido = lista.removerDoInicio();
		assertThat(lista.fornecerQuantidade(), is(0));
		lista.inserirNoInicio(segundoNumero);
		lista.inserirNoFim(terceiroNumero);
		Numero segundoNumeroRemovido = lista.removerDoInicio();
		assertThat(lista.fornecerQuantidade(), is(1));
		Numero terceiroNumeroRemovido = lista.removerDoInicio();
		assertThat(lista.fornecerQuantidade(), is(0));
		assertSame(primeiroNumeroRemovido, primeiroNumero);
		assertSame(segundoNumeroRemovido, segundoNumero);
		assertSame(terceiroNumeroRemovido, terceiroNumero);
	}

	@Test
	public void removerElementoDoFim() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoInicio(primeiroNumero);
		Numero primeiroNumeroRemovido = lista.removerDoFim();
		assertThat(lista.fornecerQuantidade(), is(0));
		lista.inserirNoFim(segundoNumero);
		lista.inserirNoInicio(terceiroNumero);
		Numero segundoNumeroRemovido = lista.removerDoFim();
		assertThat(lista.fornecerQuantidade(), is(1));
		Numero terceiroNumeroRemovido = lista.removerDoFim();
		assertThat(lista.fornecerQuantidade(), is(0));
		assertSame(primeiroNumeroRemovido, primeiroNumero);
		assertSame(segundoNumeroRemovido, segundoNumero);
		assertSame(terceiroNumeroRemovido, terceiroNumero);
	}

	@Test(expected = ExcecaoDeEstruturaVazia.class)
	public void removerUmElmentoDoInicioDeUmaListaVazia() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.removerDoInicio();
	}

	@Test(expected = ExcecaoDeEstruturaVazia.class)
	public void removerUmElmentoDoFimDeUmaListaVazia() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.removerDoFim();
	}

	@Test
	public void inserirUmElemento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		assertThat(lista.fornecerQuantidade(), is(1));
	}

	@Test
	public void inserirUmElementoDuplicadoMantemElementosDuplicados() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		lista.inserir(primeiroNumero);
		assertThat(lista.fornecerQuantidade(), is(3));
	}

	@Test
	public void removerUmElemento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		assertTrue(lista.remover(primeiroNumero));
		assertThat(lista.fornecerQuantidade(), is(0));
	}

	@Test
	public void removerUmElementoDuplicadoRemoveApenasOPrimeiro() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(primeiroNumero);
		assertTrue(lista.remover(primeiroNumero));
		assertThat(lista.fornecerQuantidade(), is(1));
		assertSame(lista.fornecerDoInicio(), primeiroNumero);
	}

	@Test
	public void removerUmElementoQueNaoExiste() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		assertFalse(lista.remover(segundoNumero));
		assertThat(lista.fornecerQuantidade(), is(1));
	}

	@Test
	public void contemUmElementoSeElePertence() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		lista.inserir(terceiroNumero);
		assertTrue(lista.contem(terceiroNumero));
	}

	@Test
	public void naoContemUmElementoSeEleNaoPertence() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		lista.inserir(terceiroNumero);
		assertFalse(lista.contem(quartoNumero));
	}

	@Test
	public void fornecerQuantidadeDeElementosQuandoNaoTemElementos() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		assertThat(lista.fornecerQuantidade(), is(0));
	}

	@Test
	public void fornecerQuantidadeDeElementosQuandoTemElementos() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		assertThat(lista.fornecerQuantidade(), is(2));
	}

	@Test
	public void estaVaziaSeNaoPossuiNenhumElemento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.remover(primeiroNumero);
		assertTrue(lista.vazio());
	}

	@Test
	public void naoEstaVaziaSePossuiElementos() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		assertFalse(lista.vazio());
	}

	@Test
	public void iteradorPassaPorTodosElementos() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoFim(primeiroNumero);
		lista.inserirNoFim(segundoNumero);
		lista.inserirNoFim(terceiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		assertSame(iterador.iterarProximo(), primeiroNumero);
		assertSame(iterador.iterarProximo(), segundoNumero);
		assertSame(iterador.iterarProximo(), terceiroNumero);
		assertFalse(iterador.possuiProximo());
	}

	@Test
	public void iteradorPermiteRemoverTodosElementos() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoFim(primeiroNumero);
		lista.inserirNoFim(segundoNumero);
		lista.inserirNoFim(terceiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		assertSame(iterador.remover(), primeiroNumero);
		iterador.iterarProximo();
		assertSame(iterador.remover(), segundoNumero);
		iterador.iterarProximo();
		assertSame(iterador.remover(), terceiroNumero);
		assertFalse(iterador.possuiProximo());
		assertTrue(lista.vazio());
	}

	@Test
	public void iteradorPermiteRemoverOPrimeiroElemento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoFim(primeiroNumero);
		lista.inserirNoFim(segundoNumero);
		lista.inserirNoFim(terceiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		assertSame(iterador.remover(), primeiroNumero);
		iterador.iterarProximo();
		iterador.iterarProximo();
		assertFalse(iterador.possuiProximo());
		assertThat(lista.fornecerQuantidade(), is(2));
	}

	@Test
	public void iteradorPermiteRemoverOSegundoElemento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoFim(primeiroNumero);
		lista.inserirNoFim(segundoNumero);
		lista.inserirNoFim(terceiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.iterarProximo();
		assertSame(iterador.remover(), segundoNumero);
		iterador.iterarProximo();
		assertFalse(iterador.possuiProximo());
		assertThat(lista.fornecerQuantidade(), is(2));
	}

	@Test
	public void iteradorPermiteRemoverOUltimoElmento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoFim(primeiroNumero);
		lista.inserirNoFim(segundoNumero);
		lista.inserirNoFim(terceiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.iterarProximo();
		iterador.iterarProximo();
		assertSame(iterador.remover(), terceiroNumero);
		assertFalse(iterador.possuiProximo());
		assertThat(lista.fornecerQuantidade(), is(2));
	}

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverDuasVezesNaMesmaIteracao() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		lista.fornecerIterador().remover();
		lista.fornecerIterador().remover();
	}

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverSemQueSeTenhaIteradoPeloMenosUmaVez() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.fornecerIterador().remover();
	}

	@Test
	public void iteradorPermiteSubstituirTodosElementos() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoFim(primeiroNumero);
		lista.inserirNoFim(segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		assertSame(iterador.substituir(terceiroNumero), primeiroNumero);
		iterador.iterarProximo();
		assertSame(iterador.substituir(quartoNumero), segundoNumero);
		assertFalse(iterador.possuiProximo());
		assertSame(lista.fornecerDoInicio(), terceiroNumero);
		assertSame(lista.fornecerDoFim(), quartoNumero);
	}

	@Test
	public void iteradorPermiteSubstituirOPrimeiroElemento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoInicio(primeiroNumero);
		lista.inserirNoFim(segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		while (iterador.possuiProximo()) {
			if (iterador.iterarProximo() == primeiroNumero) {
				iterador.substituir(terceiroNumero);
			}
		}
		assertSame(lista.fornecerDoInicio(), terceiroNumero);
	}

	@Test
	public void iteradorPermiteSubstituirOUltimoElmento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserirNoInicio(primeiroNumero);
		lista.inserirNoFim(segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		while (iterador.possuiProximo()) {
			if (iterador.iterarProximo() == segundoNumero) {
				iterador.substituir(terceiroNumero);
			}
		}
		assertSame(lista.fornecerDoFim(), terceiroNumero);
	}

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteSubstituirDuasVezesNaMesmaIteracao() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.substituir(segundoNumero);
		iterador.substituir(segundoNumero);
	}

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteSubstituirSemQueSeTenhaIteradoPeloMenosUmaVez() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.fornecerIterador().substituir(segundoNumero);
	}

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteIterarMaisVezesQueONumeroDeElementosTendoZeroElementos() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.fornecerIterador().iterarProximo();
	}

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteIterarMaisVezesQueONumeroDeElementosTendoMaisDeUmElemento() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.iterarProximo();
		iterador.iterarProximo();
	}

	@Test
	public void iteradorPermiteSubstituirEDepoisRemover() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.substituir(terceiroNumero);
		assertSame(iterador.remover(), terceiroNumero);
	}

	@Test(expected = ExcecaoDeIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverEDepoisSubstituir() {
		ListaEncadeada<Numero> lista = ListaEncadeada.criar();
		lista.inserir(primeiroNumero);
		lista.inserir(segundoNumero);
		Iterador<Numero> iterador = lista.fornecerIterador();
		iterador.iterarProximo();
		iterador.remover();
		iterador.substituir(terceiroNumero);
	}
}