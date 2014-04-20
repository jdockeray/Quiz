Quiz
====
**Intro**
A Quiz game app:
This is basic multi player quiz game, that once the server has been started allows players to join a quiz or play and then find out who the winner is based on the highest score. If the highest scores are tied, then the winner is the first person to reach that score.

It also allows players to create a quiz, with as many questions and multi choice answers as they desire and to give that quiz a name. 

The quizes themeselves are saved to a text based database, but the players scores are only kept whilst the server is running. 

---
**Launching the Server and the Client**

To launch the server, navigate to the root folder and enter the following to launch the server

java -Djava.security.policy=server.policy server.engine.ServerLauncher

and the client with this..

java -Djava.security.policy=client.policy client.Client

and then follow the instructions in the terminal/console.

---
**Additional info**

All the methods that the client can call on the server are in the compute interface which is in the compute package.
