# Distributed-Systems: 04/2016

The goal of the project was to implement a parallel Fish and Shark application targeting on
multi-cores. The project was developed in Java starting from a simple sequential solution then
using Skandium framework was possible to build the parallel application. All tests were run on
an AMD Opteron (2.3GHz) processor with 12 cores and 12 contexts. More details fan be found at <b>Joao_Report.PDF</b>

# Requirements 
Skandium http://backus.di.unipi.it/~marcod/SPM1011/Skandium.pdf

# Manual for Fish and Sharks application

<b>How to compile</b> <br>
javac -cp /usr/local/Skandium/skandium-1.0b2.jar src/fish_and_sharks /*.java

<b>How to run</b> <br>
java -cp /usr/local/Skandium/skandium-1.0b2.jar: fish_and_sharks.Run #threads #matrixSize #generations. <br>
E.g: java -cp /usr/local/Skandium/skandium-1.0b2.jar: fish_and_sharks.Run 1 2000 50 <br>
The above example will run the application with 1 thread, matrix size of 2000 for 50 generations.

PS: In order to see the value of the original matrix in the constructor of the class Matrix you
need to uncomment the method printMatrix() which is on line 46.<br>
In order to see the value of the updated matrix after the given iteration, in the class Run you
need to uncomment the method matrix.printMatrix() which is on line 57.
