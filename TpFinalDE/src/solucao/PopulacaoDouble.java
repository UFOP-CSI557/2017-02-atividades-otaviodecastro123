/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solucao;

import java.util.ArrayList;
import problema.Problema;

/**
 *
 * @author fernando
 */
public class PopulacaoDouble extends Populacao<Double> {

    double min;
    double max;
    int nvar;

    public PopulacaoDouble() {
        this.individuos = new ArrayList<>();
    }
    
    public PopulacaoDouble(Problema problema, double min, double max, int nvar, int tamanho) {
        this.min = min;
        this.max = max;
        this.nvar = nvar;
        this.tamanho = tamanho;
        this.problema = problema;
    }
    
    @Override
    public void criar() {
        individuos = new ArrayList<>();
        
        for (int i = 0; i < this.getTamanho(); i++) {
            Individuo individuo = 
                    new IndividuoDouble(min, max, nvar);
            individuo.criar();
            individuos.add(individuo);
            
        }        
    }
    
    
}
