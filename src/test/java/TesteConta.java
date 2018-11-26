import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;

import com.bcopstein.ExercicioRefatoracaoBanco.Negocio.Conta;
import com.bcopstein.ExercicioRefatoracaoBanco.Negocio.Contas;

import org.junit.jupiter.params.ParameterizedTest;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TesteConta {

	@ParameterizedTest
	@CsvSource({
		"0,Silver",
		"1,Gold",
		"2,Platinum"
	})
	public void testGetStateAtual(Integer entrada, String saida) {
		Conta conta = new Conta(123,"Douglas");
		conta.setState(entrada);
		assertEquals(conta.getStrStatus(), saida); 
	}	
	
	@Test
	public void testGetErroStateAtual() {
		Conta conta = new Conta(123,"Douglas");
		conta.setState(3); // STATUS NÃO EXISTENTE
		assertThrows(NullPointerException.class, () -> {conta.getStrStatus();}); 
	}	

	@ParameterizedTest
	@CsvSource({
		"0,10000.0",
		"1,100000.0",
		"2,500000.0"
	})
	public void testGetLimiteDiario(Integer entrada, double saida) {
		Conta conta = new Conta(123,"Douglas");
		conta.setState(entrada);
		assertEquals(conta.getLimRetiradaDiaria(), saida,10); // 10 é o "delta"
	}	

}
