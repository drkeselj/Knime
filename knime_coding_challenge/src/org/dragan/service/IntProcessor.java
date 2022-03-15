package org.dragan.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.dragan.model.IOperation;

public class IntProcessor extends AbstractProcessor<Integer> {

	public IntProcessor(List<IOperation> operations) {
		super(operations);
	}
	
	@Override
	protected Integer process(Integer i) {
		if (CollectionUtils.isEmpty(operations)) {
			return i;
		}
		for (IOperation o : operations) {
			i =  o.execute(i);
		}
		return i;
	}

	@Override
	protected Integer parse(String line) {
		return Integer.parseInt(line);
	}
}
