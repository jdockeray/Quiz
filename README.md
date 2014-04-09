Quiz
====
I have succesful launched the server with this

sudo java -Djava.security.policy=server.policy engine.ServerLauncher

and the client with this

sudo java -Djava.security.policy=client.policy quiz.ComputeAddQuestion

Just a note for myself! I think it might be a good idea to rearrange the packages so I have a seperate quiz, sever and client package, now that I think about the QuizBuilder class could probably just go into the Client class, But have a seperate quiz package holding the quiz functions, questions etc.
