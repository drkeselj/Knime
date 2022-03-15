package org.dragan.model;

/**
 * All operation classes should extend this class.
 * @author Kesa
 */
public abstract class AbstractOperation implements IOperation {
	
	/**
	 * @param i
	 * 		int value as input value for this operation
	 * @result 
	 * 		int value after applying this operation on a given int number.
	 * @throws UnsupportedOperationException 
	 * 		If int value is not supported by this operation.
	 */
	@Override
	public int execute(int i) {
		throw new UnsupportedOperationException(getClass().getName() +" [int] operation is not supported!");
	}
	
	/**
	 * @param d
	 * 		double value as input value for this operation
	 * @result 
	 * 		double value after applying this operation on a given double number.
	 * @throws UnsupportedOperationException 
	 * 		If double value is not supported by this operation.
	 */
	@Override
	public double execute(double d) {
		throw new UnsupportedOperationException(getClass().getName() +" [double] operation is not supported!");
	}
	
	/**
	 * @param s
	 * 		String as input value for this operation
	 * @result 
	 * 		String after applying this operation on a given String.
	 * @throws UnsupportedOperationException 
	 * 		If String is not supported by this operation.
	 */
	@Override
	public String execute(String s) {
		throw new UnsupportedOperationException(getClass().getName() +" [String] operation is not supported!");
	}
	
}
