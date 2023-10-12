package org.example.solvers.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Dir {
    private String name;
    private Dir parent;
    private List<Dir> sons;
    private List<File> filesInDir;
    private int dirSize = 0;

    public static Dir createRoot(String name)
    {
        return new Dir(name, null);
    }

    public Dir(String name, Dir parent) {
        this.name = name;
        this.parent = parent;
        this.sons = new ArrayList<>();
        this.filesInDir = new ArrayList<>();
    }

    public void addDir(Dir dir) {
        if(!getSon(dir.getName()).isEmpty())
            throw new RuntimeException("dir " + dir + " already exists in " + name);
        sons.add(dir);
    }

    private void updateSize(int difference)
    {
        dirSize+= difference;
        if(getParent() == null)
            return;

        getParent().updateSize(difference);
    }
    public void addFile(File file) {
        filesInDir.add(file);
        updateSize(file.getSize());
    }

    public String getName() {
        return name;
    }

    public Dir getParent() {
        return parent;
    }
    public List<Dir> getSons() {
        return sons;
    }

    public Optional<Dir> getSon(String name)
    {
        for(Dir d : getSons())
            if(d.getName().equals(name))
                return Optional.of(d);
        return Optional.empty();
    }

    public Optional<File> getFile(String name)
    {
        for(File f : getFilesInDir()){
            if(f.getName().equals(name))
                return Optional.of(f);
        }
        return Optional.empty();
    }

    public List<File> getFilesInDir() {
        return filesInDir;
    }

    public int getDirSize() {
        return dirSize;
    }
}
