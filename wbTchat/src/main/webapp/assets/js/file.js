/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var userId;
window.onload = init;
var socket = new WebSocket("ws://192.168.8.106:47778/wbTchat/tchat_ws");
socket.onmessage = onMessage;

var tabColor = ["userj.jpg", "userv.jpg", "uservv.jpg", "uservx.jpg"];

function onMessage(event){
    var msg = JSON.parse(event.data);
    
    if(msg.action === "sendMessage"){
     
     if(msg.senderId === userId){
            printMyMsg(msg);
     }else{
            printOtherMsg(msg);
     }
    }
}

function printMyMsg(element){
    var container = document.getElementById("chat");
    
    var globalBox = document.createElement("li");
    globalBox.setAttribute("class", "right clearfix");
    container.appendChild(globalBox);
    
    var firstBox = document.createElement("span");
    firstBox.setAttribute("class", "chat-img pull-right");
    
    var img_firstBox = document.createElement("img");
    img_firstBox.setAttribute("src", "assets/img/userm.jpg");
    img_firstBox.setAttribute("alt", element.senderId);
    firstBox.appendChild(img_firstBox);
    
    globalBox.appendChild(firstBox);
    
    var secondBox = document.createElement("div");
    secondBox.setAttribute("class", "chat-body clearfix");
    
    var first_secondBox = document.createElement("div");
    first_secondBox.setAttribute("class", "header");
    
    var name = document.createElement("strong");
    name.setAttribute("class", "primary-font");
    name.innerHTML = "Moi";
    first_secondBox.appendChild(name);
    secondBox.appendChild(first_secondBox);
    
    var second_secondBox = document.createElement("p");
    second_secondBox.innerHTML = element.text;
    secondBox.appendChild(second_secondBox);
    
    globalBox.appendChild(secondBox);
}

function printOtherMsg(element){
    var container = document.getElementById("chat");
    
    var globalBox = document.createElement("li");
    globalBox.setAttribute("class", "left clearfix");
    container.appendChild(globalBox);
    
    var firstBox = document.createElement("span");
    firstBox.setAttribute("class", "chat-img pull-left");
    
    var img_firstBox = document.createElement("img");
    img_firstBox.setAttribute("src", "assets/img/" + randomColor());
    img_firstBox.setAttribute("alt", element.senderId);
    firstBox.appendChild(img_firstBox);
    
    globalBox.appendChild(firstBox);
    
    var secondBox = document.createElement("div");
    secondBox.setAttribute("class", "chat-body clearfix");
    
    var first_secondBox = document.createElement("div");
    first_secondBox.setAttribute("class", "header");
    
    var name = document.createElement("strong");
    name.setAttribute("class", "primary-font");
    name.innerHTML = element.senderId;
    first_secondBox.appendChild(name);
    secondBox.appendChild(first_secondBox);
    
    var second_secondBox = document.createElement("p");
    second_secondBox.innerHTML = element.text;
    secondBox.appendChild(second_secondBox);
    
    globalBox.appendChild(secondBox);
}

function sendMessage(text, pseudo){
    var object ={
        action: "sendMessage",
        text: text,
        senderId: pseudo
    };
    socket.send(JSON.stringify(object));
}

function formatMessage(){
    var message = document.getElementById("message").value.toString();
    var pseudo = document.getElementById("userId").value.toString();
    sendMessage(message, pseudo);
    
    var remove = document.getElementById("message");
    remove.value = "";
}

function sendForm(){
    var form = document.getElementById("submitForm");
   
    var pnom = form.elements["nom"].value;
    var pseudo = form.elements["pseudo"].value;
    var sexe = form.elements["sexe"].value;
    
    var object = {
        action : "addUser",
        nom : pnom,
        pseudo : pseudo,
        sexe : sexe
    };
    socket.send(JSON.stringify(object));
}

function init(){
    var value = location.href;
    if((value.search("WpServlet")) !== -1 ){
        userId = document.getElementById("userId").value.toString();
    }
    
    console.log(userId);
    console.log(value);
}

function randomColor(){
    var i = 0;
    
    if(i === 4){
        i = 0;  
    }
    
    if(i < 4){     
        return tabColor[i++];
    }
}
