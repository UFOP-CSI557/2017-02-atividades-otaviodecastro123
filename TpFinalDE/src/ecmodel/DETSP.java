/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecmodel;

import metodo.EvolucaoDiferencialTSP;
import problema.Problema;
import problema.ProblemaTSP;

/**
 *
 * @author Ot�vio
 */
public class DETSP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Problema problema = new ProblemaTSP("C:/Users/otavi_000/Documents/NetBeansProjects/tsp/berlin52.tsp");

        double min = 0.0;
        double max = 1.0;
        int gmax = 100;
        int D = problema.getDimensao();
        int Np = 100;
        double F = 0.1;
        double Cr = 0.8;
        EvolucaoDiferencialTSP deTSP = new EvolucaoDiferencialTSP(min, max, gmax, D, Np, F, Cr, problema, -1, -1);
        deTSP.executar();

    }

}
