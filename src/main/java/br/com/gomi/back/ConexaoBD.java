/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.back;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class ConexaoBD {

	// ConexÃ£o no banco de dados
	public static Connection getConexao() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// Para motivos de testes e manteneção da sanidade do grupo
		String[] buttons = { "Barros", "Fábio" };
		String strCon = "";
		/*
		 * int opcao = JOptionPane.showOptionDialog(null, "Quém está usando o banco?",
		 * "Diga: ", JOptionPane.WARNING_MESSAGE, 0, null, buttons, 0); if (opcao == 0)
		 * strCon =
		 * "jdbc:sqlserver://localhost\\SQLEXPRESS2017;databaseName=Gomi;integratedSecurity=false;user=gb;password=GuilhermeBarros";
		 * else strCon =
		 * "jdbc:sqlserver://localhost;integratedSecurity=false;databaseName=Gomi;user=sa;password=1234";
		 */
		strCon = "jdbc:sqlserver://localhost\\SQLEXPRESS2017;databaseName=Gomi;integratedSecurity=false;user=gb;password=GuilhermeBarros";
		Connection conexao = DriverManager.getConnection(strCon);
		return conexao;
	}
}
