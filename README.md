# test1012
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class City {
    int x, y;

    public City(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Tour {
    List<City> cities;
    double distance;

    public Tour(List<City> cities) {
        this.cities = cities;
        calculateDistance();
    }

    private void calculateDistance() {
        distance = 0;
        for (int i = 0; i < cities.size() - 1; i++) {
            distance += distanceBetween(cities.get(i), cities.get(i + 1));
        }
        distance += distanceBetween(cities.get(cities.size() - 1), cities.get(0));
    }

    private double distanceBetween(City city1, City city2) {
        return Math.sqrt(Math.pow(city2.x - city1.x, 2) + Math.pow(city2.y - city1.y, 2));
    }
}

class Population {
    List<Tour> tours;

    public Population(List<Tour> tours) {
        this.tours = tours;
        sortByFitness();
    }

    public void sortByFitness() {
        tours.sort((tour1, tour2) -> Double.compare(tour1.distance, tour2.distance));
    }
}

class GeneticAlgorithm {
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

public class Main {
    public static void main(String[] args) {
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
}
