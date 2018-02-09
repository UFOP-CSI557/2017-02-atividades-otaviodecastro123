/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecmodel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import metodo.EvolucaoDiferencialTSP;
import problema.Problema;
import problema.ProblemaTSP;

/**
 *
 * @author Otávio
 */
public class DETSPPrincipal {
    
        public static void main (String args[]) throws IOException{
        String caminho = "C:\\Users\\otavi_000\\Desktop\\Nova pasta\\";
        String arquivo = caminho + "DEHybrid.csv";
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true));
        Problema problema = new ProblemaTSP("C:/Users/otavi_000/Documents/NetBeansProjects/tsp/berlin52.tsp");

        double min = 0.0;
        double max = 1.0;
        int gmax = 100;
        int D = problema.getDimensao();
        int Np = 100;
        double F = 0.1;
        double Cr = 0.8;
        EvolucaoDiferencialTSP deTSP1 = new EvolucaoDiferencialTSP(min, max, gmax, D, Np, F, Cr, problema, -1, -1);
        EvolucaoDiferencialTSP deTSP2 = new EvolucaoDiferencialTSP(min, max, gmax, D, Np, F, Cr, problema, 0.8, 0.8);
        
              
        Double result = 0.0;

        for (int c = 1; c <= 30; c++) {
            ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1, 2));
            Collections.shuffle(casos);

            for (Integer i : casos) {
                long startTime = System.currentTimeMillis();
                switch (i) {
                    case 1:
                        deTSP1.executar();
                        result = deTSP1.getMelhorSolucao().getFuncaoObjetivo();
                        break;
                    case 2:
                        deTSP2.executar();
                        result = deTSP2.getMelhorSolucao().getFuncaoObjetivo();
                        break;
                }

                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;

                String texto = c + ";" + i + ";" + result + ";" + totalTime;

                // Adiciona no fim do arquivo o texto
                bw.append(texto + "\n");
                bw.flush();

                System.out.println(c + ";" + i + ";" + result + ";" + totalTime);
                System.out.flush();

            }

        }
         bw.close();
        
        }
    
}
