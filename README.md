# EvaluationEngine

Run by cd'ing into the root directory and running `java -cp ./bin/ eval.Main`

This project is my submission project for APCS at Stuyvesant Highschool.

Much of the code is an adapted version of [the same thing but in C++](https://gist.github.com/Sam-Belliveau/3c90f0f05368f0e5dbb0c9a0b37e1025) which I also made. The C++ version of this ended up being really difficult
because there is a lot of circular refrences and without a garbage collector, can lead to memory leaks.

The only resource I used when making the origional was the example implementation in [Reverse Polish Notation Wikipedia](https://en.wikipedia.org/wiki/Reverse_Polish_notation#Postfix_evaluation_algorithm)

This project takes it a bit further by using an inheritance structure for the operators. This makes it really easy to add functions into the engine, and in the C++ version, users were able to add and remove variables.

This project was made over the course of 1-2 days, but that can be attributed to my prior knowlage on the subject.
