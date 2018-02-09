/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solucao;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author fernando
 */
public class IndividuoDouble implements Individuo<Double> {

    private ArrayList<Double> cromossomos; //genotipo e fenotipo 
    private Double funcaoObjetivo;

    private double min;
    private double max;

    private int nvar;

    public IndividuoDouble(double min, double max, int nvar) {
        this.min = min;
        this.max = max;
        this.nvar = nvar;
        this.cromossomos = new ArrayList<>();
    }

    public int getNvar() {
        return nvar;
    }

    public void setNvar(int nvar) {
        this.nvar = nvar;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    @Override
    public Double getFuncaoObjetivo() {
        return funcaoObjetivo;
    }

    @Override
    public void setFuncaoObjetivo(Double funcaoObjetivo) {
        this.funcaoObjetivo = funcaoObjetivo;
    }
    
    @Override
    public ArrayList<Double> getCromossomos() {
        return this.cromossomos;
    }

    @Override
    public void setCromossomos(ArrayList<Double> cromossomos) {
        this.cromossomos = cromossomos;
    }

    @Override
    public void criar() {
        this.cromossomos = new ArrayList<>();

        Random rnd = new Random();
        double valor;

        for (int i = 0; i < this.getNvar(); i++) {
            valor = this.getMin()
                    + (this.getMax() - this.getMin())
                    * rnd.nextDouble();
            this.cromossomos.add(valor);
        }
    }

    @Override
    public Individuo<Double> clone() {
        Individuo individuo = null;
        individuo
                = new IndividuoDouble(this.getMin(),
                        this.getMax(),
                        this.getNvar());
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
        return "Individuo{" + "cromossomos=" + cromossomos + ", funcaoObjetivo=" + funcaoObjetivo + '}';
    }

}
