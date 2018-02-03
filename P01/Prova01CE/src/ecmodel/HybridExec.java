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
import metodo.HybridEvolution;
import problema.Problema;
import problema.ProblemaSchwefels;

/**
 *
 * @author Otávio
 */
public class HybridExec {

    public static void main(String args[]) throws IOException {

        String caminho = "C:\\Users\\otavi_000\\Desktop\\Nova pasta\\";
        String arquivo = caminho + "ESHybridxH.csv";

        BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true));

        double min = -500;
        double max = 500;
        int nvar = 40;
        int mu = 100;
        int lambda = 1000;
        int geracoes = 300;
        double pMutacaoES = 0.1;
        double pH = 0.3;
        double pMutacaoDE = 0.1;

        Problema problema = new ProblemaSchwefels(100);
        HybridEvolution hb1 = new HybridEvolution(min, max, nvar, mu, 1500, problema, geracoes, pMutacaoES, 0.5, 0.05, 1);
        HybridEvolution hb2 = new HybridEvolution(min, max, nvar, mu, lambda, problema, geracoes, pMutacaoES, 1.0, 0.2, 1);
        
        Double result = 0.0;

        for (int c = 1; c <= 30; c++) {
            ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1, 2));
            Collections.shuffle(casos);

            for (Integer i : casos) {
                long startTime = System.currentTimeMillis();
                switch (i) {
                    case 1:
                        hb1.executar();
                        result = hb1.getMelhorSolucao().getFuncaoObjetivo();
                        break;
                    case 2:
                        hb2.executar();
                        result = hb2.getMelhorSolucao().getFuncaoObjetivo();
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
