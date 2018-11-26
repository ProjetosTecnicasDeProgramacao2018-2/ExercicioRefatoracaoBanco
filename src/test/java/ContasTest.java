import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;

import com.bcopstein.ExercicioRefatoracaoBanco.Negocio.Contas;

import org.junit.jupiter.params.ParameterizedTest;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ContasTest {
	private Contas contas;
	
	@BeforeEach
	public void inicializa() {
		this.contas = mock(Contas.class);
		
		this.criaDados();
	}
	
	private void criaDados() {
		when(this.contas.autenticaLogin(100)).thenReturn(true);
		when(this.contas.autenticaLogin(101)).thenReturn(false);

		when(this.contas.existeContaAtual()).thenReturn(true);

		when(this.contas.credito(100)).thenReturn(true);
		when(this.contas.credito(-10)).thenReturn(false);
		when(this.contas.credito(0)).thenReturn(false);
		
		when(this.contas.debito(100)).thenReturn(true);
		when(this.contas.debito(-10)).thenReturn(false);
		when(this.contas.debito(0)).thenReturn(false);
	}

	@ParameterizedTest
	@CsvSource({
		"100,true",
		"101,false"
	})
	public void testAutenticaLogin(Integer entrada, boolean saida) {
		assertEquals(this.contas.autenticaLogin(entrada), saida); 
	}

	@Test
	public void testExistsContaAtual() {
		assertEquals(this.contas.existeContaAtual(), true); 
	}
	
	@ParameterizedTest
	@CsvSource({
		"100,true",
		"-10,false",
		"0,false"
	})
	public void testCredito(Integer entrada, boolean saida) {
		assertEquals(this.contas.credito(entrada), saida); 
	}	

	@ParameterizedTest
	@CsvSource({
		"100,true",
		"-10,false",
		"0,false"
	})
	public void testDebito(Integer entrada, boolean saida) {
		assertEquals(this.contas.debito(entrada), saida); 
	}
}
