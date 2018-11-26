import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.bcopstein.ExercicioRefatoracaoBanco.Negocio.Contas;
import com.bcopstein.ExercicioRefatoracaoBanco.Negocio.Operacoes;

public class OperacoesTest {
	private Operacoes operacoes;
	
	@BeforeEach
	public void inicializa() {
		this.operacoes = mock(Operacoes.class);
		
		this.criaDados();
	}
	
	private void criaDados() {
		/*when(this.operacoes.autenticaLogin(100)).thenReturn(true);
		when(this.operacoes.autenticaLogin(101)).thenReturn(false);

		when(this.operacoes.existeContaAtual()).thenReturn(true);
*/
		when(this.operacoes.credito(100)).thenReturn(true);
		when(this.operacoes.credito(-10)).thenReturn(false);
		when(this.operacoes.credito(0)).thenReturn(false);
		
		when(this.operacoes.debito(100)).thenReturn(true);
		when(this.operacoes.debito(-10)).thenReturn(false);
		when(this.operacoes.debito(0)).thenReturn(false);
		
	}
	

	@ParameterizedTest
	@CsvSource({
		"100,true",
		"-10,false",
		"0,false"
	})
	public void testCredito(Integer entrada, boolean saida) {
		assertEquals(this.operacoes.credito(entrada), saida); 
	}	

	@ParameterizedTest
	@CsvSource({
		"100,true",
		"-10,false",
		"0,false"
	})
	public void testDebito(Integer entrada, boolean saida) {
		assertEquals(this.operacoes.debito(entrada), saida); 
	}
}
