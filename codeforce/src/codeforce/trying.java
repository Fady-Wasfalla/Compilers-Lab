package codeforce;

import java.util.*;  


public class trying {
	

	public static void main(String[]args) {
		int total;
		Scanner sc= new Scanner(System.in);    //System.in is a standard input stream  
		System.out.println("Enter Numbers n m a");  
		int n = sc.nextInt();  
		int m = sc.nextInt();  
		int a = sc.nextInt();
		total = (n%a>0) ? (n/a)+1 : n/a;
		total = (m%a>0) ? total * ((m/a)+1) : total* (m/a);
		
		System.out.println("Total: "+total);
	}
}
