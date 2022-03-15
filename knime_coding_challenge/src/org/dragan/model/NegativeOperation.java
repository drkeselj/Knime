package org.dragan.model;

public class NegativeOperation extends AbstractOperation {
		
	@Override
	public int execute(int i) {
		return -i;
	}
	
	@Override
	public double execute(double d) {
		return -d;
	}
	
}
