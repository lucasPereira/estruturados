package br.opp.estruturados.colecao.lista;

import br.opp.estruturados.Encadeavel;
import br.opp.estruturados.elemento.Igualavel;
import br.opp.estruturados.excecoes.ExcecaoDeIteracaoInvalida;
import br.opp.estruturados.iteracao.Iterador;
import br.opp.estruturados.iteracao.IteradorAbstrato;

public final class ListaEncadeada<E extends Igualavel<E>> extends ListaAbstrata<E> implements Encadeavel<E>, Igualavel<ListaEncadeada<E>> {
	private int quantidadeDeElementos;
	private Caixa caixaDoInicio;
	private Caixa caixaDoFim;
	
	private ListaEncadeada() {
		quantidadeDeElementos = 0;
	}
	
	public static <F extends Igualavel<F>> ListaEncadeada<F> criar() {
		return new ListaEncadeada<F>();
	}
	
	@Override
	public void inserirNoInicio(E elemento) {
		lancarExcecaoDeElementoNuloSeNecessario(elemento);
		if (quantidadeDeElementos == 0) {
			caixaDoInicio = new Caixa(elemento);
			caixaDoFim = caixaDoInicio;
		} else {
			Caixa caixaDoInicioAntiga = caixaDoInicio;
			caixaDoInicio = new Caixa(elemento);
			caixaDoInicio.fixarCaixaDaDireita(caixaDoInicioAntiga);
			caixaDoInicioAntiga.fixarCaixaDaEsquerda(caixaDoInicio);
		}
		quantidadeDeElementos++;
	}
	
	@Override
	public void inserirNoFim(E elemento) {
		lancarExcecaoDeElementoNuloSeNecessario(elemento);
		if (quantidadeDeElementos == 0) {
			caixaDoFim = new Caixa(elemento);
			caixaDoInicio = caixaDoFim;
		} else {
			Caixa caixaDoFimAntiga = caixaDoFim;
			caixaDoFim = new Caixa(elemento);
			caixaDoFim.fixarCaixaDaEsquerda(caixaDoFimAntiga);
			caixaDoFimAntiga.fixarCaixaDaDireita(caixaDoFim);
		}
		quantidadeDeElementos++;
	}
	
	@Override
	public E removerDoInicio() {
		lancarExcecaoDeEstruturaVaziaSeNecessario();
		Caixa caixaDoInicioNova = caixaDoInicio.fornecerCaixaDaDireita();
		E elemento = caixaDoInicio.fornecerElemento();
		caixaDoInicio.removerPonteiros();
		caixaDoInicio = caixaDoInicioNova;
		if (caixaDoInicio != null) {
			caixaDoInicio.fixarCaixaDaEsquerda(null);
		} else {
			caixaDoFim = null;
		}
		quantidadeDeElementos--;
		return elemento;
	}
	
	@Override
	public E removerDoFim() {
		lancarExcecaoDeEstruturaVaziaSeNecessario();
		Caixa caixaDoFimNova = caixaDoFim.fornecerCaixaDaEsquerda();
		E elemento = caixaDoFim.fornecerElemento();
		caixaDoFim.removerPonteiros();
		caixaDoFim = caixaDoFimNova;
		if (caixaDoFim != null) {
			caixaDoFim.fixarCaixaDaDireita(null);
		} else {
			caixaDoInicio = null;
		}
		quantidadeDeElementos--;
		return elemento;
	}
	
	@Override
	public E fornecerDoInicio() {
		lancarExcecaoDeEstruturaVaziaSeNecessario();
		return caixaDoInicio.fornecerElemento();
	}
	
	@Override
	public E fornecerDoFim() {
		lancarExcecaoDeEstruturaVaziaSeNecessario();
		return caixaDoFim.fornecerElemento();
	}
	
	@Override
	public void inserir(E elemento) {
		inserirNoInicio(elemento);
	}
	
	@Override
	public int fornecerQuantidade() {
		return quantidadeDeElementos;
	}
	
	@Override
	public Iterador<E> fornecerIterador() {
		return new IteradorDeListaEncadeada();
	}
	
	private final class IteradorDeListaEncadeada extends IteradorAbstrato<E> {
		private Caixa cursor;
		private Caixa cursorAnterior;
		private boolean removeu;
		private boolean substituiu;
		
		private IteradorDeListaEncadeada() {
			cursor = caixaDoInicio;
			cursorAnterior = null;
			removeu = false;
			substituiu = false;
		}
		
		@Override
		public boolean possuiProximo() {
			return (cursor != null);
		}
		
		@Override
		public E iterarProximo() {
			if (!possuiProximo()) {
				throw new ExcecaoDeIteracaoInvalida();
			}
			E elementoAtual = cursor.fornecerElemento();
			cursorAnterior = cursor;
			cursor = cursor.fornecerCaixaDaDireita();
			removeu = false;
			substituiu = false;
			return elementoAtual;
		}
		
		@Override
		public E remover() {
			if (removeu || cursorAnterior == null) {
				throw new ExcecaoDeIteracaoInvalida();
			}
			E elementoRemovido = cursorAnterior.fornecerElemento();
			Caixa caixaDaEsquerdaNova = cursorAnterior.fornecerCaixaDaEsquerda();
			if (cursor != null) {
				cursor.fixarCaixaDaEsquerda(caixaDaEsquerdaNova);
			} else {
				caixaDoFim = caixaDaEsquerdaNova;
			}
			if (caixaDaEsquerdaNova != null) {
				caixaDaEsquerdaNova.fixarCaixaDaDireita(cursor);
			} else {
				caixaDoInicio = cursor;
			}
			cursorAnterior.removerPonteiros();
			removeu = true;
			quantidadeDeElementos--;
			return elementoRemovido;
		}
		
		@Override
		public E substituir(E substituto) {
			lancarExcecaoDeElementoNuloSeNecessario(substituto);
			if (removeu || substituiu || cursorAnterior == null) {
				throw new ExcecaoDeIteracaoInvalida();
			}
			E elementoSubstituido = cursorAnterior.fornecerElemento();
			cursorAnterior.fixarElemento(substituto);
			substituiu = true;
			return elementoSubstituido;
		}
	}
	
	private final class Caixa {
		private E elemento;
		private Caixa caixaDaEsquerda;
		private Caixa caixaDaDireita;
		
		private Caixa(E elemento) {
			this.elemento = elemento; 
		}
		
		private void fixarCaixaDaEsquerda(Caixa caixa) {
			this.caixaDaEsquerda = caixa;
		}
		
		private void fixarCaixaDaDireita(Caixa caixa) {
			this.caixaDaDireita = caixa;
		}
		
		private void fixarElemento(E elemento) {
			this.elemento = elemento;
		}
		
		private Caixa fornecerCaixaDaEsquerda() {
			return caixaDaEsquerda;
		}
		
		private Caixa fornecerCaixaDaDireita() {
			return caixaDaDireita;
		}
		
		private E fornecerElemento() {
			return elemento;
		}
		
		private void removerPonteiros() {
			elemento = null;
			caixaDaEsquerda = null;
			caixaDaDireita = null;
		}
	}
	
	@Override
	public boolean igual(ListaEncadeada<E> outro) {
		return (this == outro);
	}
}

