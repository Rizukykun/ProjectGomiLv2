/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.business;

import br.com.gomi.back.LogDAO;
import br.com.gomi.shared.LogViewModel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fábio
 */
//thread responsavel por transferir da fila de logs para o banco
public class ThreadLogs extends Thread {

    private boolean ativo = true;

    @Override
    public void run() {
        Auditoria auditoria = Auditoria.obtemInstancia();

        while (isAtivo() || auditoria.existemLogsNaFila()) {
            if (auditoria.existemLogsNaFila()) {
                String log = auditoria.getFila().poll();

                LogDAO dao = new LogDAO();
                LogViewModel model = new LogViewModel();

                model.setDescricao(log);

                try {
                    dao.insert(model);
                } catch (Exception ex) {
                    Logger.getLogger(ThreadLogs.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
