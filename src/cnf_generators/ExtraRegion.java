package cnf_generators;

import cells.Cell;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Zuzka on 30.11.2015.
 */
public class ExtraRegion extends VariantGenerator {
    private ArrayList<ArrayList<Cell>> regions;

    public ExtraRegion(Generator wrapped, ArrayList<ArrayList<Cell>> regions){
        super(wrapped);
        this.regions = regions;
    }

    private void generateExtraRegion(){
        for (ArrayList<Cell> group : regions){
            ArrayList<ArrayList<Integer>> region = new ArrayList<>();
            ArrayList<Integer> list = new ArrayList<>();
            for (Cell cell : group) {
                region.add(new ArrayList<>(Arrays.asList(cell.getRow(), cell.getRow())));
            }
            generateRegion(region);
        }
    }

    @Override
    public void generateCNF(){
        super.generateCNF();
        generateExtraRegion();
    }
}
