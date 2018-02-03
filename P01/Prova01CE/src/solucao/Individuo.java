/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solucao;

import java.util.ArrayList;

/**
 *
 * @author Otávio
 */
public interface Individuo<T> extends Comparable<Individuo> {
    
    Double getFuncaoObjetivo();
    void setFuncaoObjetivo(Double funcaoObjetivo);
    
    ArrayList<T> getCromossomos();
    void setCromossomos(ArrayList<T> cromossomos);
    
    void criar();
    Individuo<T> clone();

    public int getNvar();
    
}
