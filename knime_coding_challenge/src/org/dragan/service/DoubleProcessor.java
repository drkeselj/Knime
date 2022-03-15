package org.dragan.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.dragan.model.IOperation;

public class DoubleProcessor extends AbstractProcessor<Double> {

	public DoubleProcessor(List<IOperation> operations) {
		super(operations);
	}
	
	@Override
	protected Double process(Double d) {
		if (CollectionUtils.isEmpty(operations)) {
			return d;
		}
		for (IOperation o : operations) {
			d =  o.execute(d);
		}
		return d;
	}

	@Override
	protected Double parse(String line) {
		return Double.parseDouble(line);
	}
}
