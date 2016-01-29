package cm.java.util;

public class ParseUtil {

    public static class ParseException extends Exception {

        public ParseException(String s) {
            super(s);
        }
    }

    /**
     * Returns the input if it is a JSON-permissible value; throws otherwise.
     */
    public static double checkDouble(double d) throws ParseException {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            throw new ParseException("Forbidden numeric value: " + d);
        }
        return d;
    }

    public static Boolean toBoolean(Object value) {
        if (value instanceof Boolean) {
            return (Boolean) value;
        } else if (value instanceof String) {
            String stringValue = (String) value;
            if ("true".equalsIgnoreCase(stringValue)) {
                return true;
            } else if ("false".equalsIgnoreCase(stringValue)) {
                return false;
            }
        }
        return null;
    }

    public static Double toDouble(Object value) {
        if (value instanceof Double) {
            return (Double) value;
        } else if (value instanceof Number) {
            return ((Number) value).doubleValue();
        } else if (value instanceof String) {
            try {
                return Double.valueOf((String) value);
            } catch (NumberFormatException ignored) {
            }
        }
        return null;
    }

    public static Float toFloat(Object value) {
        if (value instanceof Float) {
            return (Float) value;
        } else if (value instanceof Number) {
            return ((Number) value).floatValue();
        } else if (value instanceof String) {
            try {
                return Float.valueOf((String) value);
            } catch (NumberFormatException ignored) {
            }
        }
        return null;
    }

    public static Integer toInteger(Object value) {
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof Number) {
            return ((Number) value).intValue();
        } else if (value instanceof String) {
            try {
                return (int) Double.parseDouble((String) value);
            } catch (NumberFormatException ignored) {
            }
        }
        return null;
    }

    public static Long toLong(Object value) {
        if (value instanceof Long) {
            return (Long) value;
        } else if (value instanceof Number) {
            return ((Number) value).longValue();
        } else if (value instanceof String) {
            try {
                return (long) Double.parseDouble((String) value);
            } catch (NumberFormatException ignored) {
            }
        }
        return null;
    }

    public static String toString(Object value) {
        if (value instanceof String) {
            return (String) value;
        } else if (value != null) {
            return String.valueOf(value);
        }
        return null;
    }

    public static ParseException typeMismatch(Object indexOrName, Object actual,
                                              String requiredType) throws ParseException {
        if (actual == null) {
            throw new ParseException("Value at " + indexOrName + " is null.");
        } else {
            throw new ParseException("Value " + actual + " at " + indexOrName
                    + " of type " + actual.getClass().getName()
                    + " cannot be converted to " + requiredType);
        }
    }

    public static ParseException typeMismatch(Object actual, String requiredType)
            throws ParseException {
        if (actual == null) {
            throw new ParseException("Value is null.");
        } else {
            throw new ParseException("Value " + actual
                    + " of type " + actual.getClass().getName()
                    + " cannot be converted to " + requiredType);
        }
    }
}
