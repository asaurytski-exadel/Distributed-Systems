package fish_and_sharks;


import cl.niclabs.skandium.muscles.Merge;

public class Merger implements Merge<Range, Range>{

	public static double mergeTime;
	
	@Override
	public Range merge(Range[] range) throws Exception {
		
		long init = System.nanoTime() ;		
		Run.generations--;					//decrease the num of generations
		Matrix.swap();						//swap the matrixes
		mergeTime =mergeTime + (System.nanoTime()  - init);	//save time needed to merge
		return new Range(1, Run.matrixSize);
	}
}
