package org.dragan.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {
	
	public static int linesCount(String pathStr) throws IOException {
		Path path = Paths.get(pathStr);
		checkFile(path);
		return (int) Files.lines(path).count();
	}
	
	public static List<String> readLines(String pathStr) throws IOException {
		Path path = Paths.get(pathStr);
		checkFile(path);
		Stream<String> linesS = Files.lines(path);
		List<String> lines = linesS.collect(Collectors.toList());
		linesS.close();
		return lines;
	}
	
	public static List<String> readLines(String pathStr, int startIndex, int endIndex) throws IOException {
		Path path = Paths.get(pathStr);
		checkFile(path);
		Stream<String> linesS = Files.lines(path);
		List<String> lines = linesS.skip(startIndex).limit(endIndex - startIndex + 1).collect(Collectors.toList());
		linesS.close();
		return lines;
	}
			
	public static synchronized void writeLines(List<String> lines, String pathStr) throws IOException {
		Path path = Paths.get(pathStr);
		if (!Files.exists(path)) {
			path = Files.createFile(path);
		} else {
			if (Files.isDirectory(path)) {
				throw new IllegalArgumentException("File " + pathStr + " is directory!");
			}
		}
		Files.write(path, lines);
	}

	private static void checkFile(Path path) {
		if (!Files.exists(path)) {
			throw new IllegalArgumentException("File " + path + " does not exist!");
		}
		if (Files.isDirectory(path)) {
			throw new IllegalArgumentException("File " + path + " is directory!");
		}
	}
	
}
