package org.dragan.model;

import org.apache.commons.lang3.StringUtils;

public class CapitalizeOperation extends AbstractOperation {
		
	@Override
	public String execute(String s) {
		return StringUtils.capitalize(s);
	}

}
