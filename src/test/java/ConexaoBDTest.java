import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import br.com.gomi.back.ConexaoBD;

class ConexaoBDTest {

	@Test
	void testGetConexao() throws Exception {
		Connection connect = ConexaoBD.getConexao();
		assertEquals(connect.isValid(0), true);
	}

}
