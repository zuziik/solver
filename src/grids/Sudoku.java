package grids;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Zuzka on 17.11.2015.
 */
public class Sudoku {
    HashMap<Variants,Boolean> types;
    // pre kazdy riadok, stlpec a cislo 0-8 budeme udrziavat, ci to cislo moze byt na prislusnej pozicii
    ArrayList<ArrayList<HashMap<Integer,Boolean>>> possibles;

    public Sudoku(HashMap<Variants,Boolean> types){
        this.types = types;
        initContent();
    }

    public Sudoku(){
        this.types = new HashMap<Variants,Boolean>();
        for (Variants s : Variants.values()){
            types.put(s,false);
        }
        initContent();
    }

    public ArrayList<ArrayList<HashMap<Integer,Boolean>>> getPossibles(){
        return this.possibles;
    }

    /** Funkcia inicializuje sudoku - v kazdom policku mozu byt vsetky cisla 0-8 */
    private void initContent(){
        this.possibles = new ArrayList<>(9);
        for (int i=0; i<9; i++) this.possibles.add(new ArrayList<>(9));
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                this.possibles.get(i).add(new HashMap<>());
            }
        }
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                for (int k=0; k<9; k++){
                    this.possibles.get(i).get(j).put(k,true);
                }
            }
        }
    }

    /** Funkcia zapise cislo z na poziciu x, y a zrusi vsetky ostatne moznosti*/
    public void setXYZ(int xx, int yy, int zz){
        int x = xx-1;
        int y = yy-1;
        int z = zz-1;
        HashMap<Integer,Boolean> map = possibles.get(x).get(y);
        for (Integer i : map.keySet()){
            if (i == z){
                map.replace(i,false,true);
            }
            else{
                map.replace(i,true,false);
            }
        }
    }

    /** Funkcia odstrani cislo z z moznosti pre policko x, y*/
    public void remove(int x, int y, int z){
        possibles.get(x).get(y).replace(z,true,false);
    }

    /** Funkcia prida cislo z do moznosti pre policko x, y*/
    public void add(int x, int y, int z){
        possibles.get(x).get(y).replace(z,false,true);
    }

    /** Funkcia prekonvertuje stav sudoku (ktore policko moze obsahovat ktore cisla) do jedneho pola 729 booleanov */
    public Boolean[] gridToArray(){
        Boolean array[] = new Boolean[9*9*9];
        int x = 0;
        int y = 0;
        for (ArrayList<HashMap<Integer,Boolean>> row : possibles){
            for (HashMap<Integer,Boolean> col : row){
                for (Integer key : col.keySet()){
                    array[81*x+9*y+key] = col.get(key);
                }
                y++;
            }
            x++;
        }
        return array;
    }

    /* nastavit varianty zaskrtnutim radioboxov
    * prepojit s mriezkou v aplikacii a reagovat na to, co sa do nej zapise
    * */
}
