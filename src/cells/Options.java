package cells;

import java.util.TreeSet;

/**
 * Created by Zuzka on 23.11.2015.
 */
public class Options {
    private TreeSet<Integer> options;

    /** Konstruktor triedy Possibles vytvori mnozinu cisel 0-8 a reprezentuje, ze v policku mozu byt vsetky tieto cisla */
    public Options(){
        this.options = new TreeSet<>();
        for (int i=0; i<9; i++){
            options.add(i);
        }
    }

    public void changeOptions(String text){
        options.clear();
        for (Character c : text.toCharArray()){
            if ((c >= '1') && (c <= '9')){
                this.addOption(Integer.parseInt(c.toString())-1);
            }
        }
    }

    /** Funkcia prida hodnotu value do moznosti policka */
    public void addOption(int value){
        if ((value >= 0) && (value <= 8)){
            options.add(value);
        }
    }

    /** Funkcia odstrani hodnotu value z moznosti alebo neurobi nic, ak tam nie je */
    public void removeOption(int value){
        if (options.contains(value)){
            options.remove(value);
        }
    }

    /** Funkcia vrati mnozinu moznych hodnot pre policko */
    public TreeSet<Integer> getOptions(){
        return this.options;
    }

    /** Funkcia obnovi stav moznosti - naplni ich opat cislami 0-8 */
    public void refresh(){
        for (int i=0; i<9; i++){
            if (!options.contains(i)){
                options.add(i);
            }
        }
    }

    /** Funkcia vrati true, ak v policku moze byt jedine cislo */
    public boolean oneOption(){
        return options.size() == 1;
    }

    /** Funkcia vrati cislo, ktore musi byt v policku, ak ma policko len jedinu moznost alebo null, ak ich ma viac*/
    public Integer getOnlyOption(){
        if (this.oneOption()){
            return options.first();
        }
        else{
            return null;
        }
    }

    @Override
    public String toString(){
        StringBuffer s = new StringBuffer();
        for (int i : options){
            s.append(i+1);
        }
        return new String(s);
    }
}
