package ies.pedro.components.dialogs;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import ies.pedro.utils.ImageWithPath;

public class DialogBackground extends Dialog<ImageWithPath> {
    private ImageWithPath image;
    private ImageWithPath selected;
    private ImageView imageViewSelected;
    private ArrayList<ImageWithPath> imagenes;

    public DialogBackground() {
        super();
        this.getDialogPane().setPrefSize(275, 250);

        this.imagenes = new ArrayList<>();
    }
    public void setImages(ArrayList<ImageWithPath> imagenes) {
        this.imagenes = imagenes;
    }

    public void init() {
        this.setTitle("Seleccionar fondo");
        ButtonType acceptButtonType = new ButtonType("Aceptar", ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(acceptButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
       
        for (int i = 0; i < this.imagenes.size(); i++) {
            ImageView imageView = new ImageView(this.imagenes.get(i).getImage());

            final int index = i;
            //evento al pulsar
            imageView.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2) {
                    selected = this.imagenes.get(index);

                    this.setResult(selected);
                    this.close();

                } else {
                    selected = selected = this.imagenes.get(index);
                    if (this.imageViewSelected != null) {
                        this.imageViewSelected.setEffect(null);
                    }
                    this.imageViewSelected = imageView;
                    DropShadow dropShadow = new DropShadow();
                    dropShadow.setRadius(10.0);
                    dropShadow.setOffsetX(10.0);
                    dropShadow.setOffsetY(10.0);
                    dropShadow.setColor(Color.color(0, 0.25, 0.25));
                    this.imageViewSelected.setEffect(dropShadow);
                }
            });

            grid.add(imageView, i % 2, i / 2);
        }

        this.getDialogPane().setContent(grid);
        this.setResultConverter(dialogButton -> {
            if (dialogButton == acceptButtonType) {

                return selected;
            }
            return null;
        });
    }
}
