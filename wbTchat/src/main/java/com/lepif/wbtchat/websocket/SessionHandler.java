/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lepif.wbtchat.websocket;

import com.lepif.wbtchat.domain.Message;
import com.lepif.wbtchat.domain.User;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;

/**
 *
 * @author Lepif
 */
@ApplicationScoped
public class SessionHandler {

    private int userId = 0;
    private int messageId = 0;
    
    private final Set sessions = new HashSet<>();
    private final Set users = new HashSet<>();
    private final Set messages = new HashSet<>();

    public void addSession(Session session) {
        sessions.add(session);
        
        for(Object user: users){
            JsonObject addUser = createAddUser((User)user);
            sendToSession(session, addUser);
        }
        
        for(Object message: messages){
            JsonObject addMessage = createAddMessage((Message)message);
            sendToSession(session, addMessage);
        }
    }
    
    public void sendMessage(Message message){
        message.setId(messageId);
        messageId++;
        messages.add(message);
        
        JsonObject sendMessage = createAddMessage(message);
        sendToAllConnectedSessions(sendMessage);
    }
    
    public void newUser(User user){
        user.setId(userId);
        userId++;
        users.add(user);
        
        JsonObject sendUser = createAddUser(user);
        sendToAllConnectedSessions(sendUser);
    }
    
    public void removeUser(int id){
        User u = getUserById(id);
        users.remove(u);
        
        JsonObject removeUser = removeAddUser(u, u.getId());
        sendToAllConnectedSessions(removeUser);
    }
    
    private User getUserById(int id){
        
        for(Object user: users){
            if(id == ((User)user).getId()){
               return (User)user; 
            }
        }
        
        return null;
    }
    
    private JsonObject createAddUser(User user) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addUser = provider.createObjectBuilder()
                .add("action", "addUser")
                .add("id", user.getId())
                .add("name", user.getNom())
                .add("pseudo", user.getPseudo())
                .build();
        return addUser;
    }
    
    private JsonObject removeAddUser(User user, int id) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addUser = provider.createObjectBuilder()
                .add("action", "removeUser")
                .add("id", user.getId())
                .add("name", user.getNom())
                .add("pseudo", user.getPseudo())
                .build();
        return addUser;
    }
    
    private JsonObject createAddMessage(Message message) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject sendMessage = provider.createObjectBuilder()
                .add("action", "sendMessage")
                .add("id", message.getId())
                .add("senderId", message.getSenderId())
                .add("text", message.getText())
                .build();
        return sendMessage;
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }

    private void sendToAllConnectedSessions(JsonObject message) {
        for (Object session : sessions) {
            sendToSession((Session) session, message);
        }
    }

    private void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(SessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}