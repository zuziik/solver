package cnf_generators;

/**
 * Created by Zuzka on 30.11.2015.
 */
public class Antiknight extends VariantGenerator {
    public Antiknight(Generator wrapped){
        super(wrapped);
    }

    private void generateAK(){
        //TODO
    }

    @Override
    public void generateCNF(){
        super.generateCNF();
        generateAK();
    }
}
