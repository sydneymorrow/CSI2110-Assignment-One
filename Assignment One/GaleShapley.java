/* imports */
import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.Scanner;
import java.io.*; 
import java.util.*;
import java.util.PriorityQueue;
import java.lang.Comparable;
/**
* Name: Sydney Morrow
* Student Num: 300119030
**/
public class GaleShapley{

	/**
	*	Constructors 
	**/ 

	private ArrayStack Sue;
	private int[] students;
	private int[] employers;
	private int[][] A;
	private PriorityQueue[] PQ;
	private String[] studentNames;
	private String[] employersNames;
	private String[] matches;


	/**
	*	Main Method tests files given in assignment pdf
	**/ 
	
	public static void main(String[] args){
		GaleShapley test = new GaleShapley();
		Scanner askFileName = new Scanner(System.in);
		System.out.println("Enter File Name:");
		String filename = askFileName.nextLine(); //Retrieves file name from user
		test.initialize(filename);
		test.execute();
		try{
			test.save(filename);
		}catch (IOException e){
			//Throws IOException
			System.out.println("Exception Found.");
		}
		
	}	
	/**
	*	Initialize data types used in Gale-Shapley Algorithim
	*	@Exception FileNotFoundException if file is not found
	**/ 
	public void initialize(String filename){
		String fileRead = ""; //Empty String to store file content
		int n = 0; //int n holds number of students/employers
		try {
			File file = new File(filename);
			Scanner reader = new Scanner(file);
			String data = reader.nextLine();
			n = Integer.parseInt(data); //This gets the number of lines in the file
			while(reader.hasNextLine()){
				data = reader.nextLine();
				fileRead = fileRead + "\n" + data; 
			}
				reader.close();
		//Catch thrown if file is not found
		} catch (FileNotFoundException e){
				System.out.println("File Not Found");
		}
			
		Sue = new ArrayStack(n);
		students = new int[n];
		employers = new int[n];
		A = new int[n][n];
		
		//Create array students and employers 
		for(int i = 0; i < n; i++){
			students[i] = -1;
			employers[i] = -1;
		}
		//Create array of student and employer names and matches
		String[] placeHolder = fileRead.split("\\r?\\n"); //Each new line in fileread becomes an element in String[] placeHolder
		employersNames = new String[n]; 
		studentNames = new String[n];
		matches = new String[n];
		for(int i = 0; i < n; i++){
			employersNames[i] = placeHolder[i+1];
			studentNames[i] = placeHolder[(i+1)+n];
			matches[i] = placeHolder[(i+1)+n+n];
		}
		String[][] matches2 = new String[n][n*2]; //Creates 2D array to hold line number and also number element
		for(int i = 0; i < matches.length; i++){
			matches[i] = matches[i].replaceAll("[^\\d.]", " "); //Replaces all non integer elements with space
			String[] willHold = matches[i].split("\\D+"); //Splits at every " " and adds to array String[] willHold
			matches2[i] = willHold;
		}
		//Creating stack sue
		for(int i = 0; i < n; i++){
			Sue.push(i);
		}
		//Creating 2D array A
		for(int i = 0; i < n; i++){
			int k = 0;
			for(int j = 0; j < matches2[i].length; j++){
				if(j%2 != 0){
					String hold = matches2[i][j];
					int hold2 = Integer.parseInt(String.valueOf(hold));
					A[k][i] = hold2;
					k = k + 1;
				}
			}
		}
		
		//Creating Priority Queue
		PQ = new PriorityQueue[n];
		PriorityQueue<Match> queue;
		Match e;
		for(int i = 0; i < n; i++){
		 queue = new PriorityQueue<>();
		 int k = 0;
		 for(int j = 0; j < matches2[i].length; j++){
				if(j%2 == 0){
					String hold = matches2[i][j];
					int x = Integer.parseInt(String.valueOf(hold));
					e = new Match(x,k);
					queue.add(e);
					k = k + 1;
				}
			}
			PQ[i] = queue;
		}
	}
		
		
	/**
	*	Preforms execute function described in assignment pdf
	*	@return String of matches
	**/ 
	public String execute(){
		while(!Sue.isEmpty()){
			//e = Sue.pop()
			Object p = Sue.pop();
			int e = Integer.parseInt(p.toString());
			//s = PQ[e].removeMin()
			Object s = PQ[e].poll();
			Match s2 = (Match) s;
			int s3 = s2.getY();
			//e' = students[s]
			int emp = students[s3];
			if(students[s3] == -1){
				students[s3] = e;
				employers[e] = s3;
			}else if(A[s3][e] < A[s3][emp]){
				students[s3] = e;
				employers[e] = s3;
				employers[emp] = -1;
				Sue.push(emp);
			}else{
				Sue.push(e);
			}
		}
		String line = "";
		for(int i = 0; i < students.length; i++){
			int stud = employers[i];
			int emp = students[i];
			String lineToAdd = "Match " + i + ": " + employersNames[i] + " - " + studentNames[stud];
			line = line + "\n" + lineToAdd;
		}
		return line;
	}
		
		/**
		*	Saves string of matches to file: matches_filename.txt
		**/
		public void save(String filename) throws IOException{
			filename = filename.replaceAll(".txt", "");
			try{
			String toPrint = execute();
			System.out.println(toPrint);
			File file = new File("matches_" + filename + ".txt");
			 BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(toPrint);
			writer.close();
			} catch (IOException e){
				System.out.println("Exception caught");
			}
			
		}	
}