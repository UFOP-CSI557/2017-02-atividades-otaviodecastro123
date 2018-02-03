/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodo;

import java.util.Collections;
import java.util.Random;
import problema.Problema;
import solucao.Individuo;
import solucao.IndividuoDouble;
import solucao.PopulacaoDouble;

/**
 *
 * @author Otávio
 */
public class HybridEvolution implements Metodo {
   
    private double min;
    private double max;
    private int nvar;
    private int mu;
    private int lambda;
    private Problema problema;
    private int geracoes;
    private double pMutacaoES;
    private double pMutacaoDE;
    private double pH;
    private int versao; 
    
    Individuo melhorSolucao;

    public HybridEvolution(double min, double max, int nvar, int mu, int lambda, Problema problema, int geracoes, double pMutacaoES, double pH, double pMutacaoDE, int versao) {
        this.min = min;
        this.max = max;
        this.nvar = nvar;
        this.mu = mu;
        this.lambda = lambda;
        this.problema = problema;
        this.geracoes = geracoes;
        this.pMutacaoES = pMutacaoES;
        this.pH = pH;
        this.pMutacaoDE = pMutacaoDE;
        this.versao = versao;
    }
    
          public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }

    public void setMelhorSolucao(Individuo melhorSolucao) {
        this.melhorSolucao = melhorSolucao;
    }

    @Override
    public void executar() {

        // Gerar uma populacao inicial aleatoriamente - mu
        PopulacaoDouble populacao = new PopulacaoDouble(problema, min, max, nvar, mu);
        populacao.criar();
        // Avaliar
        populacao.avaliar();

        PopulacaoDouble novaPopulacao = new PopulacaoDouble();

        // Criterio de parada -- gen
        for (int g = 1; g <= geracoes; g++) {
            // Para cada pai, gerar lambda/mu filhos
            for (int p = 0; p < populacao.getIndividuos().size(); p++) {
                for (int i = 0; i < lambda / mu; i++) {
                    // Mutacao
                    Random rnd = new Random();
                    double valor = rnd.nextDouble();
                    if (valor <= this.pMutacaoES) {
                        IndividuoDouble filho = (IndividuoDouble) populacao.getIndividuos().get(p).clone();
                        
                        mutacaoES(filho);
                        
                        // avaliar filhos
                        problema.calcularFuncaoObjetivo(filho);
                        // Adicionar
                        novaPopulacao.getIndividuos().add(filho);
                        
                        
                        // novo metodo
                        if(versao == 1){
                        if(rnd.nextDouble() <= this.pH){
                        IndividuoDouble pai1 = (IndividuoDouble) populacao.getIndividuos().get(p).clone(); 
                        IndividuoDouble pai2 = (IndividuoDouble) filho.clone();
                        
                        
                        IndividuoDouble trial = (IndividuoDouble) populacao.getIndividuos().get(p).clone();
                        perturbacao(trial, pai1, pai2);
                        mutacao(trial, (IndividuoDouble) populacao.getIndividuos().get(rnd.nextInt(mu)).clone());
                        
                        
                        IndividuoDouble filho2 = (IndividuoDouble) populacao.getIndividuos().get(p).clone();
                        crossoverUmPonto(pai1, trial, filho2); 
                        
                        problema.calcularFuncaoObjetivo(filho2);
                        novaPopulacao.getIndividuos().add(filho2);
                        }
                        }
                        

                    }
                }
            }

            // ES(mu, lambda)
            // populacao.getIndividuos().clear();
            // ES(mu+lambda)
            populacao.getIndividuos()
                    .addAll(novaPopulacao.getIndividuos());
            Collections.sort(populacao.getIndividuos());
            populacao.getIndividuos()
                    .subList(this.mu,
                            populacao.getIndividuos().size()).clear();

//System.out.println("Gen = " + g
//                        + "\tFO = "
//                        + populacao.getMelhorIndividuo()
//                        .getFuncaoObjetivo()
//                        + "\tPop = "
//                        + populacao.getIndividuos().size());

        }
        
        this.setMelhorSolucao((Individuo) populacao.getMelhorIndividuo().clone());

    }
    
      private void perturbacao(IndividuoDouble trial, IndividuoDouble xr1, IndividuoDouble xr2) {
          trial.getCromossomos().clear();
        // Diferen�a entre r1 e r2
        for (int i = 0; i < nvar; i++) {
            double diferenca = xr1.getCromossomos().get(i)
                    - xr2.getCromossomos().get(i);
            trial.getCromossomos().add(diferenca);
        }

    }
      
          private void mutacao(IndividuoDouble trial, IndividuoDouble xr0) {

        // Multiplicar por F a diferen�a e somar com Xr0
        for (int i = 0; i < nvar; i++) {
            double valor = xr0.getCromossomos().get(i)
                    + this.pMutacaoDE * (trial.getCromossomos().get(i));
            if(valor >= this.min && valor <= this.max){
            trial.getCromossomos().set(i, valor);
            }
        }

    }

    private void mutacaoES(IndividuoDouble filho) {

        Random rnd = new Random();

        double valor;
        for (int i = 0; i < filho.getCromossomos().size(); ++i) {
            if (rnd.nextDouble() <= this.pMutacaoES) {
                valor = filho.getCromossomos().get(i)
                        * rnd.nextDouble();

                if (rnd.nextBoolean() == false) {
                    valor = -valor;
                }
                valor = filho.getCromossomos().get(i)
                        + valor;
                if (valor >= this.min && valor <= this.max) {
                    filho.getCromossomos().set(i, valor);
                }
            }
        }

    }
    
    
      private void crossoverUmPonto(IndividuoDouble pai1,
            IndividuoDouble pai2, Individuo filho) {

        Random rnd = new Random();
        int corte = rnd.nextInt(pai1.getCromossomos().size());

//        System.out.println(filho.getCromossomos().size());
//        System.out.println(pai1.getCromossomos().size());        
        filho.getCromossomos().clear();

//        System.out.println(filho.getCromossomos().size());
//        System.out.println(pai1.getCromossomos().size());        
        filho.getCromossomos()
                .addAll(pai1.getCromossomos()
                        .subList(0, corte));
        filho.getCromossomos()
                .addAll(pai2.getCromossomos()
                        .subList(corte,
                                pai2.getCromossomos().size()));

    }
}
