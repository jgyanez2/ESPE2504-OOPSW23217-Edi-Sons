/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.espe.edu.wargame.model;

/**
 *
 * @author bernardo suarez
 */
public class Area {
    public static double convert(double value, areaUnit from, areaUnit to) {
        double valueInSquareMeters = toSquareMetersConverter(value, from);
        return fromSquareMetersConverter(valueInSquareMeters, to);
    }
    public enum areaUnit {
    acre,
    hectare,
    squareMeter,
    squareYard,
}

    private static double toSquareMetersConverter(double value, areaUnit unit) {
        switch (unit) {
            case squareMeter -> {
                return value;
            }
            case hectare -> {
                return value * 10000;
            }
            case acre -> {
                return value * 4046.9;
            }
            case squareYard -> {
                return value * 0.836;
            }
            default -> throw new IllegalArgumentException("Unidad desconocida: " + unit);
        }
    }

    private static double fromSquareMetersConverter(double value, areaUnit unit) {
        switch (unit) {
            
            case squareMeter -> {
                return value;
            }
            case hectare -> {
                return value / 10000;
            }
            case acre -> {
                return value / 4046.9;
            }
            case squareYard -> {
                return value / 0.836;
            }
            default -> throw new IllegalArgumentException("Unknown unit: " + unit);
        }
    }
}