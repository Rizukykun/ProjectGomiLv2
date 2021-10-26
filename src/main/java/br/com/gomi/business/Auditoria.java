/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.business;

import java.util.concurrent.ConcurrentLinkedQueue;

//Auditoria é responsavel por registrar todos os logs do programa
public class Auditoria {

    /*Singleton*/
    static private Auditoria _instancia;

    private Auditoria() {
    }

    public static Auditoria obtemInstancia() {
        if (_instancia == null) {
            _instancia = new Auditoria();
        }
        return _instancia;
    }
    /*Singleton*/

    ThreadLogs thread; //Thread responsavel por ler a fila e salvar no banco
    //Fila de logs thread safe
    static private ConcurrentLinkedQueue<String> fila = new ConcurrentLinkedQueue<String>();

    public ConcurrentLinkedQueue<String> getFila() {
        return fila;
    }

    public void salvarLog(String log) {
        fila.add(log);
    }

    public boolean existemLogsNaFila() {
        return !fila.isEmpty();
    }

    //método que inicia a thread
    public void iniciar() {
        if (thread == null) {
            thread = new ThreadLogs();
            thread.setName("Auditoria");
            thread.start();
        }
    }

    //método que finaliza a thread
    public void finalizar() {
        if (thread != null) {
            thread.setAtivo(false);
        }
    }
}
