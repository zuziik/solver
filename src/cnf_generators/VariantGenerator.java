package cnf_generators;

/**
 * Created by Zuzka on 30.11.2015.
 */
public abstract class VariantGenerator extends Generator {
    Generator wrapped;

    public VariantGenerator( Generator wrapped ) {
        this.wrapped = wrapped;
    }

    public void generateCNF(){
        wrapped.generateCNF();
        this.formulas.addAll(wrapped.getCNFFormulas());
    }

}
