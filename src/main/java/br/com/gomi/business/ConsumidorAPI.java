/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gomi.business;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Administrador
 */
public class ConsumidorAPI {

    private static final String URLBase = "https://maps.googleapis.com/maps/api/";
    private static final String token = "AIzaSyBm-NJBXbbypVeoPSr1adAK2czqzWlRfNg";

    private static ConsumidorAPI _instancia; //Instancia da API do Google Maps para o consumidor

    private final CloseableHttpClient clienteHTTP; //Endereço HTTP cliente

    private ConsumidorAPI() {
        this.clienteHTTP = HttpClients.createDefault();
    }

    public static ConsumidorAPI getInstancia() {
        if (_instancia == null) {
            _instancia = new ConsumidorAPI();
        }
        return _instancia;
    }

    //Direções do mapa
    public String getDirections(String origem, String destino) {
        String responseBody = null;
        try {
            HttpPost httpPost = new HttpPost(URLBase + "directions/json?origin=" + origem + "&destination=cep+" + destino + "&mode=driving&key=" + token);
            //Handler para pegar o conteúdo da resposta
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse hr) throws ClientProtocolException, IOException {
                    int status = hr.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = hr.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status" + status);
                    }
                }
            };
            responseBody = this.clienteHTTP.execute(httpPost, responseHandler);
        } catch (IOException erro) {
            Logger.getLogger(ConsumidorAPI.class.getName()).log(Level.SEVERE, null, erro);
        }
        return responseBody;
    }
}
