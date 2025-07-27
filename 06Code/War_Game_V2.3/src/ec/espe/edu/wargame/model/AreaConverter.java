/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.espe.edu.wargame.model;

/**
 *
 * @author bernardo suarez
 */
public class AreaConverter {

    public static double convert(double value, AreaUnit from, AreaUnit to) {
        double valueInSquareMeters = toSquareMeters(value, from);
        return fromSquareMeters(valueInSquareMeters, to);
    }

    private static double toSquareMeters(double value, AreaUnit unit) {
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

    private static double fromSquareMeters(double value, AreaUnit unit) {
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
