package grids;

import cells.Cell;
import cells.InputCell;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import sample.Main;
import javafx.scene.control.TextField;


/**
 * Created by Zuzka on 23.11.2015.
 */
public class InputGrid extends Grid  {
    private InputCell[][] grid;
    private TextField[][] texts;

    public InputGrid(Main start){
        this.start = start;
        grid = new InputCell[9][9];
        texts = new TextField[9][9];
        createGrid(grid);
    }

    @Override
    public void createGrid(Cell[][] grid){
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                //parne boxy budu biele, neparne bledoruzove
                Color col = (((i / 3) + (j / 3)) % 2 == 0) ? Color.WHITE : Color.ANTIQUEWHITE;
                texts[i][j] = new TextField();
                grid[i][j] = new InputCell(this,i,j,col,texts[i][j]);
                texts[i][j].setText(grid[i][j].getOptions().toString());
                super.add(grid[i][j], i, j);
                super.add(texts[i][j],i,j);
            }
        }
        super.createGrid(grid);
    }

    public void setTexts(TextField[][] texts){
        this.texts = texts;
    }

    public TextField[][] getTexts(){
        return this.texts;
    }
}
