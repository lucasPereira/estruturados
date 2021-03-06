package br.dominioL.estruturados.testes;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import org.junit.Test;

import br.dominioL.estruturados.colecao.pilha.PilhaLista;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.excecoes.ExcecaoElementoNulo;
import br.dominioL.estruturados.excecoes.ExcecaoEstruturaVazia;
import br.dominioL.estruturados.excecoes.ExcecaoIteracaoInvalida;
import br.dominioL.estruturados.iteracao.Iterador;

public final class TestePilhaLista {

	private Numero numeroUm = Numero.criar(1);
	private Numero numeroDois = Numero.criar(2);
	private Numero numeroTres = Numero.criar(3);
	private Numero numeroQuatro = Numero.criar(4);

	@Test
	public void empilharEmPilhaVazia() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroUm);
		assertSame(pilha.fornecerDoTopo(), numeroUm);
		assertFalse(pilha.vazio().avaliar());
		assertThat(pilha.contarElementos().inteiro(), is(1));
	}

	@Test
	public void empilharEmPilhaNaoVazia() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroUm);
		pilha.empilhar(numeroDois);
		assertSame(pilha.fornecerDoTopo(), numeroDois);
		assertFalse(pilha.vazio().avaliar());
		assertThat(pilha.contarElementos().inteiro(), is(2));
	}

	@Test
	public void desempilharUmElemento() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		assertSame(pilha.desempilhar(), numeroUm);
		assertFalse(pilha.vazio().avaliar());
		assertThat(pilha.contarElementos().inteiro(), is(1));
	}

	@Test
	public void desempilharDoisElementos() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroTres);
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		assertSame(pilha.desempilhar(), numeroUm);
		assertSame(pilha.desempilhar(), numeroDois);
		assertFalse(pilha.vazio().avaliar());
		assertThat(pilha.contarElementos().inteiro(), is(1));
	}

	@Test
	public void desempilharTodosElementos() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroTres);
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		assertSame(pilha.desempilhar(), numeroUm);
		assertSame(pilha.desempilhar(), numeroDois);
		assertSame(pilha.desempilhar(), numeroTres);
		assertTrue(pilha.vazio().avaliar());
		assertThat(pilha.contarElementos().inteiro(), is(0));
	}

	@Test(expected = ExcecaoElementoNulo.class)
	public void empilharElementoNuloLancaUmaExcecao() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(null);
	}

	@Test(expected = ExcecaoEstruturaVazia.class)
	public void desempilharPilhaVaziaLancaUmaExcecao() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.desempilhar();
	}

	@Test(expected = ExcecaoEstruturaVazia.class)
	public void fornecerElementoDoTopoDeUmaPilhaVaziaLancaUmaExcecao() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.fornecerDoTopo();
	}

	@Test
	public void elementoEstaNoTopoSeFoiOUltimoElementoEmpilhado() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		assertTrue(pilha.estaNoTopo(numeroUm).avaliar());
	}

	@Test
	public void elementoNaoEstaNoTopoSeNaoFoiOUltimoElementoEmpilhado() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		assertTrue(pilha.estaNoTopo(numeroUm).avaliar());
	}

	@Test
	public void elementoNaoEstaNoTopoSeAPilhaEstaVazia() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		assertFalse(pilha.estaNoTopo(numeroUm).avaliar());
	}

	@Test
	public void inserirUmElementoDuplicadoMantemElementosDuplicados() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.inserir(numeroDois);
		pilha.inserir(numeroUm);
		assertThat(pilha.contarElementos().inteiro(), is(3));
	}

	@Test
	public void removerUmElemento() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.inserir(numeroDois);
		assertTrue(pilha.remover(numeroUm).avaliar());
		assertThat(pilha.contarElementos().inteiro(), is(1));
	}

	@Test
	public void removerUmElementoDuplicadoRemoveApenasOPrimeiro() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.inserir(numeroDois);
		pilha.inserir(numeroUm);
		assertTrue(pilha.remover(numeroUm).avaliar());
		assertThat(pilha.contarElementos().inteiro(), is(2));
		assertSame(pilha.fornecerDoTopo(), numeroDois);
	}

	@Test
	public void removerUmElementoQueNaoExiste() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		assertFalse(pilha.remover(numeroDois).avaliar());
		assertThat(pilha.contarElementos().inteiro(), is(1));
	}

	@Test
	public void contemUmElementoSeElePertence() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.inserir(numeroDois);
		pilha.inserir(numeroTres);
		assertTrue(pilha.contem(numeroDois).avaliar());
	}

	@Test
	public void naoContemUmElementoSeEleNaoPertence() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.inserir(numeroDois);
		pilha.inserir(numeroTres);;
		assertFalse(pilha.contem(numeroQuatro).avaliar());
	}

	@Test
	public void fornecerQuantidadeDeElementosQuandoNaoTemElementos() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		assertThat(pilha.contarElementos().inteiro(), is(0));
	}

	@Test
	public void fornecerQuantidadeDeElementosQuandoTemElementos() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.inserir(numeroDois);
		assertThat(pilha.contarElementos().inteiro(), is(2));
	}

	@Test
	public void estaVaziaSeNaoPossuiNenhumElemento() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.remover(numeroUm);
		assertTrue(pilha.vazio().avaliar());
	}

	@Test
	public void naoEstaVaziaSePossuiElementos() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		assertFalse(pilha.vazio().avaliar());
	}

	@Test
	public void iteradorPassaPorTodosElementos() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroUm);
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroTres);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		assertSame(iterador.iterarProximo(), numeroTres);
		assertSame(iterador.iterarProximo(), numeroDois);
		assertSame(iterador.iterarProximo(), numeroUm);
		assertFalse(iterador.possuiProximo().avaliar());
	}

	@Test
	public void iteradorPermiteRemoverTodosElementos() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroTres);
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		assertSame(iterador.remover(), numeroUm);
		iterador.iterarProximo();
		assertSame(iterador.remover(), numeroDois);
		iterador.iterarProximo();
		assertSame(iterador.remover(), numeroTres);
		assertFalse(iterador.possuiProximo().avaliar());
		assertTrue(pilha.vazio().avaliar());
	}

	@Test
	public void iteradorPermiteRemoverOPrimeiroElemento() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroTres);
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		assertSame(iterador.remover(), numeroUm);
		iterador.iterarProximo();
		iterador.iterarProximo();
		assertFalse(iterador.possuiProximo().avaliar());
		assertThat(pilha.contarElementos().inteiro(), is(2));
	}

	@Test
	public void iteradorPermiteRemoverOSegundoElemento() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroTres);
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		iterador.iterarProximo();
		assertSame(iterador.remover(), numeroDois);
		iterador.iterarProximo();
		assertFalse(iterador.possuiProximo().avaliar());
		assertThat(pilha.contarElementos().inteiro(), is(2));
	}

	@Test
	public void iteradorPermiteRemoverOUltimoElmento() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroTres);
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		iterador.iterarProximo();
		iterador.iterarProximo();
		assertSame(iterador.remover(), numeroTres);
		assertFalse(iterador.possuiProximo().avaliar());
		assertThat(pilha.contarElementos().inteiro(), is(2));
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverDuasVezesNaMesmaIteracao() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		pilha.fornecerIterador().remover();
		pilha.fornecerIterador().remover();
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverSemQueSeTenhaIteradoPeloMenosUmaVez() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.fornecerIterador().remover();
	}

	@Test
	public void iteradorPermiteSubstituirTodosElementos() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		assertSame(iterador.substituir(numeroTres), numeroUm);
		iterador.iterarProximo();
		assertSame(iterador.substituir(numeroQuatro), numeroDois);
		assertFalse(iterador.possuiProximo().avaliar());
		assertSame(pilha.fornecerDoTopo(), numeroTres);
		pilha.desempilhar();
		assertSame(pilha.fornecerDoTopo(), numeroQuatro);
	}

	@Test
	public void iteradorPermiteSubstituirOPrimeiroElemento() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroDois);
		pilha.empilhar(numeroUm);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		assertTrue(iterador.iterarProximo().igual(numeroUm).avaliar());
		assertTrue(iterador.substituir(numeroTres).igual(numeroUm).avaliar());
		assertTrue(iterador.iterarProximo().igual(numeroDois).avaliar());
		assertFalse(iterador.possuiProximo().avaliar());
		assertSame(pilha.fornecerDoTopo(), numeroTres);
	}

	@Test
	public void iteradorPermiteSubstituirOUltimoElmento() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.empilhar(numeroUm);
		pilha.empilhar(numeroDois);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		assertTrue(iterador.iterarProximo().igual(numeroDois).avaliar());
		assertTrue(iterador.iterarProximo().igual(numeroUm).avaliar());
		assertTrue(iterador.substituir(numeroTres).igual(numeroUm).avaliar());
		assertFalse(iterador.possuiProximo().avaliar());
		assertSame(pilha.fornecerDoTopo(), numeroDois);
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteSubstituirDuasVezesNaMesmaIteracao() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		iterador.substituir(numeroDois);
		iterador.substituir(numeroDois);
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteSubstituirSemQueSeTenhaIteradoPeloMenosUmaVez() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.fornecerIterador().substituir(numeroDois);
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteIterarMaisVezesQueONumeroDeElementosTendoZeroElementos() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.fornecerIterador().iterarProximo();
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteIterarMaisVezesQueONumeroDeElementosTendoMaisDeUmElemento() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.inserir(numeroDois);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		iterador.iterarProximo();
		iterador.iterarProximo();
	}

	@Test
	public void iteradorPermiteSubstituirEDepoisRemover() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.inserir(numeroDois);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		iterador.substituir(numeroTres);
		assertSame(iterador.remover(), numeroTres);
	}

	@Test(expected = ExcecaoIteracaoInvalida.class)
	public void iteradorNaoPermiteRemoverEDepoisSubstituir() {
		PilhaLista<Numero> pilha = PilhaLista.criar();
		pilha.inserir(numeroUm);
		pilha.inserir(numeroDois);
		Iterador<Numero> iterador = pilha.fornecerIterador();
		iterador.iterarProximo();
		iterador.remover();
		iterador.substituir(numeroTres);
	}

}
