/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Main {
    
    List<City> cities = new ArrayList<>();
        cities.add(new City(0, 0));
        cities.add(new City(1, 2));
        cities.add(new City(3, 1));

        GeneticAlgorithm ga = new GeneticAlgorithm(0.01, cities);

        // Initialize the population with random tours
        List<Tour> initialTours = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<City> shuffledCities = new ArrayList<>(cities);
            Collections.shuffle(shuffledCities);
            initialTours.add(new Tour(shuffledCities));
        }

        Population initialPopulation = new Population(initialTours);

        // Evolve the population for a certain number of generations
        int numGenerations = 100;
        Population finalPopulation = initialPopulation;
        for (int generation = 0; generation < numGenerations; generation++) {
            finalPopulation = ga.evolve(finalPopulation);
        }

        // Access the best tour and its distance
        Tour bestTour = finalPopulation.tours.get(0);
        System.out.println("Best Tour Order: " + bestTour.cities);
        System.out.println("Best Tour Distance: " + bestTour.distance);
    }
