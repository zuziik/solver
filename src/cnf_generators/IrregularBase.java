package cnf_generators;

import cells.Cell;
import grids.Sudoku;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Zuzka on 30.11.2015.
 */
public class IrregularBase extends BaseGenerator {
    ArrayList<ArrayList< Cell >> regions;

    public IrregularBase(Sudoku sudoku, ArrayList<ArrayList<Cell>> regions) {
        super(sudoku);
        this.regions = regions;
    }

    @Override
    public void generateBase() {
        for (ArrayList<Cell> group : regions){
            ArrayList<ArrayList<Integer>> region = new ArrayList<>();
            ArrayList<Integer> list = new ArrayList<>();
            for (Cell cell : group) {
                region.add(new ArrayList<>(Arrays.asList(cell.getRow(), cell.getRow())));
            }
            generateRegion(region);
        }
    }
}
