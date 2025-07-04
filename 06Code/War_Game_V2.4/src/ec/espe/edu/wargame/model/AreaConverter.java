package ec.espe.edu.wargame.model;

import static ec.espe.edu.wargame.model.AreaUnit.ACRE;
import static ec.espe.edu.wargame.model.AreaUnit.HECTARE;
import static ec.espe.edu.wargame.model.AreaUnit.SQUARE_METER;
import static ec.espe.edu.wargame.model.AreaUnit.SQUARE_YARD;

/**
 *
 * @author bernardo suarez
 */
public class AreaConverter {

    public static double convert(double value, AreaUnit from, AreaUnit to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Las unidades no pueden ser nulas");
        }
        double valueInSquareMeters = convertToSquareMeters(value, from);
        return convertFromSquareMeters(valueInSquareMeters, to);
    }

    private static double convertToSquareMeters(double value, AreaUnit unit) {
        return convertArea(value, unit, true);
    }
    
    private static double convertFromSquareMeters(double value, AreaUnit unit) {
        return convertArea(value, unit, false);
    }
    
    private static double convertArea (double value, AreaUnit unit, boolean isToSquareMeters) {
    switch (unit) {
        case SQUARE_METER -> {
            return value;
        }
        case HECTARE -> {
            return isToSquareMeters ? value * 10000 : value / 10000;
        }
        case ACRE -> {
        return isToSquareMeters ? value * 4046.9 : value / 4046.9;
        } 
        case SQUARE_YARD -> {
        return isToSquareMeters ? value * 0.836 : value / 0.836;
        }
    default -> throw new IllegalArgumentException("Unidad no soportada " + unit);
        }
    }
}