package org.example.solvers.day3;

public interface Converter {
    static int getItemPriority(char c) {
        if (c >= 'a' && c <= 'z')
            return Character.getNumericValue(c) - Character.getNumericValue('a') + 1;
        return Character.getNumericValue(c) - Character.getNumericValue('A')+ 27;
    }
}
