/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.util.List;

/**
 *
 * @author sadan
 */
public class Population {
     List<Tour> tours;

    public Population(List<Tour> tours) {
        this.tours = tours;
        sortByFitness();
    }

    public void sortByFitness() {
        tours.sort((tour1, tour2) -> Double.compare(tour1.distance, tour2.distance));
    }

    private static class Tour {

        public Tour() {
        }
    }
}

