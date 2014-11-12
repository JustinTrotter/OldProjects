/* 
*    Name:  Justin Trotter
*    Current Date:  3/20/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Driver2{
	
	public static void main(String [] arg) throws IOException{
		ArrayList<String> list = new ArrayList<String>();
		String currentLine;
		
		BufferedReader br = new BufferedReader(new FileReader("poem.txt"));
		while((currentLine = br.readLine()) != null){
			String[] tokens = new String[currentLine.split(" ").length];
			tokens = currentLine.split(" ");
				
			for(int i = 0; i < tokens.length; i++)
				list.add(tokens[i]);
		}
		br.close();

		for(int i = 0; i < list.size(); i++)
			System.out.println(list.get(i));	
		
		File file = new File("myWork.txt");
			
		if(!file.exists())
			file.createNewFile();
			
		FileWriter fw = new FileWriter(file.getAbsolutePath());
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(int i = 0; i < list.size(); i++)
			bw.write(list.get(i) + " ");

		bw.close();	
	}	
}