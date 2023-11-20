/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.GeneticAlgorithm;
import src.Population;

/**
 *
 * @author sadan
 */
public class GeneticAlgorithmTest {
    GeneticAlgorithm GA;
	 double mutationRate;
    List<City> cities;

    public GeneticAlgorithm(double mutationRate, List<City> cities) {
        this.mutationRate = mutationRate;
        this.cities = cities;
    }

    public Tour crossover(Tour parent1, Tour parent2) {
        int size = parent1.cities.size();
        int startPos = new Random().nextInt(size);
        int endPos = new Random().nextInt(size - startPos) + startPos;

        List<City> childCities = new ArrayList<>(Collections.nCopies(size, null));

        for (int i = startPos; i <= endPos; i++) {
            childCities.set(i, parent1.cities.get(i));
        }

        int parent2Index = 0;
        for (int i = 0; i < size; i++) {
            if (!childCities.contains(parent2.cities.get(i))) {
                while (childCities.get(parent2Index) != null) {
                    parent2Index++;
                }
                childCities.set(parent2Index, parent2.cities.get(i));
            }
        }

        return new Tour(childCities);
    }

    public void mutate(Tour tour) {
        for (int i = 0; i < tour.cities.size(); i++) {
            if (Math.random() < mutationRate) {
                int swapIndex = new Random().nextInt(tour.cities.size());
                Collections.swap(tour.cities, i, swapIndex);
            }
        }
    }

    public Population evolve(Population population) {
        List<Tour> newTours = new ArrayList<>();

        // Elitism: Keep the best tour from the previous generation
        newTours.add(population.tours.get(0));

        // Crossover and Mutation to create the next generation
        while (newTours.size() < population.tours.size()) {
            Tour parent1 = selectParent(population);
            Tour parent2 = selectParent(population);

            Tour child = crossover(parent1, parent2);

            mutate(child);

            newTours.add(child);
        }

        return new Population(newTours);
    }

    private Tour selectParent(Population population) {
        // Tournament Selection: Randomly select a few tours and choose the best one
        int tournamentSize = 5;
        Population tournament = new Population(new ArrayList<>());

        for (int i = 0; i < tournamentSize; i++) {
            int randomIndex = new Random().nextInt(population.tours.size());
            tournament.tours.add(population.tours.get(randomIndex));
        }

        tournament.sortByFitness();
        return tournament.tours.get(0);
    }
}