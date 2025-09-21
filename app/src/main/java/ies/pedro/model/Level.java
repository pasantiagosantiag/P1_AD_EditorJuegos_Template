/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ies.pedro.model;


import ies.pedro.utils.Size;


import java.io.File;

import java.io.Serializable;

import java.util.*;


public class Level  {

    private Size size;


    private ArrayList<Block> elementos;

    private double time;

    private String sound;

    private String name;

    private String backgroundImage;

    public Level(String name) {
        this.name = name;
        this.elementos = new ArrayList<>();

    }

    public Level(String name, Size size) {
        this.name = name;
        this.size = size;
        this.elementos = new ArrayList<>();

    }

    public Level() {
        this.elementos = new ArrayList<>();
    }

    public void init() {
    }

    public void reset() {
        this.elementos.clear();
        this.backgroundImage = null;
        this.sound = null;
        this.init();
    }

    public void addElement(Block block) {
        this.elementos.add(block);
    }

    public void removeElement(Block block) {
        this.elementos.remove(block);
    }

    public boolean intersects(Block b) {
        for (Block block : this.elementos) {
            if (block.getRectangle().intersects(b.getRectangle())) {
                return true;
            }
        }
        return false;
    }

    public Optional<Block> getByPosition(int x, int y) {
        for (Block block : this.elementos) {
            if (block.getRectangle().contains(x, y)) {
                return Optional.of(block);
            }
        }
        return Optional.empty();
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }


    public List<Block> getElements() {

        return Collections.unmodifiableList(this.elementos);
    }


    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String path) {
        this.backgroundImage = path;
    }

    public void setBackgroundPosition(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }


    public static Level load(File file) throws Exception {
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        Level m = null;
        if (extension.equals("xml")) {
            //m = Level.loadXML(file);
        } else {
            if (extension.equals("json")) {
                // m = Level.loadJSON(file);
            } else {
                if (extension.equals("bin")) {
                    //m = Level.loadBin(file);
                } else {
                    throw new Exception("Extensión " + extension + " no permitida");
                }
            }
        }
        return m;
    }

    public static void save(Level level, File file) throws Exception {

        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        if (extension.equals("xml")) {
            //Level.saveXML(levels, file);
        } else {
            if (extension.equals("json")) {
                // Level.saveJSON(levels, file);
            } else {
                if (extension.equals("bin")) {
                    // Level.saveBin(levels, file);
                } else {
                    throw new Exception("Extensión " + extension + " no permitida");

                }
            }

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
