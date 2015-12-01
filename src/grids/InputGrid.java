package grids;

import cells.Cell;
import cells.InputCell;
import javafx.scene.paint.Color;
import GUI.Main;
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
                //texts[i][j].setText(grid[i][j].getOptions().toString()); //toto robim dvakrat
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


    public String toFile(boolean withPencilmarks){
        if (withPencilmarks){
            return toFileWithPencilmarks();
        }
        else{
            return toFileWithGivens();
        }
    }

    private String toFileWithPencilmarks(){
        //TODO
        return null;
    }

    private String toFileWithGivens(){
        StringBuffer s = new StringBuffer();
        //typ vypisu
        s.append("GIVENS\n");
        for (int i=0; i<9; i++){
            //horny hruby okraj
            if (i % 3 == 0){
                for (int k=0; k<13; k++){
                    s.append("X");
                }
                s.append("\n");
            }
            for (int j=0; j<9; j++){
                if (j % 3 == 0){
                    s.append("X");
                }
                if (grid[i][j].oneOption()){
                    s.append(grid[i][j].getOnlyOption());
                }
                else{
                    s.append(" ");
                }
            }
            s.append("X");
            s.append("\n");
        }
        for (int k=0; k<13; k++){
            s.append("X");
        }
        return new String(s);
    }


}
