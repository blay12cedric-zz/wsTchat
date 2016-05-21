<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <!--  This file has been downloaded from bootdey.com    @bootdey on twitter -->
        <!--  All snippets are MIT license http://bootdey.com/license -->
        <!-- 
            The codes are free, but we require linking to our web site.
            Why to Link?
            A true story: one girl didn't set a link and had no decent date for two years, and another guy set a link and got a top ranking in Google! 
            Where to Put the Link?
            home, about, credits... or in a good page that you want
            THANK YOU MY FRIEND!
        -->
        <title>Chat: Home</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/css/file.css" rel="stylesheet"/>
        <script type="text/javascript" src="http://gc.kis.scr.kaspersky-labs.com/1B74BD89-2A22-4B93-B451-1C9E1052A0EC/main.js" charset="UTF-8"></script></head>
    <body>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <div class="container bootstrap snippet">
            <div class="row">
                <div class="col-md-4 bg-white ">
                    <div class=" row border-bottom padding-sm" style="height: 40px; text-align: center; padding-top:15px;">
                        <strong>Acceuil Tchat</strong>
                    </div>

                    <!-- =============================================================== -->
                    <!-- member list -->
                    <ul class="friend-list">
                        <!-- class="active bounceInDown" permet de selectionner l'User dont le Tchat est actif-->
                        <li class="active bounceInDown">
                            <a href="#" class="clearfix">
                                <img src="assets/img/usert.jpg" alt="" class="img-circle">
                                <div class="friend-name">	
                                    <strong>Global Tchat</strong>
                                </div>
                            </a>
                        </li>                 
                    </ul>
                </div>

                <!--=========================================================-->
                <!-- selected chat -->
                <div class="col-md-8 bg-white ">
                    <div class="chat-message">
                        <ul class="chat" id="chat">
                            <!--Message Location-->
                            
                        </ul>
                    </div>

                    <div class="chat-box bg-white">
                        <div class="input-group">
                            <input class="form-control border no-shadow no-rounded" placeholder="Entrer votre message..." id="message">
                            <span class="input-group-btn">
                                <button class="btn btn-success no-rounded" type="button" onclick="formatMessage()">Envoyer</button>
                            </span>
                        </div><!-- /input-group -->	
                        <input type="hidden" id="userId" value="${pseudo}"/>
                    </div>            
                </div>        
            </div>
        </div>
        <script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="assets/js/file.js"></script>
    </body>
</html>