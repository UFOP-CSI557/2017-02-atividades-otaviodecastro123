/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problema;

import solucao.Individuo;

/**
 *
 * @author Otávio
 */
public class ProblemaSchwefels implements Problema {

    private int dimensao;

    public ProblemaSchwefels(int dimensao) {
        this.dimensao = dimensao;
    }

    @Override
    public void calcularFuncaoObjetivo(Individuo individuo) {
        Double somatorio = 0.0;

        for (Object vr : individuo.getCromossomos()) {
            Double var = (Double) vr;
            somatorio += -var * Math.sin(Math.sqrt(Math.abs(var)));
        }

        individuo.setFuncaoObjetivo(somatorio);
    }

    public int getDimensao() {
        return this.dimensao;
    }

}
