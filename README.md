# HITS-PageRank

#COMPILATION INSTRUCTIONS:

-Use the following commands to compile the java code.

-javac HITS.java

-javac PageRank.java


#EXECUTION INSTRUCTIONS:

-Use the following commands to run the compiled code.

-java HITS        NumberOfIterations   initialValue   InputFileName.txt

-java PageRank    NumberOfIterations   initialValue   InputFileName.txt


*For initialVAlue, you need to enter -2,-1 or 1 as input. The initial value 0 gives 'NaN' value.
*Exactly 3 arguments is needed. Violating this condition causes an error.


#Input File Details:

-The first row contains number of vertices followed by the number of edges and the subsequent rows are the directed edges from one vertex to another. 
-The input file should have exactly (number of edges + 1) rows.
-The number of vertices and edges given in the input file should be positive.
-The values of vertices and edges should also be positive. Negative numbers will throw exception.
-The values of edges representing webpages in input file should be numbered in the range 0 to (number of vertices - 1), otherwise the program will throw exception.
