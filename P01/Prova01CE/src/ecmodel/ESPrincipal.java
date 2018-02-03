/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecmodel;

import metodo.HybridEvolution;
import problema.Problema;
import problema.ProblemaSchwefels;
import solucao.Individuo;

/**
 *
 * @author Otávio
 */
public class ESPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
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
        HybridEvolution es = new HybridEvolution(min, max, nvar, mu, lambda, problema, geracoes, pMutacaoES, pH, pMutacaoDE, 1);
        
        es.executar();
        Individuo melhor = es.getMelhorSolucao();
        
        System.out.println("Melhor solucao = " + melhor);  
        
        
    }
    
}
