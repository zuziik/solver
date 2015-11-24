package grids;

import cells.Cell;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Zuzka on 23.11.2015.
 */
public abstract class Grid extends GridPane{
    public void createGrid(Cell[][] grid){
        /*Naplnime mriezku 9x9 polickami*/
//        for (int i=0; i<9; i++){
//            for (int j=0; j<9; j++){
//                //parne boxy budu biele, neparne bledoruzove
//                Color col = (((i / 3) + (j / 3)) % 2 == 0) ? Color.WHITE : Color.ANTIQUEWHITE;
//                grid[i][j] = new Cell(this,i,j,col);
//                super.add(grid[i][j],i,j);
//            }
//        }

        //TODO: farbenie stvorcov by sa malo robit inak pre irregularne sudoku

        /*Nastavime polickam susedov*/
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                Cell cell = grid[i][j];
                cell.setUp(grid[(i+8)%9][j]);
                cell.setDown(grid[(i+1)%9][j]);
                cell.setLeft(grid[i][(j+8)%9]);
                cell.setRight(grid[i][(j+1)%9]);
            }
        }



    }
}
