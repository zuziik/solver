package cnf_generators;

import grids.Sudoku;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Zuzka on 30.11.2015.
 */
public abstract class Generator {
    String outputFile = "files/cnf.txt";
    //formula obsahuje true alebo false pre kazdu z 729 premennych
    //formulas[i] == true prave vtedy, ked sudoku[i/81][(i % 81)/9] == i % 9
    //interne ide o sudoku s R0-8, C0-8, cislami 0-8, len pri vypise sa prida +1
    //pozor na parne, resp. neparne sudoku (parne obsahuje "cisla" 1,3,5,7)
    ArrayList<ArrayList<Integer>> formulas = new ArrayList<>();

    public Generator(){
    }

    public abstract void generateCNF();

    /** Funkcia pre kazde dve policka zo zadaneho regionu zabezpeci, ze budu obsahovat rozne cisla.
     * Funkcia predpoklada, ze zadany region bude obsahovat kazde policko LEN RAZ.*/
    void generateRegion(ArrayList<ArrayList<Integer>> cells){
        //pre vsetky x1, x2, y1, y2, z, (x1,y1)<>(x2,y2): ^Px1y1z v ^Px2y2z
        for (ArrayList<Integer> cell1 : cells){
            for (ArrayList<Integer> cell2 : cells){
                if (cell1 != cell2){
                    for (int z = 0; z < 9; z++){
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(-variableNo(cell1.get(0),cell1.get(1),z));
                        list.add(-variableNo(cell2.get(0),cell2.get(1),z));
                        formulas.add(list);
                    }
                }
            }
        }
    }



    /** Funkcia vrati poziciu policka v zozname 729 premennych (1-729) pre CNF
     * Pozor, pri indexacii do pola (napriklad possibles) by sme chceli cislo o 1 mensie*/
    int variableNo(int x, int y, int z){
        return 81*x + 9*y + z + 1;
    }

    String printFormula(ArrayList<Integer> f){
        StringBuffer s = new StringBuffer();
        for ( Integer i : f ) {
            s.append(i);
            s.append(' ');
        }
        s.append("0");
        return new String(s);
    }

    public ArrayList<ArrayList<Integer>> getCNFFormulas() {
        return this.formulas;
    }

    public void printToFile() throws IOException{
        try{
            PrintWriter out = new PrintWriter(new File(outputFile));
            out.println("p cnf 729 " + formulas.size());
            for (ArrayList<Integer> list : formulas){
                out.println(printFormula(list));
            }
            out.close();
        }
        catch(IOException e){
            System.err.println("File not found");
        }
    }

    public void createFileWithCNF() throws IOException{
        this.generateCNF();
        this.printToFile();
        System.out.println("CNF generated");
    }
}
