/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class ConexaoBD {

	// Conexão no banco de dados
	public static Connection getConexao() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		// String strCon =
		// "jdbc:sqlserver://localhost\\SQLEXPRESS2017;databaseName=Gomi;integratedSecurity=false;user=gb;password=GuilhermeBarros";
		String strCon = "jdbc:sqlserver://localhost;integratedSecurity=false;user=sa;password=1234";
		Connection conexao = DriverManager.getConnection(strCon);
		return conexao;
	}
}
