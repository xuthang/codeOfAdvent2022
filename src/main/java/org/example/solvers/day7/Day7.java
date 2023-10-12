package org.example.solvers.day7;

import org.example.FileReader.InputHandle;
import org.example.solvers.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Day7 implements Solver {
    @Override
    public String getFileLocationPart1() {
        return "7/input1.txt";
    }

    @Override
    public String getFileLocationPart2() {
        return null;
    }


    @Override
    public void solve1() {
        var input = generateIO(getFileLocationPart1());
        List<String> commands = parseInput(input);

        Dir root = getTree(commands);
        int total = accumulateDirSize(root, (int size) -> {
            return size <= 100000;
        });

        System.out.println(total);
    }


    @Override
    public void solve2() {
        var input = generateIO(getFileLocationPart1());
        List<String> commands = parseInput(input);

        Dir root = getTree(commands);
        int bestSize = Integer.MAX_VALUE;

        int disSize = 70000000, reqSpace = 30000000;
        int usedSpace = root.getDirSize();
        int freeSpace = 70000000 - usedSpace;
        int goal = reqSpace - freeSpace;
        int bestDirSize = findBestDir(root, Integer.MAX_VALUE, goal);

        System.out.println(bestDirSize);
    }


    private int findBestDir(Dir cur, int curBestSize, int goal)
    {
        int size = cur.getDirSize();
        if(size >= goal && size < curBestSize)
            curBestSize = size;

        for(Dir d : cur.getSons())
            curBestSize = findBestDir(d, curBestSize, goal);

        return curBestSize;
    }

    private int accumulateDirSize(Dir cur, IAcceptableSize callBack) {
        int total = callBack.satisfies(cur.getDirSize()) ? cur.getDirSize() : 0;
        for (Dir son : cur.getSons()) {
            total += accumulateDirSize(son, callBack);
        }
        return total;
    }

    private Dir changeDirectory(Dir cur, String command) {
        String dirName = command.split(" ")[2];
        if (dirName.equals("..")) {
            return cur.getParent();
        }

        Optional<Dir> nextDir = cur.getSon(dirName);
        if (!nextDir.isEmpty()) {
            return nextDir.get();
        }

        Dir tmp = new Dir(dirName, cur);
        cur.addDir(tmp);
        return tmp;
    }

    /**
     * @param idx index of the ls command
     */
    private int parsels(List<String> commands, int idx, Dir cur) {
        for (int i = idx + 1; i < commands.size(); i++) {
            String s = commands.get(i);
            if (s.charAt(0) == '$')
                return i;
            if (s.split(" ")[0].equals("dir")) {
                String dirName = s.split(" ")[1];
                if (cur.getSon(dirName).isEmpty()) {
                    cur.addDir(new Dir(dirName, cur));
                }
            } else {
                String fileName = s.split(" ")[1];
                int fileSize = Integer.parseInt(s.split(" ")[0]);

                if (cur.getFile(fileName).isEmpty()) {
                    cur.addFile(new File(fileName, fileSize));
                }
            }
        }

        return commands.size();
    }

    private Dir getTree(List<String> commands) {
        Dir root = Dir.createRoot("");
        Dir cur = root;
        int idx = 0;
        while (idx < commands.size()) {
            String s = commands.get(idx);
            if (s.charAt(0) != '$') {
                throw new RuntimeException("found " + s);
            }


            String command = s.split(" ")[1];
            if (command.equals("cd")) {
                cur = changeDirectory(cur, s);
                idx++;
                continue;
            }

            if (command.equals("ls")) {
                idx = parsels(commands, idx, cur);
            }
        }

        return root;
    }


    private List<String> parseInput(InputHandle input) {
        List<String> ret = new ArrayList<>();
        while (input.hasNextLine()) {
            String s = input.getNextLine();
            if (s.isEmpty()) {
                break;
            }
            ret.add(s);
        }
        return ret;
    }

}
