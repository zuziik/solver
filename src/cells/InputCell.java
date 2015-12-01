package cells;
import GUI.Mode;
import grids.Grid;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;


/**
 * Trieda reprezentuje policko vstupnej mriezky.
 */
public class InputCell extends Cell {
    TextField text; //textove pole na zadavanie moznosti pre policko

    /** Konstruktor dostane mriezku, poziciu policka, jeho farbu a textove pole na zadavanie moznosti */
    public InputCell(Grid grid, int row, int col, Color color, TextField text) {
        super(grid, row, col, color);

        //oproti Cell nastavujeme este vstupne textove pole a naplnime ho cislami z Options
        this.text = text;
        this.setupText();
    }

    /** Funkcia nastavi vstupne policko moznostami z Options a nastavi jeho vyzor*/
    private void setupText(){
        text.setFont(new Font(10));
        //text.setText(this.getOptions().toString());
        text.setText("");
        //velkostou bude textove pole presne suhlasit s polickom, pricom bude mat priesvitne pozadie, teda neprekryje farbu
        text.setPrefWidth(this.getSize());
        text.setPrefHeight(this.getSize());
        text.setBackground(Background.EMPTY);
    }

    /** Funkcia vrati prislusne vystupne policko k tomuto policku (policko z vystupnej mriezky na tej istej pozicii)*/
    @Override
    public Cell getMyOutCell(){
        return this.getGrid().start.outGrid.getXY(this.getRow(), this.getCol());
    }

    /** Funkcia zmeni Options policka podla obsahu textoveho pola*/
    @Override
    public void update(){
        if (this.getGrid().start.getMode().equals(Mode.GIVENS)){
            if (this.text.getText().equals("")){
                this.getOptions().refresh();
            }
            else{
                this.getOptions().changeOptions(this.text.getText());
            }
        }
        else{
            this.getOptions().changeOptions(this.text.getText());
        }
    }

    /** Funkcia vrati true, ak ma policko len jedinu moznost*/
    public boolean onlyOption(){
        return this.getOptions().oneOption();
    }

    /** Funkcia vrati jedine cislo, ktore moze byt v policku, aj tam moze byt len jedina moznost - inak vracia null*/
    public Integer getOnlyOption(){
        return this.getOptions().getOnlyOption();
    }

    /** Funkcia vrati textovu reprezentaciu policka v tvare Input Cell at {x, y} : {options}*/
    @Override
    public String toString(){
        return "Input Cell at "+this.getRow()+", "+this.getCol()+": "+this.getOptions().toString();
    }

    @Override
    public void showPencilmarks(){
        this.update();
        this.text.setText(this.getOptions().toString());
    }

    @Override
    public void hidePencilmarks(){
        this.update();
        if (this.getOptions().oneOption()){
            this.text.setText(this.getOptions().getOnlyOption().toString());
        }
        else{
            this.text.setText("");
        }
    }
}
