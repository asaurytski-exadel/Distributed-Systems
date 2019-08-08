package fish_and_sharks;

import cl.niclabs.skandium.muscles.Split;

public class Splitter implements Split<Range, Range>{

	private int threads;
	public static double splitTime;

	public Splitter(int threads) {
		this.threads = threads;		
	}			

	@Override
	public Range[] split(Range range) throws Exception {

		long init = System.nanoTime() ;		
		int rowLength;
		int rowSize = (Matrix.getMatrix().length-2) / threads; 	//distribute number of rows to each processor
		int rem = (Matrix.getMatrix().length-2) % threads;	//interpret odd or even cases

		int rowStart = range.getStart();

		Range[] result = new Range[threads];

		for (int i = 0; i < threads; i++) {
			/*	if      then			else
			 * 	if rem is odd, on the first iteration add one more row */
			rowLength = (i < rem) ? rowSize + 1 : rowSize;
			Range r = new Range(rowStart, (rowStart + (rowLength -1)));
			
			//holds only interval of rows no data, since the application runs on shared memory
			result[i]= r;		
			rowStart += rowLength;
		}
		splitTime =splitTime + (System.nanoTime()  - init);	//save the time needed to split

		return result;
	}	
}
