/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import problema.Problema;
import solucao.Individuo;
import solucao.IndividuoInteger;

/**
 *
 * @author fernando
 */
public class BuscaLocalCombinatorio {

    Problema problema;

    public BuscaLocalCombinatorio(Problema problema) {
        this.problema = problema;
    }
           
    //1) remover u e inserir após v;
    //2) remover u e x e inserir u e x após v;
    //3) remover u e x e inserir x e u após v; (posições invertidas)
    //4) trocar u e v;
    //5) troca u e x com v;
    //6) troca u e x com v e y;
    
    public void buscaLocal(IndividuoInteger individuo) {
        ArrayList<Integer> operacoes = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Collections.shuffle(operacoes);
                
        for(Integer i : operacoes) {
            switch(i) { 
                case 1:
                    buscaLocal1(individuo);
                    break;
                case 2:
                    buscaLocal2(individuo);
                    break;
                case 3:
                    buscaLocal3(individuo);
                    break;
                case 4:
                    buscaLocal4(individuo);
                    break;
                case 5:
                    buscaLocal5(individuo);
                    break;
                case 6:
                    buscaLocal6(individuo);
                    break;
            }
        }
    }
    
    
    // Busca local - 
    //1) remover u e inserir após v;
    public void buscaLocal1(IndividuoInteger individuo) {

        double foInicial = individuo.getFuncaoObjetivo();

        for (int u = 0; u < individuo.getCromossomos().size() - 1; u++) {
            for (int v = u + 1; v < individuo.getCromossomos().size(); v++) {

                int valorU = (int) individuo.getCromossomos().get(u);

                individuo.getCromossomos().remove(u);
                individuo.getCromossomos().add(v, valorU);

                // Calcular FO - Delta
                problema.calcularFuncaoObjetivo(individuo);

                // Se existe melhora
                if (individuo.getFuncaoObjetivo() < foInicial) {
                    // Encerra - first improvement
                    return;
                } else {
                    // Desfazer a troca
                    individuo.getCromossomos().remove(v);
                    individuo.getCromossomos().add(u, valorU);
                }

            }
        }

        // Retorna valor original da FO
        // Nao aconteceu nenhuma mudanca
        individuo.setFuncaoObjetivo(foInicial);

    }

    // Busca local - 
    //2) remover u e x e inserir u e x após v;
    public void buscaLocal2(IndividuoInteger individuo) {

        double foInicial = individuo.getFuncaoObjetivo();

        for (int u = 0; u < individuo.getCromossomos().size() - 2; u++) {
            int x = u + 1;
            for (int v = x + 1; v < individuo.getCromossomos().size(); v++) {

                int valorU = individuo.getCromossomos().get(u);
                int valorX = individuo.getCromossomos().get(x);

                int valorV = individuo.getCromossomos().get(v);

                individuo.getCromossomos().remove(u);
                individuo.getCromossomos().remove(x - 1);

                // Nova posicao de V
                int posV = individuo.getCromossomos().indexOf(valorV);
                individuo.getCromossomos().add(posV + 1, valorU);
                individuo.getCromossomos().add(posV + 2, valorX);

                // Calcular FO - Delta
                problema.calcularFuncaoObjetivo(individuo);

                // Se existe melhora
                if (individuo.getFuncaoObjetivo() < foInicial) {
                    // Encerra - first improvement
                    return;
                } else {
                    // Desfazer a troca
                    // Remove U e X
                    int pos = individuo.getCromossomos().indexOf(valorU);
                    individuo.getCromossomos().remove(pos);
                    pos = individuo.getCromossomos().indexOf(valorX);
                    individuo.getCromossomos().remove(pos);

                    // Inserir U e X nas posicoes originais
                    individuo.getCromossomos().add(u, valorU);
                    individuo.getCromossomos().add(u + 1, valorX);
                }

            }
        }

        // Retorna valor original da FO
        // Nao aconteceu nenhuma mudanca
        individuo.setFuncaoObjetivo(foInicial);

    }

    //3) remover u e x e inserir x e u após v;
    public void buscaLocal3(IndividuoInteger individuo) {

        double foInicial = individuo.getFuncaoObjetivo();

        for (int u = 0; u < individuo.getCromossomos().size() - 2; u++) {
            int x = u + 1;
            for (int v = x + 1; v < individuo.getCromossomos().size(); v++) {

                int valorU = individuo.getCromossomos().get(u);
                int valorX = individuo.getCromossomos().get(x);

                int valorV = individuo.getCromossomos().get(v);

                individuo.getCromossomos().remove(u);
                individuo.getCromossomos().remove(x - 1);

                // Nova posicao de V
                int posV = individuo.getCromossomos().indexOf(valorV);
                individuo.getCromossomos().add(posV + 1, valorX);
                individuo.getCromossomos().add(posV + 2, valorU);

                // Calcular FO - Delta
                problema.calcularFuncaoObjetivo(individuo);

                // Se existe melhora
                if (individuo.getFuncaoObjetivo() < foInicial) {
                    // Encerra - first improvement
                    return;
                } else {
                    // Desfazer a troca
                    // Remove U e X
                    int pos = individuo.getCromossomos().indexOf(valorU);
                    individuo.getCromossomos().remove(pos);
                    pos = individuo.getCromossomos().indexOf(valorX);
                    individuo.getCromossomos().remove(pos);

                    // Inserir U e X nas posicoes originais
                    individuo.getCromossomos().add(u, valorU);
                    individuo.getCromossomos().add(u + 1, valorX);
                }
            }
        }

        // Retorna valor original da FO
        // Nao aconteceu nenhuma mudanca
        individuo.setFuncaoObjetivo(foInicial);

    }

    public void buscaLocal4(IndividuoInteger individuo) {

        //4) trocar u e v;
        // SWAP
        // First improvement
        double foInicial = individuo.getFuncaoObjetivo();

        for (int u = 0; u < individuo.getCromossomos().size() - 1; u++) {
            for (int v = u + 1; v < individuo.getCromossomos().size(); v++) {

                // Opera SWAP
                Collections.swap(individuo.getCromossomos(), u, v);
                // Calcular FO - Delta
                problema.calcularFuncaoObjetivo(individuo);

                // Se existe melhora
                if (individuo.getFuncaoObjetivo() < foInicial) {
                    // Encerra - first improvement
                    return;
                } else {
                    // Desfazer a troca
                    Collections.swap(individuo.getCromossomos(), u, v);
                }

            }
        }

        // Retorna valor original da FO
        // Nao aconteceu nenhuma mudanca
        individuo.setFuncaoObjetivo(foInicial);

    }

    //5) troca u e x com v;
    public void buscaLocal5(IndividuoInteger individuo) {

        double foInicial = individuo.getFuncaoObjetivo();

        for (int u = 0; u < individuo.getCromossomos().size() - 2; u++) {
            int x = u + 1;
            for (int v = x + 1; v < individuo.getCromossomos().size(); v++) {

                int valorX = individuo.getCromossomos().get(x);

                // Opera SWAP
                Collections.swap(individuo.getCromossomos(), u, v);
                individuo.getCromossomos().remove(x);
                individuo.getCromossomos().add(v, valorX);

                // Calcular FO - Delta
                problema.calcularFuncaoObjetivo(individuo);

                // Se existe melhora
                if (individuo.getFuncaoObjetivo() < foInicial) {
                    // Encerra - first improvement
                    return;
                } else {
                    // Desfazer a troca
                    Collections.swap(individuo.getCromossomos(), u, v);
                    int pos = individuo.getCromossomos().indexOf(valorX);
                    individuo.getCromossomos().remove(pos);
                    individuo.getCromossomos().add(x, valorX);
                }

            }
        }

        // Retorna valor original da FO
        // Nao aconteceu nenhuma mudanca
        individuo.setFuncaoObjetivo(foInicial);

    }

    //6) troca u e x com v e y;
    public void buscaLocal6(IndividuoInteger individuo) {

        double foInicial = individuo.getFuncaoObjetivo();

        for (int u = 0; u < individuo.getCromossomos().size() - 2; u++) {
            int x = u + 1;
            for (int v = x + 1; v < individuo.getCromossomos().size() - 1; v++) {

                int y = v + 1;

                // Opera SWAP
                Collections.swap(individuo.getCromossomos(), u, v);
                Collections.swap(individuo.getCromossomos(), x, y);

                // Calcular FO - Delta
                problema.calcularFuncaoObjetivo(individuo);

                // Se existe melhora
                if (individuo.getFuncaoObjetivo() < foInicial) {
                    // Encerra - first improvement
                    return;
                } else {
                    // Desfazer a troca
                    Collections.swap(individuo.getCromossomos(), u, v);
                    Collections.swap(individuo.getCromossomos(), x, y);
                }

            }
        }

        // Retorna valor original da FO
        // Nao aconteceu nenhuma mudanca
        individuo.setFuncaoObjetivo(foInicial);

    }
} 