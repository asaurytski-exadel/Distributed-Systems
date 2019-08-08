package fish_and_sharks;

import cl.niclabs.skandium.muscles.Condition;

public class Cond implements Condition<Range>{

	@Override
	public boolean condition(Range arg0) throws Exception {
		if (Run.generations > 0) {
			return true;
		}
		else return false;
	}

}
