package cnf_generators;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Zuzka on 30.11.2015.
 */
public class Diagonal extends VariantGenerator {
    public Diagonal(Generator wrapped){
        super(wrapped);
    }

    private void generateDiagonal(){
        ArrayList<ArrayList<Integer>> region = new ArrayList<>();
        for (int i=0; i<9; i++){
            region.add(new ArrayList<>(Arrays.asList(i, i)));
        }
        generateRegion(region);
    }

    @Override
    public void generateCNF(){
        super.generateCNF();
        generateDiagonal();
    }
}
