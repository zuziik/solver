package cnf_generators;

import java.util.ArrayList;

/**
 * Created by Zuzka on 30.11.2015.
 */
public class Untouchable extends VariantGenerator {
    public Untouchable(Generator wrapped){
        super(wrapped);
    }

    private void generateUntouchable(){
        for (int x = 0; x < 9; x++ ) {
            for (int y = 0; y < 9; y++ ) {
                if ( (x - 1 >= 0) && (y >= 0) ){
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int i = 0; i < 9; i++ ) {
                        list.add(-variableNo(x,y,i));
                        list.add(-variableNo(x-1,y-1,i));
                    }
                    formulas.add(list);
                }
                if ( (x - 1 >= 0) && (y + 1 <= 8) ) {
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int i = 0; i < 9; i++ ) {
                        list.add(-variableNo(x,y,i));
                        list.add(-variableNo(x-1,y+1,i));
                    }
                    formulas.add(list);
                }
            }
        }
    }

    @Override
    public void generateCNF(){
        super.generateCNF();
        generateUntouchable();
    }
}
