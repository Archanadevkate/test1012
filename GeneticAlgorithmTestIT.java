/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author sadan
 */
public class GeneticAlgorithmTestIT {
    
    public GeneticAlgorithmTestIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of setUp method, of class GeneticAlgorithmTest.
     */
    @Test
    public void testSetUp() throws Exception {
        System.out.println("setUp");
        GeneticAlgorithmTest instance = new GeneticAlgorithmTest();
        instance.setUp();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tearDown method, of class GeneticAlgorithmTest.
     */
    @Test
    public void testTearDown() throws Exception {
        System.out.println("tearDown");
        GeneticAlgorithmTest instance = new GeneticAlgorithmTest();
        instance.tearDown();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
