package org.example.solvers;

import org.example.FileReader.FileAccess;
import org.example.FileReader.InputHandle;

public interface Solver {

    String getFileLocationPart1();

    default String getFileLocationPart2() {
        return getFileLocationPart1();
    }

    void solve1();

    void solve2();

    default InputHandle generateIO(String fileLocation) {
        return new FileAccess(fileLocation).getFileReader();
    }
}
