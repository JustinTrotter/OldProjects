/* 
*    Name:  Justin Trotter
*    Current Date:  3/7/2014
*    Sources Consulted: http://cslibrary.stanford.edu/110/BinaryTrees.html
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;
public class Driver {
	
	public static BinaryTree tree;
	public static Scanner sc;
	public static Map<String, String> map;
	
	public static void main (String [] args) throws FileNotFoundException{
		
		tree = new BinaryTree();
		
		map = new HashMap<String, String>();
		
		//Insert root node
		tree.insert("", ".");
		
		//Insert space translation
		map.put(" ", "");
		
		//Parse Morse.txt and insert into binary tree
		sc = new Scanner(new File("morse.txt"));
		while(sc.hasNext()){
			String line = sc.next();
			String letter = "";
			String morse = "";
			letter += line.charAt(0);
			for (int i = 0; i < line.length() - 1; i++){
				morse += line.charAt(i + 1);
			}	
			tree.insert(letter, morse);
			map.put(letter, morse);
			
			
			//
			//List<String> valSet = new ArrayList<String>();
			//valSet.add(morse);
			//map.put(letter, valSet);
		}
		
		// Parse Translate.txt and perform translations
		sc = new Scanner(new File("translate.txt"));
		
		ArrayList<String> stringList = new ArrayList<String>();
		String firstLine = sc.nextLine();
		StringTokenizer st = new StringTokenizer(firstLine);
		
		//Read First Line and Store two ints
		while (st.hasMoreTokens()) {
			stringList.add(st.nextToken());
	    }
		int E2M = Integer.parseInt(stringList.get(0));
		int M2E = Integer.parseInt(stringList.get(1));
		
		int countE2M = 0;
		int countM2E = 0;
		//Read Through Rest of Lines
		while(sc.hasNext()){
			
			//E2M First
			while(countE2M != E2M){
				if (countE2M == 0){
					System.out.println("English to Morse translations:");
					
				}
				String currentLine = sc.nextLine();
				System.out.println("English phrase: " + currentLine);
				//translate here
				String translatedLine = "";
				
				for(int i = 0; i < currentLine.length(); i++){
					String translatedChar = "";
					translatedChar += currentLine.charAt(i);

					translatedLine += map.get(translatedChar) + " ";

				}
				System.out.println(translatedLine);
				countE2M++;
			}
			System.out.println();
			while(countM2E != M2E){
				if(countM2E == 0){
					System.out.println("Morse to English translations:");
				}
				String currentLine = sc.nextLine();
				System.out.println("Morse phrase: " + currentLine);
				//translate here
				
				
				
				
				
				countM2E++;
			}
			
			
		}
		
		//System.out.print(map.get("E"));
//		tree.printTree();
//		System.out.print("Postorder: ");
//		tree.printPostorder();
//		
//		tree.printPaths();
		
//		for (Map.Entry<String, String> entry : map.entrySet()){
//			String key = entry.getKey();
//			String value = entry.getValue();
//			System.out.print("Key = " + key + " ");
//			System.out.println("Value = " + value);
//		}
	}
}