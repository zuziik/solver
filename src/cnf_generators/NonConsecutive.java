package cnf_generators;

import java.util.ArrayList;

/**
 * Created by Zuzka on 30.11.2015.
 */
public class NonConsecutive extends VariantGenerator {
    public NonConsecutive(Generator wrapped){
        super(wrapped);
    }

    private void generateNC(){
        for ( int x = 0; x < 9; x++ ) {
            for ( int y = 0; y < 0; y++ ) {
                if ( x - 1 >= 0 ) {
                    for ( int z1 = 0; z1 < 9; z1++ ) {
                        if ( z1 - 1 >= 0 ) {
                            ArrayList<Integer> list = new ArrayList<>();
                            list.add(-variableNo(x,y,z1));
                            list.add(-variableNo(x-1,y,z1-1));
                            formulas.add(list);
                        }
                        if ( z1 + 1 < 9 ) {
                            ArrayList<Integer> list = new ArrayList<>();
                            list.add(-variableNo(x,y,z1));
                            list.add(-variableNo(x-1,y,z1+1));
                            formulas.add(list);
                        }
                    }
                }
                if ( y - 1 >= 0 ) {
                    for ( int z1 = 0; z1 < 9; z1++ ) {
                        if ( z1 - 1 >= 0 ) {
                            ArrayList<Integer> list = new ArrayList<>();
                            list.add(-variableNo(x,y,z1));
                            list.add(-variableNo(x,y-1,z1-1));
                            formulas.add(list);
                        }
                        if ( z1 + 1 < 9 ) {
                            ArrayList<Integer> list = new ArrayList<>();
                            list.add(-variableNo(x,y,z1));
                            list.add(-variableNo(x,y-1,z1+1));
                            formulas.add(list);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void generateCNF(){
        super.generateCNF();
        generateNC();
    }
}
