/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solucao;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author fernando
 */
public class IndividuoInteger implements Individuo<Integer> {

    private ArrayList<Integer> cromossomos;
    private Double funcaoObjetivo;
    private Integer dimensao;

    public IndividuoInteger(Integer dimensao) {
        this.dimensao = dimensao;
        this.cromossomos = new ArrayList<>();
    }

    @Override
    public Double getFuncaoObjetivo() {
        return this.funcaoObjetivo;
    }

    @Override
    public void setFuncaoObjetivo(Double funcaoObjetivo) {
        this.funcaoObjetivo = funcaoObjetivo;
    }

    @Override
    public ArrayList<Integer> getCromossomos() {
        return this.cromossomos;
    }

    @Override
    public void setCromossomos(ArrayList<Integer> cromossomos) {
        this.cromossomos = cromossomos;
    }

    public Integer getDimensao() {
        return dimensao;
    }

    public void setDimensao(Integer dimensao) {
        this.dimensao = dimensao;
    }
    
    @Override
    public void criar() {
        this.cromossomos = new ArrayList<>();
        for (int i = 1; i <= this.dimensao; i++) {
            this.cromossomos.add(i);
        }

        Collections.shuffle(this.cromossomos);
    }

    @Override
    public Individuo<Integer> clone() {
        Individuo individuo = new IndividuoInteger(dimensao);
        individuo.setCromossomos(new ArrayList<>(this.getCromossomos()));
        individuo.setFuncaoObjetivo(this.getFuncaoObjetivo());

        return individuo;
    }

    @Override
    public int compareTo(Individuo o) {
        return this.getFuncaoObjetivo()
                .compareTo(o.getFuncaoObjetivo());
    }


    @Override
    public String toString() {
        return "Individuo{" + "cromossomos=" + cromossomos + ", funcaoObjetivo=" + funcaoObjetivo + ", dimensao=" + dimensao + '}';
    }  

    @Override
    public int getNvar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}