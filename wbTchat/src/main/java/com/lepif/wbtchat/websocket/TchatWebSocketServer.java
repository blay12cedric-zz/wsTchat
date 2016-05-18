/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lepif.wbtchat.websocket;

import com.lepif.wbtchat.domain.Message;
import com.lepif.wbtchat.domain.User;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Lepif
 */
@ApplicationScoped
@ServerEndpoint("/tchat_ws")
public class TchatWebSocketServer {
    
    @Inject
    private SessionHandler handler;
    private static final Logger LOG = Logger.getLogger(TchatWebSocketServer.class.getName());
    
    @OnOpen
    public void onOpen(Session session){
        handler.addSession(session);
    }
    
    @OnClose
    public void onClose(Session session){
        handler.removeSession(session);
    }
    
     @OnMessage
    public void onMessage(String message, Session session) {
        
        try( JsonReader reader = Json.createReader(new StringReader(message))){
            JsonObject object = reader.readObject();
            
            //Ajouter un nouvel Utilisateur 
            if("addUser".equals(object.getString("action"))){
                User user = new User();
                
                user.setNom(object.getString("nom"));
                user.setPseudo(object.getString("pseudo"));
                handler.newUser(user);
            }
            
            //Déconnexion d'un Utilisateur
            if("removeUser".equals(object.getString("action"))){
                int id = object.getInt("id");
                handler.removeUser(id);
            }
            
            //Envoyer un message
            if("sendMessage".equals(object.getString("action"))){
                Message message1 = new Message();
                
                message1.setSenderId(object.getString("senderId"));
                message1.setText(object.getString("text"));
                handler.sendMessage(message1);
            }
        }
    }
    
    @OnError
    public void onError(Throwable throwable){
        LOG.log(Level.SEVERE, null, throwable);
    }
    
}