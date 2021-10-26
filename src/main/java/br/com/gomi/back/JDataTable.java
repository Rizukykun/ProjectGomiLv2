package br.com.gomi.back;

import java.util.Map;
import java.util.HashMap;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author Thiago Arreguy 22/12/2011 Adaptado por Guilherme Barros 23/03/2021
 */
public class JDataTable {

    private Map<Integer, HashMap<String, Object>> dados;
    private Vector<String> nomeColunas;

    public JDataTable(ResultSet rs) {
        this.nomeColunas = new Vector<>();
        this.dados = new HashMap<>();
        setNomeColunas(rs);
        popular(rs);
    }

    private void setNomeColunas(ResultSet rs) {
        try {
            // Get result set meta data
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColunas = rsmd.getColumnCount();
            //System.out.println("numColumns: " + numColumns);

            // Get the column names; column indices start from 1
            for (int i = 1; i <= numColunas; i++) {
                String nomeColuna = rsmd.getColumnName(i);

                nomeColunas.add(nomeColuna);
            }
        } catch (SQLException e) {
        }
    }

	private void popular(ResultSet rs) {
        try {
            if (rs != null) {
                while (rs.next()) {
                    HashMap<String, Object> valorMapa = new HashMap<>();

                    for (int i = 1; i <= this.nomeColunas.size(); i++) {
                        String chave = this.nomeColunas.get(i - 1); //index comeca em 0
                        Object valor = rs.getObject(i);
                        valor = rs.wasNull() ? "" : valor;

                        valorMapa.put(chave, valor);

                    }

                    int key = rs.getRow();
                    this.dados.put(key, new HashMap<>((valorMapa)));

                    //System.out.println("ROW:\t" + key );
                    valorMapa.clear();
                }
            }
        } catch (SQLException e) {
        }
    }

    public int getNumeroColunas() {
        return this.nomeColunas.size();
    }

    public int getNumeroLinhas() {
        return this.dados.size();
    }

    public String getNomeColuna(int coluna) {
        return this.nomeColunas.get(coluna - 1);
    }

    public Object getValorEm(int linha, String coluna) {
        Map<String, Object> valorMapa = this.dados.get(linha);
        return valorMapa.get(coluna);
    }

    public HashMap<String, Object> getLinha(int linha) {
        return this.dados.get(linha);
    }
}
