package grids;

import cells.Cell;
import cells.InputCell;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import sample.Main;

/**
 * Created by Zuzka on 23.11.2015.
 */
public class InputGrid extends Grid  {
    Main start;
    Cell[][] grid;

    public InputGrid(Main start){
        this.start = start;
        grid = new InputCell[9][9];
        createGrid(grid);
    }

    @Override
    public void createGrid(Cell[][] grid){
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                //parne boxy budu biele, neparne bledoruzove
                Color col = (((i / 3) + (j / 3)) % 2 == 0) ? Color.WHITE : Color.ANTIQUEWHITE;
                grid[i][j] = new InputCell(this,i,j,col);
                super.add(grid[i][j],i,j);
            }
        }
        super.createGrid(grid);
    }
}
