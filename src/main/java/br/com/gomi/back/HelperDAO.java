/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.back;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Administrador
 */
public class HelperDAO {

	// Executa uma procedure
	public static void executaProc(String sql, String[] parametros) throws Exception {
		try (Connection conexao = ConexaoBD.getConexao()) {
			PreparedStatement comando = conexao.prepareStatement(sql);
			for (int i = 1; i <= parametros.length; i++) {
				if (parametros[i - 1].equals("null") || parametros[i - 1] == null) {
					comando.setNull(i, 0);
				} else {
					comando.setString(i, parametros[i - 1]);
				}
			}
			comando.execute();
			conexao.close();
		}
	}

	// Criar uma tabela intermediaria com o nome do campo e as linhas. Executa uma
	// procedure que devolve o valor
	public static JDataTable executaProcSelect(String sql, String[] parametros) throws Exception {
		try (Connection conexao = ConexaoBD.getConexao()) {
			PreparedStatement comando = conexao.prepareStatement(sql);
			for (int i = 1; i <= parametros.length; i++) {
				if (parametros[i - 1].equals("null") || parametros[i - 1] == null) {
					comando.setNull(i, 0);
				} else {
					comando.setString(i, parametros[i - 1]);
				}
			}
			ResultSet rs = comando.executeQuery();
			JDataTable tabela = new JDataTable(rs);
			if (tabela.getNumeroLinhas() == 0) {
				return null;
			}
			conexao.close();
			return tabela;
		}
	}
}
