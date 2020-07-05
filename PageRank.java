//Sepideh Nikookar 	CS610 8186 PrP


import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class PageRank8186{
	
	
	//Defining variables
	
	static double errorRate;
	static int numOfVertices;
	static int numOfEdges;
	static int[][] adjMatrix;
	static double initialValue;
	static int numOfIterations;
	static int intValue;
	static String file="Input.txt";
	static String input[];
	static final float d=0.85f;
	static int iter=0;
	static boolean convergenceCondition;
	static DecimalFormat decimalFormat = new DecimalFormat("0.0000000");
	
	
	//Reading from input file
  
    public static int[][] readinput8186(){
		
		
    	
		Scanner scanner;
		try {
			scanner = new Scanner(new File(file));
		
		
		numOfVertices=scanner.nextInt();
		numOfEdges=scanner.nextInt();


		adjMatrix = new int[numOfVertices][numOfVertices];
		
	        
		// filling the adjacency matrix with zero.
		
	     for(int i = 0; i < numOfVertices; i++) {
	    	 for(int j = 0; j < numOfVertices; j++) {
	        		adjMatrix[i][j] = 0;
	        	}
	        } 
		
	    // filing the adjacency Matrix with 1 for existing edges
	        
         
         for(int i=0; i<numOfEdges; i++){ 
	    	adjMatrix[scanner.nextInt()][scanner.nextInt()] = 1; 
	    	}

		scanner.close();
		

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		
		return adjMatrix;
	}


     //Checking error rate


	public static double errorRate8186(int numOfIteration) {
		if (numOfIteration == 0) {
			return (Math.pow(10.0,-5.0));  //For 0 iteration, set 10^(-5) to error rate
		}
		else if(numOfIteration<0){
			return (Math.pow(10.0, numOfIteration)); 
		}else {
			return (-1);
		}
	}
    
    
    
	//checking the convergence condition
	
    public static boolean isConverge8186(double[] currentValue, double[] prevValue, double errorRate, int numOfVertices, int iter){
    	double distance=-1;
    	for(int i = 0 ; i < numOfVertices; i++) {
    		if(Math.abs(prevValue[i] - currentValue[i])>distance) {
    			distance=Math.abs(prevValue[i] - currentValue[i]);
    		}
    	}
    	 	
    	
    	if(numOfIterations>0) {
    		if(iter<numOfIterations){
    			return false;

    		}else return true;


   		} else {
   			if(distance<errorRate) {
   				return true;
   		} else return false;
  
   	    }
    }
    
    
    
    //Printing outputs
    
    public static void printingOutput8186(double[] array, int iter, Boolean condition) {

    	String string="";
        if (iter==0) {
            string += "Base Case : " + iter + " :";

        }
        else {
            string += "Iter : " + iter + " :";
            if(numOfVertices>10 && condition){
                System.out.println("Iter : " + iter );
            }
        }
        for(int i=0;i<numOfVertices;i++){
        	string+="P["+i+"]="+ decimalFormat.format(array[i])+" ";
            if(numOfVertices>10 && condition){
                System.out.println("P ["+i+"] = "+ decimalFormat.format(array[i])+" ");
            }
        }
        if(numOfVertices<=10){
        	System.out.println(string);
        }
        else if(condition) {
            System.out.println();
        }
    }
    
    
    
    //Page Rank Algorithm
    
    public static void pageRank8186(){
    	
    	
    	 //initializing base case for authority and hub values
    	
    	double[] pageRank = new double[numOfVertices];

    	
        double[] prevPageRank = new double[numOfVertices];
        
        int[] outDegree = new int[numOfVertices];  
        
        
            
         for (int i=0; i<numOfVertices; i++){
        	 pageRank[i]=initialValue;
         }
         
         
         //Printing Base Case
            
    	   
         printingOutput8186(pageRank,iter,!convergenceCondition); 
         
        //calculating the out degree for each vertex
    	 
 	    for(int i=0;i<numOfVertices;i++) {
 	    	for(int j=0;j<numOfVertices;j++) {
 	    			outDegree[i]+=adjMatrix[i][j];
 	    			
 	    		}
 	    	}   
         
         errorRate= errorRate8186(numOfIterations);

 
        	 do {
        		 

        		 
          		 //Assigninh  valuePage Rank to previous one before updating
        		 
                 for(int i = 0; i < numOfVertices; i++) {
                     prevPageRank[i] = pageRank[i];
                     pageRank[i]=0.0;

                 }
	 
       
        	 
        	    //calculating Page Rank values
        	    
        	 
        	    for(int i=0;i<numOfVertices;i++) {
        	    	for(int j=0;j<numOfVertices;j++) {
        	    		if(adjMatrix[j][i]==1) {
        	    			double sum=0.0;
        	    			sum +=prevPageRank[j]/outDegree[j];
        	    			pageRank[i]=(1-d)/numOfVertices+d*sum;
        	    			}
        	    		}
        	    	}
        	    
        	    
        	    
        	convergenceCondition=isConverge8186(pageRank, prevPageRank, errorRate, numOfVertices, iter);  
            

        	//Increasing iteration number by one
        	
            iter++;
            
            
            //Printing output
            
            printingOutput8186(pageRank,iter,!convergenceCondition);
            
            

        	 }while(isConverge8186(pageRank, prevPageRank, errorRate, numOfVertices,iter) == false);
            

           	
       }     


  	public static void main(String[] args){
		
	

  		//getting initial value, number of iteration and file name as inputs from user
  		
  		
  		
  		input=args;
  		numOfIterations= Integer.parseInt(input[0]);
  		intValue= Integer.parseInt((input[1]));
  		file= input[2];
  		  
  		
  		
  		//reading input
  		
  		
  		readinput8186();

  		
  		
  		
  		if(numOfVertices>=10) {
  			intValue=-1;
  		}
  	    
  	    
  		if (intValue==1) {
  			initialValue=1.0;
  		}
  		else if(intValue == -1) {
  			initialValue = 1.0/numOfVertices;
  		}
  		else if(intValue == -2) {
  			initialValue = 1.0/Math.sqrt( numOfVertices );
  		}


  	      
  	      
  	      //Page Rank Algorithm
  	      
  	      
  	      pageRank8186();
  	      

  	}	
  	
  	
}





