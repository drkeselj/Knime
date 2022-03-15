package org.dragan.service;

import java.util.List;
import org.dragan.model.IOperation;

public class ProcessorFactory {
	
	public static IProcessor getInstance(String id, String[] operationIds) {
		List<IOperation> operations = OperationFactory.getInstances(operationIds);
		id = id.trim().toLowerCase();
		switch(id) {
			case "string": return new StringProcessor(operations);
			case "int":    return new IntProcessor(operations);
			case "double": return new DoubleProcessor(operations);
		}
		return null;
	}
}
