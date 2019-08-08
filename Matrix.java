package fish_and_sharks;

import java.util.Random;

import cl.niclabs.skandium.muscles.Execute;

/*
 * 							PROJECT DESCRIPTION
 * 
 * 	An initial population of fishes and sharks evolves according to the following set of rules:
	1. Fish breeding age starts at 2, shark breeding age starts at 3
	2. Fish live up to 10, then they die, sharks live up to 20, then they die
	3. An empty cell with >= 4 fish (shark) neighbors and >= 3 of them in breeding age and < 4 shark (fish) neighbours 
	is filled by a new fish (shark) individual with age 1
	4. A fish in a cell
		dies if >= 5 neighbors are sharks (Shark food)
		dies if >= 8 neighbors are fishes (overpopulation)
		otherwise its age increases
	5. A shark in a cell
		dies if >= 6 neighbors are sharks and =0 neighbours are fishes (starvation)
		dies with 1/32 probability by random causes
		otherwise its age increases
	The application computes the evolution of an initial population is a mesh of N * N cells (25%
	sharks, 50% fishes and 25% empty cells) for a number M of iterations. */


public class Matrix{

	//to print fish, sharks and empty in different colors
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_BLUE = "\u001B[34m";

	private static String matrix[][];			//read only matrix
	private int size;					//size of the matrix
	private int x,y;					//indexes of the matrix
	private Random random;					//random class
	public static String newMatrix[][];			//write only matrix
	private static int len1, len2;				//variables for update ghost positions

	public Matrix(int size) {
		this.size = size;
		matrix = new String[size+2][size+2]; 
		newMatrix = new String[size+2][size+2]; 
		random = new Random();
		len1 = matrix.length - 1;
		len2 = matrix.length - 2;
		play();
	//	printMatrix();
	}

	
	public static void swap() {
		cornerPoints(); 		//update ghost corner points
		String[][] temp = matrix;
		matrix = newMatrix;
		newMatrix = temp;
	}
	
	public static String[][] getMatrix() {
		return matrix;
	}
	

	public void generateRandomIndex() { 					//method to generate random indexes of the matrix
		x = random.nextInt(size)+1;
		y = random.nextInt(size)+1;
	}

	public void generateMatrix(){
		int noFish	= (size * size) / 2;				//50% of population
		int noSharks 	= noFish / 2;					//25% of population
		int noEmpty	= (size * size) - (noFish + noSharks);		//25% of population

		generateFishAndSharks(noFish, 9, "F_");				//generateFishes		
		generateFishAndSharks(noSharks, 19, "S_");			//generateSharks			
		generateFishAndSharks(noEmpty, 1, "Empty");			//generate empty		
	}	

	public void generateFishAndSharks(int no, int rand, String type){	//generate Fish, Sharks	and Empty	
		int age;
		//no: number of Fish, Shark and Empty to generate
		for (int i = 1; i <= no;) {					//rand:	random age fishes (1 - 10) sharks (1 - 20)
			age = random.nextInt(rand) + 1;				//type: F_ / S_
			generateRandomIndex();

			if (matrix[x][y] == null) {				//check if matrix at index x, y is null
				i++;

				if (!type.equals("Empty")) matrix[x][y] = type + Integer.toString(age);	//add fish or shark to the matrix
				else matrix[x][y] = "Empty";						//add empty to the matrix
			}		
		}		
	}

	public static void generateGhost(){				//Ghost method to represent interaction on a mesh

		for (int i = 1; i < matrix.length; i++) {
			matrix[i][len1] = matrix[i][1]; 		//rightmost ghost column
			matrix[i][0] = matrix[i][len2]; 		//leftmost ghost column
		}

		for (int j = 1; j < matrix.length; j++) {
			matrix[len1][j] = matrix[1][j];			//southmost ghost column
			matrix[0][j] = matrix[len2][j];			//northmost ghost column
		}

		// ghost corner points of original matrix
		matrix[0][0] = matrix[len2][len2];	//first index of 1st row
            	matrix[len1][0] = matrix[1][len2];	//first index of last row 
            	matrix[0][len1] = matrix[len2][1];	//last index of 1st row 
            	matrix[len1][len1] = matrix[1][1];	//last index of last row
	}

	public void play(){
		generateMatrix();
		generateGhost();
		
	}

	//print matrix 
	public static void printMatrix() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) 
				if (matrix[i][j].startsWith("S")) 		//print red for shark
					System.out.print(ANSI_RED +matrix[i][j] + "\t" + ANSI_RESET);
				else {
					if (matrix[i][j].startsWith("F")) 	//print blue for fish
						System.out.print(ANSI_BLUE +matrix[i][j] + "\t" + ANSI_RESET);
					else 
						System.out.print(matrix[i][j] + "\t" );
				}		
			System.out.println();
		}
	}

	// Used for the ghost corner points of new matrix
	public static void cornerPoints(){        

            newMatrix[0][0] = newMatrix[len2][len2];	//first index of 1st row
            newMatrix[len1][0] = newMatrix[1][len2];	//first index of last row 
            newMatrix[0][len1] = newMatrix[len2][1];	//last index of 1st row 
            newMatrix[len1][len1] = newMatrix[1][1];	//last index of last row 
	}

}
