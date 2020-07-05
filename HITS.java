//Sepideh Nikookar 	CS610 8186 PrP


import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;



public class HITS8186 {
	
	//Defining variables
	
	static double errorRate;
	static int numOfVertices;
	static int numOfEdges;
	static int[][] adjMatrix;
	static double initialValue;
	static int numOfIterations;
	static int intValue; 
	static String file;
	static String input[];
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
	        
	    for(int i=0; i<numOfEdges; ++i) {
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
	
	
	//Scaling authority and hub values in each step
	
	
	public static double[] scaling8186(double[] vector, int numOfVertices) {
		    double factor;
        	double sum=0.0;
        	for(int i = 0; i < numOfVertices ; i++) {
        		sum += Math.pow(vector[i], 2.0);    
        	}

        	factor = Math.sqrt(sum); 
        	for(int i = 0; i < numOfVertices; i++) {
        		vector[i] = vector[i]/factor;
        	}
			return vector;	
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
    
    
    public static void printingOutput8186(double[] a, double[] h,int iter, Boolean condition) {

       	String string="";
        if (iter==0) {
            string += "Base Case : " + iter + " :";

        }
        else {
            string += "Iteration : " + iter + " :";
            if(numOfVertices>10 && condition){
                System.out.println("Iteration : " + iter );
            }
        }
        
        for(int i=0;i<numOfVertices;i++){
        	string+="A/H["+i+"]="+ decimalFormat.format(a[i])+"/"+decimalFormat.format(h[i])+" ";
            if(numOfVertices>10 && condition){
                System.out.println("A/H ["+i+"] = "+ decimalFormat.format(a[i])+"/"+decimalFormat.format(h[i])+" ");
            }
        }
        
        
        if(numOfVertices<=10){
        	System.out.println(string);
        }
        else if(condition) {
            System.out.println();
        }
    }
    
    
    

	
    //HITS Algorithm
    
    public static void hits8186(){
    	
    	 
    	//initializing base case for authority and hub values
    	
    	double[] authority = new double[numOfVertices];
        double[] hub = new double[numOfVertices];
    	
        double[] prevAuthority = new double[numOfVertices];
        double[] prevHub = new double[numOfVertices];
            
            
         for (int i=0; i<numOfVertices; i++)   {
        	 authority[i]=initialValue;
        	 hub[i]=initialValue;
         }
         
         
       //Printing Base Case
            
         printingOutput8186(authority,hub,iter,!convergenceCondition); 
         
         errorRate= errorRate8186(numOfIterations);


        	 do {
        		 
        		 
        		 //Assigninh authority and hub values to previous ones before updating

                 for(int i = 0; i < numOfVertices; i++) {
                     prevAuthority[i] = authority[i];
                     prevHub[i] = hub[i];
                     authority[i]=0.0;
                     hub[i]=0.0;
                 }


        	     //calculating authority values
        	 
        	    for(int i=0;i<numOfVertices;i++) {
        	    	for(int j=0;j<numOfVertices;j++) {
        	    		if(adjMatrix[j][i]==1) {
        	    			authority[i]+=prevHub[j];
        	    			}
        	    		}
        	    	}	 
       
        	 
        	 //calculating hub values
     
        	 for(int i=0;i<numOfVertices;i++) {
        		 for(int j=0;j<numOfVertices;j++) {
        			 if(adjMatrix[i][j]==1) {
        				 hub[i]+=authority[j];
        			 }
        		 }
        	 }	
            
        	 
        	//Scaling authority and hub values
            
           authority=scaling8186(authority,numOfVertices);
           hub=scaling8186(hub,numOfVertices);
           
           
           convergenceCondition=isConverge8186(authority, prevAuthority, errorRate, numOfVertices, iter) ||  isConverge8186(hub, prevHub, errorRate, numOfVertices,iter) ;
            	
           //Increasing iteration number by one
           
           iter++;
           
           //Printing Output
           
           printingOutput8186(authority,hub,iter,!convergenceCondition);
        
                    

        	 }while(isConverge8186(authority, prevAuthority, errorRate, numOfVertices, iter) == false ||  isConverge8186(hub, prevHub, errorRate, numOfVertices,iter) == false);
            
	
             
    }


	public static void main(String[] args){
		
		

	

	//getting initial value, number of iteration and file name as inputs from user.
	
	
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

	
	//HITS Algorithm

      hits8186();
        
	}
}