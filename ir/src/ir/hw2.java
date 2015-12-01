package ir;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class hw2 {
	public static void main (String args []){
		
		//read file
		try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/simon-win-lenovo/workspace2/ir/src/ir/databig.txt"))) {
		    String line;
		  
		    //save first line and split it to setup matrix size
		    String firstline = br.readLine();
//		    System.out.println(firstline);
		    firstline = firstline.trim();
		    firstline =	firstline.replaceAll(" +"," ");
		    String[] splitLines = firstline.split(" ");
		    int firstedge = Integer.parseInt(splitLines[0]);
		    int firstnode = Integer.parseInt(splitLines[1]);
		    int matrixnbr1 [][] = new int[firstedge][firstedge];  
		   
		    //print out nbr of edges and nodes in matrix
//		    System.out.println(firstedge+"     "+ firstnode+"\n\n");
		    int edge=0,node=0;
		    //loop though every line of the data file
		    while ((line = br.readLine()) != null) {
		    	// print every line 
//		    	System.out.println(line);
		    	line = line.trim().replaceAll(" +"," ");
//		    	System.out.println(line);
		    	//split line into 2 ints
		    	splitLines = line.split(" ");
		    	try{
			    edge = Integer.parseInt(splitLines[0])-1;
			    node = Integer.parseInt(splitLines[1])-1;
		    	} catch (NumberFormatException e) {
		            br.readLine();
		         }
			    //print all edges and nodes
//			    System.out.println(edge+"     "+ node);
			    //set position in matrix to 1
			    matrixnbr1 [edge][node] = 1;
		    }
		    
		    //print out matrixnbr1
		  //also calculate probability
		    int sum[] = new int[firstedge];
		    int value;
		    for(int i=0;i<matrixnbr1.length;i++){
		    	System.out.println("");
				  	for(int j=0;j<matrixnbr1[i].length;j++){
				  		value = matrixnbr1[i][j];
				  		//if position is not 1 set it to 0
				  		if(value!=1){
				  			matrixnbr1[i][j] = 0;
				  		}else{
					  		sum[i]++;
				  		}
				  		System.out.print(matrixnbr1[i][j]+" ");
				  	}
			}
		    //print how many nodes each edge has
		    System.out.println("\n");
		    for(int i=0;i<sum.length;i++){
		    	System.out.print(sum[i]+"  ");
		    }
		    System.out.println("");
		    
		    double[][] matrixnbr2 = new double[firstedge][firstedge];
		    int value2;
		    float one = 1;
		    double p[] = new double[firstedge];
		    for(int i=0;i<matrixnbr1.length;i++){
		    	System.out.println("");
				  	for(int j=0;j<matrixnbr1[i].length;j++){
				  		value2 = matrixnbr1[i][j];  
				  		//value3 = matrixnbr1[i][j];
				  		//if position is not 1 set it to 0
				  		if(value2==1){
				  			matrixnbr2[i][j] = one/sum[i];
				  			//p[i] = matrixnbr2[i][j];
				  			if(sum[i] == 1){
				  				matrixnbr2[i][j] = 1;
				  			}
				  		}else if(value2==0){
				  			matrixnbr2[i][j] = 0;
				  		}
				  		System.out.print(matrixnbr2[i][j]+" ");
				  	}
		    }
		    

//		    power method create an array with node=index size 	1/nodes
		    double x[] = new double[firstedge];
		    double res[] = new double[firstedge];
		   for(int i=0;i<x.length;i++){
		    	x[i] = one / firstedge; 
		    }
		   
		   for(int f=0;f<1000;f++){
		    for(int i=0;i<matrixnbr2.length;i++){
//		    	System.out.println("");
				  	for(int j=0;j<matrixnbr2[i].length;j++){
				  		res[i] += x[j] * matrixnbr2[j][i];
//				  		System.out.println(x[i]+"  ");
				  	} 	
		    }
		    for(int q=0;q<x.length;q++){
		    	x[q] = res[q];
		    	res[q]=0;
//		    	System.out.println(x[q]+"  ");
		    }
		   }
		    
		    System.out.println("\n");
		    for(int i=0;i<x.length;i++){
		    	System.out.print(x[i]+"  ");
		    }
		    System.out.println("");
		    
		    // apply algorithm and print results
		    double[][] matrixnbr3 = new double[firstedge][firstedge]; 
		    double a = 0.85;
		    int N = firstedge;
		    //p = probability of arriving at a particular node 
		    //L = outbound links
		    double pageRank = 0;
		    //pageRank = (1 - a) / N; 
		  for(int i=0;i<matrixnbr1.length;i++){
			  	System.out.println("");
				//  for(int j=0;j<matrixnbr1[i].length;j++){
				  		//matrixnbr3[i][j]
					  pageRank = (1 - a) / N + a * (x[i]/sum[i]);
					  System.out.print(pageRank);
				  //			}	
				 }
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
