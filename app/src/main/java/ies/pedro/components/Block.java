/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ies.pedro.components;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ies.pedro.App;
import ies.pedro.utils.BlocksData;
import ies.pedro.utils.Rectangle2DAdapterJSON;
import ies.pedro.utils.Size;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.lang.reflect.Type;
/**
 *
 * @author Pedro
 */
public class Block {
    /*
    Para cargar el JSON
     */

    private ImageView imgv;
    private Pane panel;
    private boolean selected;
    private static Image img;
    private Size img_block_size;
    private static BlocksData blockData;
    private String type;
    private final ArrayList<IBlockListener> blocklisteners;
    private Runnable onClick;
    public static void loadBlocks(String path) throws URISyntaxException {
            //se tiene que cargar el json que se pase
            //tiene los rectangulso
            blockData = new BlocksData();

            img =  new Image(Block.class.getResource("/bloques.png").toURI().toString());


    }
   public static BlocksData getBlockData() {
        return blockData;
   }




    public Block(String group, String type) {
        this.type = type;
        this.selected = false;
        this.img_block_size = new Size((int) Block.blockData.blocks.get(group).get(type).getWidth(), (int) Block.blockData.blocks.get(group).get(type).getHeight());
        this.blocklisteners = new ArrayList<>();
        this.imgv = new ImageView(Block.getImage());
        this.panel = new Pane();
        this.panel.getChildren().add(imgv);
        var rectangulo = Block.getCoordenadaByName(group, type);
        this.imgv.setViewport(rectangulo);
        this.imgv.setFitWidth(rectangulo.getWidth() * App.SCALE);
        this.imgv.setFitHeight(rectangulo.getHeight() * App.SCALE);

        this.imgv.setOnMouseClicked(eh -> {
            if (this.onClick != null) {
                this.onClick.run();
            }
            this.blocklisteners.forEach(a -> {
                a.onClicked(this);
            });
        });
    }

    public static Image getImage() {
        return Block.img;
    }

    public static String[] getNamesBlocksByGroup(String group) {
        return Arrays.stream(Block.blockData.blocks.get(group).keySet().toArray()).toArray(String[]::new);
    }

    public static List<String> getGroups() {
        if(Block.blockData.blocks!=null)
        return Block.blockData.blocks.keySet().stream().toList(); //Arrays.stream((Block.imgs_coordenadas.keySet().toArray())).toList());//Arrays.stream(Block.imgs_coordenadas.keySet().toArray()).toArray(String[]::new);
        else
            return new ArrayList<>();
    }

    public static HashMap<String, Rectangle2D> getGroup(String group) {
        return Block.blockData.blocks.get(group);
    }

    public static Rectangle2D getCoordenadaByName(String group, String name) {
        return Block.blockData.blocks.get(group).get(name);
    }

    public static Rectangle2D getCoordenadaByName(String name) {
        Rectangle2D block = Block.blockData.blocks.values().stream()
                .filter(submapa -> submapa.containsKey(name))
                .map(submapa -> submapa.get(name))
                .findFirst()
                .orElse(null);
        return block;
    }


    public boolean isSelected() {
        return selected;
    }

    public void select() {
        this.selected = true;

        this.panel.setStyle("-fx-background-color: FF0000;");
    }

    public void unselect() {
        this.selected = false;
        this.panel.setStyle("");
    }


    public Node getComponent() {
        return this.panel;
    }

    public String getType() {

        return type;

    }

    public Size getBlockSize() {
        return this.img_block_size;
    }

    public void setTipo(String group, String type) {
        this.type = type;
        this.imgv.setViewport(Block.getCoordenadaByName(group, type));

    }

    public void addBlocklistener(IBlockListener blocklistener) {
        this.blocklisteners.add(blocklistener);
    }

    public void addClickListener(Runnable event) {
        this.onClick = event;
    }

    public void removeClickListener() {
        this.onClick = null;
    }

    public void removeBlocklistener(IBlockListener blocklistener) {
        this.blocklisteners.remove(blocklistener);
    }

}
