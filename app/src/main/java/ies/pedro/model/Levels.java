package ies.pedro.model;








import java.io.*;

import java.util.ArrayList;

public class Levels  {


    private Level selected;


    private final ArrayList<Level> levels;



    public Levels() {
        this.levels = new ArrayList<>();
    }

    public void reset(){
        this.levels.clear(); // = new ArrayList<>();
    }
    public static void save(Levels levels, File file) throws Exception {

        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        if (extension.equals("xml")) {
            Levels.saveXML(levels, file);
        } else {
            if (extension.equals("json")) {
                Levels.saveJSON(levels, file);

            } else {
                if (extension.equals("bin")) {
                    Levels.saveBin(levels, file);
                } else {
                    throw new Exception("Exensión " + extension + " no permitida");

                }
            }

        }
    }
    public static Levels load(File file) throws  IOException, FileNotFoundException, ClassNotFoundException, Exception {
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        Levels m=null ;
        if (extension.equals("xml")) {
            m = Levels.loadXML(file);
        } else {
            if (extension.equals("json")) {
                m = Levels.loadJSON(file);

            } else {
                if (extension.equals("bin")) {
                    m = Levels.loadBin(file);
                } else {
                    throw new Exception("Exencsión " + extension + " no permitida");

                }
            }

        }
        return m;

    }
    private static Levels loadJSON(File file) throws FileNotFoundException, IOException {

        Levels m = null;

        return m;
    }

    private static Levels loadXML(File file) throws  IOException {

        Levels m = null;
        return m;
    }

    public static Levels loadBin(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
        Levels m = null;
        return m;
    }

    private static void saveJSON(Levels levels, File file) throws FileNotFoundException, UnsupportedEncodingException {


    }

    private static void saveXML(Levels levels, File file)  throws IOException {


    }

    public static void saveBin(Levels levels, File file) throws FileNotFoundException, IOException {

    }


    public void addLevel(Level level) {
        this.levels.add(level);
    }

    public Level getSelected(){
        return this.selected;
    }

    public ArrayList<Level> getLevels(){
        return this.levels;
    }
    public void setSelected(Level selected){
        this.selected = selected;
    }
    public void setSelected(int index){
        this.selected = this.levels.get(index);
    }
    public void setSelected(String name){
        this.selected = this.levels.stream().filter( l ->
                l.getName().equals(name)).findFirst().get();
    }
    public void resetSelected(){
        this.selected.reset();
    }
    public Level getLevelByIndex(int index){
        return this.levels.get(index);
    }
    public Level getLevelByName(String name){
        return this.levels.stream().filter( l->l.getName().equals(name)).findFirst().get();
    }

    public void removeLevel(String name){
        this.levels.removeIf(level -> level.getName().equals(name));
    }
}
