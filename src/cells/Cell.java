package cells;

import grids.Grid;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Zuzka on 23.11.2015.
 */
public class Cell extends Rectangle {
    private int row;
    private int col;
    private Grid grid;
    Options options;
    private final int size = 40;
    private Color fill;
    private Color outline = Color.BLACK;
    /** Referencie na susedne policka, aby sa dal nastavit pohyb sipkami - sprava sa ako toroid*/
    private Cell up, down, left, right;


    public Cell(Grid grid, int row, int col, Color color){
        this.fill = color;
        super.setWidth(size);
        super.setHeight(size);
        super.setFill(fill);
        super.setStroke(outline);
        super.setStrokeWidth(0.5);
        this.grid = grid;
        this.row = row;
        this.col = col;
        this.options = new Options();
    }

    /** Funkcia vrati sirku strany policka*/
    public int getSize(){
        return size;
    }

    /** Funkcia vrati (toroidneho) horneho suseda policka */
    public Cell getUp(){
        return this.up;
    }

    /** Funkcia nastavi policku horneho suseda */
    public void setUp(Cell up){
        this.up = up;
    }

    /** Funkcia vrati (toroidneho) dolneho suseda policka */
    public Cell getDown(){
        return this.down;
    }

    /** Funkcia nastavi policku dolneho suseda */
    public void setDown(Cell down){
        this.down = down;
    }
    /** Funkcia vrati (toroidneho) laveho suseda policka */
    public Cell getLeft(){
        return this.left;
    }

    /** Funkcia nastavi policku laveho suseda */
    public void setLeft(Cell left){
        this.left = left;
    }

    /** Funkcia vrati (toroidneho) praveho suseda policka */
    public Cell getRight(){
        return this.right;
    }

    /** Funkcia nastavi policku praveho suseda */
    public void setRight(Cell right){
        this.right = right;
    }

    /**
     * Funkcia vrati xovu poziciu policka v mriezke - cislo stlpca (0-8)
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Funkcia vrati ypsilonovu poziciu policka v mriezke - cislo riadku (0-8)
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Funkcia nastavi policku mriezku
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    /**
     * Funkcia vrati odkaz na mriezku, ktorej patri policko
     */
    public Grid getGrid() {
        return this.grid;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    /**
     * Funkcia vrati zoznam vpisiek policka vo formate Options
     */
    public Options getOptions() {
        return this.options;
    }

    /**
     * Funkcia prida cislo i do moznosti policka Cell
     *
     * @param i
     */
    public void addOption(int i) {
        this.options.addOption(i);
    }

    /**
     * Funkcia vrati true, ak ma policko prave jednu moznost
     */
    public boolean oneOption() {
        return this.options.oneOption();
    }
}
