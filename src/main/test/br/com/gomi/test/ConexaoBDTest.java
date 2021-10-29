package br.com.gomi.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import br.com.gomi.back.ConexaoBD;

class ConexaoBDTest {

	@Test
	void testGetConexao() throws SQLException {
		ConexaoBD connect = new ConexaoBD();
		Connection c = connect.getConexao();
		assertEquals(true, c.isValid(0));
	}

}
