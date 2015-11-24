package cells;

import grids.Grid;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 * Created by Zuzka on 23.11.2015.
 */
public class InputCell extends Cell {
    TextField text;

    public InputCell(Grid grid, int row, int col, Color color) {
        super(grid, row, col, color);
        this.text = new TextField();
        text.setFont(new Font(10));
        text.setText(options.toString());
        text.setPrefWidth(this.getSize());
        text.setPrefHeight(this.getSize());
        //text.setBackground(Background.EMPTY);
        grid.getChildren().add(text);
    }


}
