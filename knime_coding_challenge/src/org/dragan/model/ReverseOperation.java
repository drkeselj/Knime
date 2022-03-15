package org.dragan.model;

import org.apache.commons.lang3.StringUtils;

public class ReverseOperation extends AbstractOperation {
		
	@Override
	public int execute(int i) {
		String s = String.valueOf(i);
		s = StringUtils.reverse(s);
		return Integer.parseInt(s);
	}
	
	@Override
	public double execute(double d) {
		String s = String.valueOf(d);
		s = StringUtils.reverse(s);
		return Double.parseDouble(s);
	}
	
	@Override
	public String execute(String s) {
		return StringUtils.reverse(s);
	}

}
