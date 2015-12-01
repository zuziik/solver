package grids;

import cells.Cell;
import cells.OutputCell;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import GUI.Main;

/**
 * Created by Zuzka on 23.11.2015.
 */
public class OutputGrid extends Grid {
    OutputCell[][] grid;
    Label[][] texts;

    public OutputGrid(Main start){
        this.start = start;
        grid = new OutputCell[9][9];
        texts = new Label[9][9];
        createGrid(grid);
    }

    @Override
    public void createGrid(Cell[][] grid){
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                //parne boxy budu biele, neparne bledoruzove
                Color col = (((i / 3) + (j / 3)) % 2 == 0) ? Color.WHITE : Color.WHITESMOKE;
                texts[i][j] = new Label();
                grid[i][j] = new OutputCell(this,i,j,col,texts[i][j]);
                //texts[i][j].setText(grid[i][j].getMyInCell().getOptions().toString());
                super.add(grid[i][j], i, j);
                super.add(texts[i][j],i,j);
            }
        }
        super.createGrid(grid);
    }

    public void setTexts(Label[][] texts){
        this.texts = texts;
    }

    public Label[][] getTexts(){
        return this.texts;
    }

}
