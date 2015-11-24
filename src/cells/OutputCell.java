package cells;

import grids.Grid;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Created by Zuzka on 24.11.2015.
 */
public class OutputCell extends Cell {
    Label text;

    public OutputCell(Grid grid, int row, int col, Color color, Label text) {
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
    public Cell getMyInCell(){
        return this.getGrid().start.inGrid.getXY(this.getRow(), this.getCol());
    }

    @Override
    public void update(){
        System.out.println(this.getMyInCell().getOptions().toString());
        this.setOptions(this.getMyInCell().getOptions());
        this.text.setText(this.getOptions().toString());
    }

    @Override
    public String toString(){
        return "Output Cell at "+this.getRow()+", "+this.getCol();
    }
}
