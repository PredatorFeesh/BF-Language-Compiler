import java.lang.Exception;
import java.lang.StringBuilder;
import java.lang.Character;
import java.lang.String;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Compiler{
	
	public static final ArrayList<String> commands = new ArrayList<String>();
	public static final int[] nodes = new int[500];
	public static int node = 250;

	public static void main(String[] args){
		if ( args.length != 0 )		
			new Compiler(args);
		else
			//assert false;
		return;	
	}
	public Compiler(String[] args){
		if ( setCommands(readFile(args[0])) ){
			parseCommands(0, Compiler.commands.size());
		}
	}
	public String readFile(String file){
	
	try{

		StringBuilder s = new StringBuilder("");
		String temp = "";				
		BufferedReader r = new BufferedReader(new FileReader(file));
		while( (temp = r.readLine()) != null ){ // O(n) = n
			s.append(temp);
		}
		r.close();
		return s.toString();
	}catch(Exception e){
		e.printStackTrace();
	}
	return null;
	}
	public boolean setCommands(String c){
		String[] sep = c.split("");
		int i  = 0;
		for ( String a : sep )
			if ( a.equals("<") || a.equals(">") || a.equals("+") || a.equals("-") || a.equals(".") || a.equals("[") || a.equals("]") ){
				Compiler.commands.add( a );
				i++;			
			}		
		
		return true;
	}
	public boolean parseCommands(int s, int e){
		String c = "";
		int t = 0;
		for ( int i = s; i <= e; i++ ) {
			if(Compiler.commands.size() == i)
				break;

			c = Compiler.commands.get(i);
			switch (c){
				case ">":
					Compiler.node++;
				break;
				case "<":
					Compiler.node--;
				break;
				case "+":
					Compiler.nodes[Compiler.node]++;
				break;
				case "-":
					Compiler.nodes[Compiler.node]--;
				break;
				case ".":
					System.out.println( Character.toString((char)Compiler.nodes[Compiler.node]));
				break;
				
			}
			if(c.equals("[")){
				for ( int k = i; k < Compiler.commands.size(); k++ ){
					if( Compiler.commands.get(k).equals("]") ){
						t = k;						
						break;
					}
				}
				parseCommands(i + 1, t);
				i = t  ;
			}
			if(c.equals("]") ){
				if( Compiler.nodes[Compiler.node] == 0 ){
					break;
				}
				else{
					parseCommands( s, e );
				}			
			}
			
		}
	return true;
	}
	
}
