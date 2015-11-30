package cnf_generators;

import cells.Cell;

import java.util.ArrayList;

/**
 * Created by Zuzka on 30.11.2015.
 */
public class Even extends VariantGenerator {
    private ArrayList<Cell> cells;

    public Even( Generator wrapped, ArrayList<Cell> cells ) {
        super(wrapped);
        this.cells = cells;
    }

    private void generateEven(){
        for (Cell cell : cells){
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 1; i < 9; i += 2 ) {
                list.add(variableNo(cell.getRow(),cell.getCol(),i));
            }
            formulas.add(list);
        }
    }

    @Override
    public void generateCNF(){
        super.generateCNF();
        generateEven();
    }
}
