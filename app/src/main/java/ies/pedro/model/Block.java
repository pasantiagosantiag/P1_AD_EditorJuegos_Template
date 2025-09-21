/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ies.pedro.model;


import javafx.geometry.Rectangle2D;



public class Block  {

    private String type;

    private  Rectangle2D rect;
    public Block(){
        this.type=null;
    }
    public Block(String type,Rectangle2D rectangle){
        this.type=type;
        this.rect=rectangle;
    }


    public Rectangle2D getRectangle() {
        return rect;
    }
    public void setRectangle(Rectangle2D rectangle) {
        this.rect=rectangle;
    }
    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type=type;
    }
    public boolean isEmpty(){
        return this.type==null;
    }
    @Override
    public String toString(){
        return this.type+" "+this.rect;
    }

}
