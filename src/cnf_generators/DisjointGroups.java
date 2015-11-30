package cnf_generators;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Zuzka on 30.11.2015.
 */
public class DisjointGroups extends VariantGenerator {

    public DisjointGroups(Generator wrapped){
        super(wrapped);
    }

    private void generateDisjointRegion(int i, int j) {
        ArrayList<ArrayList<Integer>> region = new ArrayList<>();
        for ( int x = 0; x < 3; x++ ) {
            for ( int y = 0; y < 3; y++ ) {
                region.add(new ArrayList<>(Arrays.asList(3 * x + i, 3 * y + j)));
            }
        }
        generateRegion(region);
    }

    private void generateDisjointGroups() {
        for ( int i = 0; i < 3; i++ ) {
            for ( int j = 0; j < 3; j++ ) {
                generateDisjointRegion(i,j);
            }
        }
    }

    @Override
    public void generateCNF(){
        super.generateCNF();
        generateDisjointGroups();
    }

}
