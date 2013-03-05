package br.dominioL.estruturados.testes;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import br.dominioL.estruturados.excecoes.ExcecaoJsonDeAnalise;
import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ListaJson;
import br.dominioL.estruturados.json.ObjetoJson;

public class TesteJson {
	@Test
	public void objetoVazio() {
		ObjetoJson json = Json.criarObjeto("{}");
		assertThat(json.fornecerComoJson(), is("{}"));
		json = Json.criarObjeto("{   }");
		assertThat(json.fornecerComoJson(), is("{}"));
	}
	
	@Test
	public void objetoComObjetoDentro() {
		ObjetoJson json = Json.criarObjeto("   {   \"outroObjeto\"   : {   }   }   ");
		assertThat(json.fornecerComoJson(), is("{\"outroObjeto\": {}}"));
	}
	
	@Test
	public void inserirObjetoDentroDeObjeto() {
		ObjetoJson objetoPrimario = Json.criarObjeto();
		ObjetoJson objetoSecundario = Json.criarObjeto();
		ObjetoJson objetoTerciario = Json.criarObjeto();
		objetoSecundario.inserir(Json.criarIdentificador("terciario"), objetoTerciario);
		objetoPrimario.inserir(Json.criarIdentificador("secundario"), objetoSecundario);
		assertThat(objetoPrimario.fornecerComoJson(), is("{\"secundario\": {\"terciario\": {}}}"));
	}
	
	@Test
	public void objetoComTextoDentro() {
		ObjetoJson json = Json.criarObjeto("{\"texto\" : 'valorTextual'}");
		assertThat(json.fornecerComoJson(), is("{\"texto\": \"valorTextual\"}"));
	}
	
	@Test
	public void objetoComBooleanoFalsoDentro() {
		ObjetoJson json = Json.criarObjeto("   {   \"booleano\" : false   }   ");
		assertThat(json.fornecerComoJson(), is("{\"booleano\": false}"));
	}
	
	@Test
	public void objetoComBooleanoVerdadeiroDentro() {
		ObjetoJson json = Json.criarObjeto(" {\"booleano\" : true} ");
		assertThat(json.fornecerComoJson(), is("{\"booleano\": true}"));
	}
	
	@Test
	public void objetoComNumeroDentro() {
		ObjetoJson json = Json.criarObjeto(" {\"numero\" : 1} ");
		assertThat(json.fornecerComoJson(), is("{\"numero\": 1}"));
		json = Json.criarObjeto(" {\"numero\" : 1.0} ");
		assertThat(json.fornecerComoJson(), is("{\"numero\": 1.0}"));
		json = Json.criarObjeto(" {\"numero\" : 1.0101} ");
		assertThat(json.fornecerComoJson(), is("{\"numero\": 1.0101}"));
		json = Json.criarObjeto(" {\"numero\" : 0.0101} ");
		assertThat(json.fornecerComoJson(), is("{\"numero\": 0.0101}"));
	}
	
	@Test
	public void listaComElementosDentro() {
		ListaJson json = Json.criarLista(" [ 10, 'texto' , true , false , \"outroTexto\" , { \"numero\": 10.1 } , [ 1, 2, 3, { } ] ] ");
		assertThat(json.fornecerComoJson(), is("[10, \"texto\", true, false, \"outroTexto\", {\"numero\": 10.1}, [1, 2, 3, {}]]"));
	}
	
	@Test
	public void inserirElementosDentroDeLista() {
		ListaJson letras = Json.criarLista();
		ListaJson numeros = Json.criarLista();
		numeros.inserir(Json.criarNumero(1));
		numeros.inserir(Json.criarNumero(3));
		numeros.inserir(Json.criarNumero(2));
		letras.inserir(Json.criarTexto("a"));
		letras.inserir(Json.criarTexto("b"));
		letras.inserir(Json.criarTexto("c"));
		numeros.inserir(letras);
		assertThat(numeros.fornecerComoJson(), is("[1, 3, 2, [\"a\", \"b\", \"c\"]]"));
	}
	
	@Test(expected = ExcecaoJsonDeAnalise.class)
	public void textoIncompletoLancaExcecao() {
		ObjetoJson json = Json.criarObjeto("{\"texto\": \"inicio}");
	}
	
	@Test(expected = ExcecaoJsonDeAnalise.class)
	public void booleanoIncompletoLancaExcecao() {
		ObjetoJson json = Json.criarObjeto("{\"booleano\": fals}");
	}
	
	@Test(expected = ExcecaoJsonDeAnalise.class)
	public void numeroComLetrasLancaExcecao() {
		ObjetoJson json = Json.criarObjeto("{\"numero\": 10A0}");
	}
	
	@Test(expected = ExcecaoJsonDeAnalise.class)
	public void vazioLancaExcecao() {
		ObjetoJson json = Json.criarObjeto("");
	}
	
	@Test(expected = ExcecaoJsonDeAnalise.class)
	public void objetoComVazioLancaExcecao() {
		ObjetoJson json = Json.criarObjeto("{\"outro\":   }");
	}
	
	@Test(expected = ExcecaoJsonDeAnalise.class)
	public void objetoNaoFechadoLancaExcecao() {
		ObjetoJson json = Json.criarObjeto("{\"outroObjeto\": {}");
	}
	
	@Test(expected = ExcecaoJsonDeAnalise.class)
	public void listaNaoFechadaLancaExcecao() {
		ListaJson json = Json.criarLista("[1, 2, 3");
	}
	
	@Test(expected = ExcecaoJsonDeAnalise.class)
	public void listaComSeparadorAMaisLancaExcecao() {
		ListaJson json = Json.criarLista("[1, 2, 3, ]");
	}
	
	@Test(expected = ExcecaoJsonDeAnalise.class)
	public void objetoSemSeparadorDeIdentificadorLancaExcecao() {
		ObjetoJson json = Json.criarObjeto("{\"outroObjeto\" {}}");
	}
}