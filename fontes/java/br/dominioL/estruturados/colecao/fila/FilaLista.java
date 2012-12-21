package br.dominioL.estruturados.colecao.fila;

import br.dominioL.estruturados.colecao.ColecaoAbstrata;
import br.dominioL.estruturados.colecao.lista.ListaEncadeada;
import br.dominioL.estruturados.elemento.Igualavel;
import br.dominioL.estruturados.iteracao.Iterador;

public final class FilaLista<E extends Igualavel<E>> extends ColecaoAbstrata<E> implements Fila<E> {
	private ListaEncadeada<E> elementos;
	
	private FilaLista() {
		elementos = ListaEncadeada.criar();
	}
	
	public static <F extends Igualavel<F>> FilaLista<F> criar() {
		return new FilaLista<F>();
	}
	
	@Override
	public Integer fornecerQuantidade() {
		return elementos.fornecerQuantidade();
	}
	
	@Override
	public Boolean contem(E elemento) {
		if (elemento != null) {
			return elementos.contem(elemento);
		}
		return false;
	}
	
	@Override
	public Boolean remover(E elemento) {
		if (elemento != null) {
			return elementos.remover(elemento);
		}
		return false;
	}
	
	@Override
	public void inserir(E elemento) {
		enfilerar(elemento);
	}
	
	@Override
	public Iterador<E> fornecerIterador() {
		return elementos.fornecerIterador();
	}
	
	@Override
	public void enfilerar(E elemento) {
		elementos.inserirNoFim(elemento);
	}
	
	@Override
	public E desenfilerar() {
		return elementos.removerDoInicio();
	}
	
	@Override
	public E fornecerDoInicio() {
		return elementos.fornecerDoInicio();
	}
	
	@Override
	public E fornecerDoFim() {
		return elementos.fornecerDoFim();
	}
	
	@Override
	public Boolean estaNoInicio(E elemento) {
		if (!vazio() && elemento != null) {
			return elementos.fornecerDoInicio().igual(elemento);
		}
		return false;
	}
	
	@Override
	public Boolean estaNoFim(E elemento) {
		if (!vazio() && elemento != null) {
			return elementos.fornecerDoFim().igual(elemento);
		}
		return false;
	}
}