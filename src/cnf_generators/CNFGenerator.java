package cnf_generators;

import grids.Sudoku;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Hlavna trieda na spustenie grafickeho okna a ovladanie dalsich tried
 */
/* Pxyz <-> sudoku[x][y] == z <-> content[81*x + 9*y + z] == true */

public class CNFGenerator {
    String outputFile;
    Sudoku sudoku;
    //formula obsahuje true alebo false pre kazdu z 729 premennych
    //formulas[i] == true prave vtedy, ked sudoku[i/81][(i % 81)/9] == i % 9
    //interne ide o sudoku s R0-8, C0-8, cislami 0-8, len pri vypise sa prida +1
    //pozor na parne, resp. neparne sudoku (parne obsahuje "cisla" 1,3,5,7)
    ArrayList<ArrayList<Integer>> formulas;

    public CNFGenerator(String outputFile, Sudoku sudoku){
        this.outputFile = outputFile;
        this.sudoku = sudoku;
        this.formulas = new ArrayList<>();
    }

    public void generateCNF() throws IOException {
        generateClassic();
        generateGivens();
        printToFile();
    }

    /** Funkcia prida formuly pre kazde policko podla vpisiek, ktore obsahuje */
    private void generateGivens(){
        ArrayList<ArrayList<HashMap<Integer,Boolean>>> notes = this.sudoku.getPossibles();
        for ( int x = 0; x < 9; x++ ){
            for ( int y = 0; y < 9; y++ ) {
                HashMap<Integer,Boolean> map = notes.get(x).get(y);
                ArrayList<Integer> list = new ArrayList<>();
                for ( Integer k : map.keySet() ) {
                    if (map.get(k)){
                        list.add(variableNo(x, y, k));
                    }
                }
                formulas.add(list);
            }
        }
    }

    /** Funkcia vrati poziciu policka v zozname 729 premennych (1-729) pre CNF
     * Pozor, pri indexacii do pola (napriklad possibles) by sme chceli cislo o 1 mensie*/
    private int variableNo(int x, int y, int z){
        return 81*x + 9*y + z + 1;
    }

    /** Funkcia zabezpeci, ze kazde policko bude obsahovat nejake cislo 0-8 */
    private void min1Number(){
        // pre vsetky x, y: Pxy1 v Pxy2 v ... v Pxy9
        for ( int x = 0; x < 9; x++ ) {
            for ( int y = 0; y < 9; y++ ) {
                ArrayList<Integer> list = new ArrayList<>();
                for ( int z = 0; z < 9; z++ ) {
                    list.add(variableNo(x, y, z));
                }
                formulas.add(list);
            }
        }
    }

    /** Funkcia zabezpeci, ze kazde policko bude obsahovat maximalne jedno cislo */
    private void max1Number(){
        // pre vsetky x, y, z1, z2, z1<>z2: ^Pxzy1 v ^Pxzy2
        for ( int x = 0; x < 9; x++ ) {
            for ( int y = 0; y < 9; y++ ) {
                for ( int z1 = 0; z1 < 9; z1++ ) {
                    for ( int z2 = z1+1; z2 < 9; z2++ ) {
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(-variableNo(x, y, z1));
                        list.add(-variableNo(x, y, z2));
                        formulas.add(list);
                    }
                }

            }
        }
    }

    /** Funkcia zabezpeci, ze cisla v riadkoch sa nebudu opakovat*/
    private void noRowRepetition(){
        // pre vsetky x, y1, y2, z, y1<>y2: ^Pxy1z v ^Pxy2z
        for ( int x = 0; x < 9; x++ ) {
            for ( int z = 0; z < 9; z++ ) {
                for ( int y1 = 0; y1 < 9; y1++ ) {
                    for ( int y2 = y1+1; y2 < 9; y2++ ) {
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(-variableNo(x, y1, z));
                        list.add(-variableNo(x, y2, z));
                        formulas.add(list);
                    }
                }

            }
        }
    }

    /** Funkcia zabezpeci, ze cisla v stlpcoch sa nebudu opakovat*/
    private void noColRepetition(){
        // pre vsetky x1, x2, y, z, x1<>x2: ^Px1yz v ^Px2yz
        for ( int x1 = 0; x1 < 9; x1++ ) {
            for ( int x2 = x1+1; x2 < 9; x2++ ) {
                for ( int y = 0; y < 9; y++ ) {
                    for ( int z = 0; z < 9; z++ ) {
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(-variableNo(x1, y, z));
                        list.add(-variableNo(x2, y, z));
                        formulas.add(list);
                    }
                }

            }
        }
    }

    /** Funkcia zabezpeci, ze cisla v stvorcoch 3x3 sa nebudu opakovat*/
    private void noBoxRepetition(){
        // pre vsetky x1, x2, y1, y2, z, (x1,y1)<>(x2,y2), x1/3 == x2/3, y1/3 == y2/3: ^Px1y1z v ^Px2y2z
        for ( int x1 = 0; x1 < 8; x1++ ) {
            for ( int x2 = 0; x2 < 8; x2++) {
                for ( int y1 = 0; y1 < 8; y1++) {
                    for ( int y2 = 0; y2 < 8; y2++) {
                        for ( int z = 0; z < 8; z++){
                            if ((x1/3 == x2/3) && (y1/3 == y2/3) && ((x1 != x2) || (y1 != y2))) {
                                ArrayList<Integer> list = new ArrayList<>();
                                list.add(-variableNo(x1, y1, z));
                                list.add(-variableNo(x2, y2, z));
                                formulas.add(list);
                            }
                        }
                    }
                }
            }
        }
    }

    /** Funkcia vygeneruje formuly splnajuce pravidla klasickeho sudoku*/
    private void generateClassic(){
        // kazde policko obsahuje aspon jedno cislo
        min1Number();
        // kazde policko obsahuje maximalne jedno cislo
        max1Number();
        // ziadne dve cisla v riadku sa neopakuju
        noRowRepetition();
        // ziadne dve cisla v stlpci sa neopakuju
        noColRepetition();
        // ziadne dve cisla v stvorci 3x3 sa neopakuju
        noBoxRepetition();

    }

    private void generateWindoku(){
        //TODO
    }

    private void generateDiagonal(){
        //TODO
    }

    private void generateOdd(){
        //TODO
    }

    private void generateEven(){
        //TODO
    }

    private String printFormula(ArrayList<Integer> f){
        StringBuffer s = new StringBuffer();
        for ( Integer i : f ) {
            s.append(i);
            s.append(' ');
        }
        s.append("0");
        return new String(s);
    }

    private void printToFile() throws IOException{
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
}
