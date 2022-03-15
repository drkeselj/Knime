package org.dragan.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.dragan.model.IOperation;

public class StringProcessor extends AbstractProcessor<String> {

	public StringProcessor(List<IOperation> operations) {
		super(operations);
	}
	
	@Override
	protected String process(String s) {
		if (CollectionUtils.isEmpty(operations)) {
			return s;
		}
		for (IOperation o : operations) {
			s =  o.execute(s);
		}
		return s;
	}

	@Override
	protected String parse(String line) {
		return line;
	}
}
