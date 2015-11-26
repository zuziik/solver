package cells;
import grids.Grid;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;


/**
 * Created by Zuzka on 23.11.2015.
 */
public class InputCell extends Cell {
    TextField text;

    public InputCell(Grid grid, int row, int col, Color color, TextField text) {
        super(grid, row, col, color);
        this.text = text;
        this.setupText();
    }

    private void setupText(){
        text.setFont(new Font(10));
        text.setText(this.getOptions().toString());
        text.setPrefWidth(this.getSize());
        text.setPrefHeight(this.getSize());
        text.setBackground(Background.EMPTY);
    }

    @Override
    public Cell getMyOutCell(){
        return this.getGrid().start.outGrid.getXY(this.getRow(), this.getCol());
    }

    @Override
    public void update(){
        this.getOptions().changeOptions(this.text.getText());
        //System.out.println(this.getOptions().toString());
    }

    @Override
    public String toString(){
        return "Input Cell at "+this.getRow()+", "+this.getCol();
    }

    public boolean onlyOption(){
        return this.getOptions().oneOption();
    }

    public Integer getOnlyOption(){
        return this.getOptions().getOnlyOption();
    }
}
