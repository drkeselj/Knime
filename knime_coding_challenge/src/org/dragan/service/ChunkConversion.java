package org.dragan.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

public class ChunkConversion {
	
	private int startIndex;
	private int endIndex;
	
	private String inputFile;
	
	private Map<Integer, String> linesPerIndex;
		
	private IProcessor processor;
	
	public ChunkConversion(String processorId, String[] operations, String inputFile) {
		this.processor = ProcessorFactory.getInstance(processorId, operations);
		this.inputFile = inputFile;
	}
		
	public ChunkConversion(String processorId, String[] operations, String inputFile, int startIndex, int endIndex) {
		this.processor = ProcessorFactory.getInstance(processorId, operations);
		this.inputFile = inputFile;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	
	public int getStartIndex() {
		return startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public String getInputFile() {
		return inputFile;
	}
	public IProcessor getProcessor() {
		return processor;
	}
	public Map<Integer, String> getLinesPerIndex() {
		return linesPerIndex;
	}

	public void run() throws IOException {
		linesPerIndex = new HashMap<>();
		List<String> inLines = FileManager.readLines(inputFile, startIndex, endIndex);
		if (CollectionUtils.isEmpty(inLines)) {
			System.out.println("No lines loaded from " + inputFile + " " + startIndex + "-" + endIndex);
			return;
		}
		List<String> outLines = processor.processLines(inLines);
		int index = startIndex;
		for (String l : outLines) {
			linesPerIndex.put(index, l);
			index++;
		}
	}
}
