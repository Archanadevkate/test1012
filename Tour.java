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
public class Tour {
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

