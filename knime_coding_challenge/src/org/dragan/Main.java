package org.dragan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.dragan.service.ChunkConversion;
import org.dragan.service.FileManager;

/**
 * Main class.
 * 
 * @author KNIME GmbH
 */
public class Main {

	public static void main(String[] args) throws IOException {
		String inputFile = args[1];
		String inputType = args[3];
		String[] operations = args[5].split(",");
		int threads = (args.length > 7) ? Integer.parseInt(args[7]) : 1;
		String outputFile =  (args.length > 9) ? args[9] : null;
		
		if (threads <= 0) {
			threads = 1;
		}
		
		Map<Integer, String> allOutLinesPerIndex = Collections.synchronizedMap(new HashMap<Integer, String>());
		
		ExecutorService threadPool = Executors.newFixedThreadPool(threads);
		CountDownLatch latch = new CountDownLatch(threads);
		int linesCount = FileManager.linesCount(inputFile);
		int partSize = (int) (linesCount / threads);
		
		//create separate threads for every chunk
		for (int threadIndex = 0; threadIndex < threads; threadIndex++) {
			int startIndex = threadIndex * partSize;
			int endIndex1 = startIndex + partSize - 1;
			int endIndex = (endIndex1 > linesCount - 1 || threadIndex == threads - 1) ? linesCount - 1 : endIndex1;
			Callable<Map<Integer, String>> task = new Callable<Map<Integer, String>>() {
				
				@Override
				public Map<Integer, String> call() throws Exception {
					ChunkConversion conversion = new ChunkConversion(inputType, operations, inputFile, startIndex, endIndex);
					conversion.run();
					latch.countDown();
					return conversion.getLinesPerIndex();
				}
				
			};
			Future<Map<Integer, String>> future = threadPool.submit(task);
			try {
			   Map<Integer, String> outLinesPerIndex = future.get();
			   allOutLinesPerIndex.putAll(outLinesPerIndex);
			} catch (Throwable ex) {
				threadPool.shutdownNow();
				ex.getCause().printStackTrace();
				System.exit(-1);
			} 
		}
		
		//wait for all threads to finish
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		//generate output lines
		List<String> outLines = new ArrayList<>();
		for (int lineIndex = 0; lineIndex < linesCount; lineIndex++) {
			String outLine = allOutLinesPerIndex.get(lineIndex); 
			if (outLine == null) {
				outLine = "";
			}
			outLines.add(outLine);
		}
		
		//write output lines
		if (!StringUtils.isEmpty(outputFile)) {
			FileManager.writeLines(outLines, outputFile);
		} else {
			outLines.stream().forEach(System.out::println);
		}
					
		// DO NOT CHANGE THE FOLLOWING LINES OF CODE
		System.out.println(String.format("Processed %d lines (%d of which were unique)", //
				Statistics.getInstance().getNoOfLinesRead(), //
				Statistics.getInstance().getNoOfUniqueLines()));
		System.exit(-1);
	}

}
