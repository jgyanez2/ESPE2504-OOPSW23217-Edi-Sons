package ec.edu.espe.wargame.model;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author Kevin Vaca Edison's OOP ESPE
 */
public final class AreaConverter {
    
    public enum Unit {
        SQUARE_METER("m²", "Square Meter"),
        HECTARE("ha", "Hectare"),
        ACRE("ac", "Acre"),
        SQUARE_YARD("yd²", "Square Yard");
        
        private final String symbol;
        private final String displayName;
        private final Map<Unit, Double> conversionMap = new EnumMap<>(Unit.class);
        
        static {
            // Initialize conversion matrix (toSqm, toHectare, toAcre, toSqYard)
            SQUARE_METER.init(1.0,         0.0001,      0.000247105, 1.19599);
            HECTARE.init(10000.0,         1.0,         2.47105,     11959.9);
            ACRE.init(4046.8564224,       0.404686,    1.0,         4840.0);
            SQUARE_YARD.init(0.83612736,  0.000083613, 0.000206612, 1.0);
        }
        
        Unit(String symbol, String displayName) {
            this.symbol = symbol;
            this.displayName = displayName;
        }
        
        private void init(double toSqm, double toHectare, double toAcre, double toSqYard) {
            conversionMap.put(SQUARE_METER, toSqm);
            conversionMap.put(HECTARE, toHectare);
            conversionMap.put(ACRE, toAcre);
            conversionMap.put(SQUARE_YARD, toSqYard);
        }
        
        /**
         * Convert value to target unit with single multiplication
         */
        public double convert(double value, Unit target) {
            return this == target ? value : value * conversionMap.get(target);
        }
        
        public String getSymbol() { return symbol; }
        @Override public String toString() { return displayName; }
    }
    
    // Conversion API ----------------------------------------------------------
    
    /**
     * Convert between any units with precision control
     * @param value Value to convert
     * @param from Source unit
     * @param to Target unit
     * @param decimals Decimal places (optional)
     * @return Converted value
     */
    public static double convert(double value, Unit from, Unit to, int... decimals) {
        validateUnits(from, to);
        double result = from.convert(value, to);
        return decimals.length > 0 ? round(result, decimals[0]) : result;
    }
    
    // Utility methods ---------------------------------------------------------
    
    private static void validateUnits(Unit from, Unit to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Conversion units cannot be null");
        }
    }
    
    private static double round(double value, int decimals) {
        double factor = Math.pow(10, decimals);
        return Math.round(value * factor) / factor;
    }
    
    /**
     * Get formatted conversion result
     * @param value Original value
     * @param from Source unit
     * @param to Target unit
     * @param decimals Decimal places
     * @return Formatted string (e.g., "10.00 m² = 2.47 ac")
     */
    public static String getFormattedResult(double value, Unit from, Unit to, int decimals) {
        double converted = convert(value, from, to, decimals);
        return String.format("%."+decimals+"f %s = %."+decimals+"f %s", 
               value, from.getSymbol(), converted, to.getSymbol());
    }
}