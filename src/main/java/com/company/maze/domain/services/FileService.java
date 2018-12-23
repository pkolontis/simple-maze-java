/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.services;

import com.company.maze.domain.ex.FileNotReadException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a service that performs file operations such as reading
 * 
 * @author Petros Kolontis <petros.kolontis@gmail.com>
 */
public class FileService {
    
    private static final Logger log = LoggerFactory.getLogger(FileService.class);
    
    /**
     * Reads the file given
     * 
     * @param file the file to read
     * @return an array of lines
     * 
     * @throws IllegalArgumentException if file argument is null
     * @throws FileNotReadException if read process is failed
     */
    public static String[] readFile(String file) {
        if (file == null) {
            throw new IllegalArgumentException("file argument cannot be null");
        }
        try {
            InputStream inputStream = FileService.class.getClassLoader().getResourceAsStream(file);
            Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines();
            String[] linesStr = lines.toArray(String[]::new);
            log.debug("File {} was successfully read", file);
            
            return linesStr;
        } catch(Exception ex) {
            log.error("Failed to read file : {}", file);
            throw new FileNotReadException(String.format("Failed to read file : %s", file), ex);
        }
    }
}
