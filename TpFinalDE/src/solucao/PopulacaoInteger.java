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
public class PopulacaoInteger extends Populacao<Integer> {

    public PopulacaoInteger(int tamanho, Problema problema) {
        this.tamanho = tamanho;
        this.problema = problema;
    }    
    
    @Override
    void criar() {
        this.individuos = new ArrayList<>();
        
        for(int i = 1; i <= this.tamanho; i++) {
            Individuo individuo = new IndividuoInteger(this.problema.getDimensao());
            individuo.criar();
            this.individuos.add(individuo);            
        }        
    }
    
    
}
