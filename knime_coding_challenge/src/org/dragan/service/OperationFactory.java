package org.dragan.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.dragan.model.CapitalizeOperation;
import org.dragan.model.IOperation;
import org.dragan.model.NegativeOperation;
import org.dragan.model.ReverseOperation;

public class OperationFactory {
	
	public static List<IOperation> getInstances(String[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.emptyList();
		}
		return Arrays.asList(ids).stream().map(id -> id.trim().toLowerCase()).map(id -> getInstance(id)).filter(o -> o != null).collect(Collectors.toList());
	}
	
	public static IOperation getInstance(String id) {
		switch(id) {
			case "capitalize": return new CapitalizeOperation();
			case "reverse":    return new ReverseOperation();
			case "neg":        return new NegativeOperation();
		}
		return null;
	}
	
}
