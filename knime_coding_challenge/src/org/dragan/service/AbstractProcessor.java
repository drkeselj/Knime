package org.dragan.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.dragan.Statistics;
import org.dragan.model.IOperation;

public abstract class AbstractProcessor<T> implements IProcessor {
	
	protected List<IOperation> operations;
	protected Statistics statistics;

	public AbstractProcessor(List<IOperation> operations) {
		this.operations = operations;
		this.statistics = Statistics.getInstance();
	}

	public List<IOperation> getOperations() {
		return operations;
	}

	public void setOperations(List<IOperation> operations) {
		this.operations = operations;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
	
	@Override
	public List<String> processLines(List<String> lines) {
		if (CollectionUtils.isEmpty(lines)) {
			System.out.println("Nothing to process.");
			return Collections.emptyList();
		}
		List<String> outLines = lines.stream().map(l -> processLine(l)).map(el -> String.valueOf(el)).collect(Collectors.toList());
		return outLines;
	}
	
	protected T processLine(String line) {
		T item = parse(line);
		item = process(item);
		statistics.updateStatisticsWithLine(line);
		return item;
	}

	abstract protected T parse(String line);

	abstract protected T process(T element);
	
}
