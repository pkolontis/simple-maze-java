/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.services.test;

import com.company.maze.domain.ex.FileNotReadException;
import com.company.maze.domain.services.FileService;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Represents a class used for testing of FileService 
 * methods such as readFile
 * 
 * @author Petros Kolontis <petros.kolontis@gmail.com>
 */
public class FileServiceTest {
    
    private static final String SAMPLE_FILES_PATH = "com/company/maze/test/samplefiles/";
    
    public FileServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testReadFile() {
        Path path = Paths.get(SAMPLE_FILES_PATH, "maze-valid.txt");
        FileService.readFile(path.toString());
    }
    
    @Test(expected = FileNotReadException.class)
    public void testReadFileNotExistingFile() {
        Path path = Paths.get(SAMPLE_FILES_PATH, "maze-not-existing.txt");
        FileService.readFile(path.toString());
    }
}
