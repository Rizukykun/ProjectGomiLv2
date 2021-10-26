/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.back;

import java.util.ArrayList;
import java.util.HashMap;
import br.com.gomi.shared.*;
import java.math.BigDecimal;

/**
 *
 * @author Administrador
 * @param <T>
 */
public abstract class PadraoDAO<T> extends PadraoViewModel {

    protected PadraoDAO() {
        setTabela();
        setQtdParametros();
    }
    ;
    protected String tabela; //tabela
    protected String chave;
    protected String qtdParametros; //quantidade de parametros

    protected abstract String[] CriaParametros(T model); //Talvez nao seja statement, mas aqui é para passar parametros

    protected abstract T MontaModel(HashMap<String, Object> registro);

    protected abstract void setTabela();

    protected abstract void setQtdParametros();

    public int insert(T model) throws Exception {
        JDataTable tab = HelperDAO.executaProcSelect("exec spInsert_" + getTabela() + getQtdParametros(), CriaParametros(model));
        return ((BigDecimal) tab.getLinha(1).get("Id")).intValue();
    }

    public void update(T model) throws Exception {
        HelperDAO.executaProc("exec spUpdate_" + getTabela() + getQtdParametros(), CriaParametros(model));
    }

    public void delete(int id) throws Exception {
        String[] parametros = {String.valueOf(id), getTabela()};
        HelperDAO.executaProc("spDelete ?, ? ", parametros);
    }

    public T consult(int id) throws Exception {
        String[] parametros = {String.valueOf(id), getTabela()};
        JDataTable tab = HelperDAO.executaProcSelect("spConsult ?, ?", parametros);
        if (tab.getNumeroLinhas() == 0) {
            return null;
        } else {
            return MontaModel(tab.getLinha(1));
        }
    }

    public ArrayList<T> list(String ordem) throws Exception {
        String[] parametros = {getTabela(), ordem};
        JDataTable tab;
        tab = HelperDAO.executaProcSelect("spList ?, ?", parametros);
        ArrayList<T> lista = new ArrayList<>();
        for (int linha = 1; linha <= tab.getNumeroLinhas(); linha++) {
            lista.add(MontaModel(tab.getLinha(linha)));
        }
        return lista;
    }

    /**
     * @return the tabela
     */
    public String getTabela() {
        return tabela;
    }

    /**
     * @return the chave
     */
    public String getChave() {
        return chave;
    }

    /**
     * @param chave the chave to set
     */
    public void setChave(String chave) {
        this.chave = chave;
    }

    /**
     * @return the qtdParametros
     */
    public String getQtdParametros() {
        return qtdParametros;
    }
}
