/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problema;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import solucao.Individuo;

/**
 *
 * @author fernando
 */
public class ProblemaTSP implements Problema {
   
    String nomeDoArquivo;
    String nomeDaInstancia;
    String funcaoCalculo;
    int dimensao;
    
    Double[][] coordenadas;
    Double[][] distancias;
    
    public ProblemaTSP(String nomeDoArquivo) {
        this.nomeDoArquivo = nomeDoArquivo;
        lerArquivo();
        calcularDistancias();
    }
    
    public void lerArquivo() {
        
        BufferedReader br = null;
        
        try {
            br = new BufferedReader(new FileReader(this.nomeDoArquivo));
            String linha;
            String[] dados;
            
            // Nome da instancia
            linha = br.readLine();
            dados = linha.split(":");
            this.nomeDaInstancia = dados[1];
            
            // Tipo de instancia
            linha = br.readLine();
            // Comentario
            linha = br.readLine();
            
            // Dimensao
            linha = br.readLine();
            dados = linha.split(":");
            this.dimensao = Integer.parseInt(dados[1].trim());
            
            coordenadas = new Double[dimensao][2];
            distancias = new Double[dimensao][dimensao];
            
            // Funcao de Calculo (ATT, EUC_2D, ...)
            linha = br.readLine();
            dados = linha.split(":");
            funcaoCalculo = dados[1].trim();
            // Cabecalho
            linha = br.readLine();
            
            while( (linha = br.readLine()) != null ) {
                                             
                if (linha.equals("EOF")) {
                    break;
                }
                
                dados = linha.split(" ");
                
                int id = Integer.parseInt(dados[0]);
                
                // X
                coordenadas[id-1][0] = Double.parseDouble(dados[1]);
                // Y
                coordenadas[id-1][1] = Double.parseDouble(dados[2]);
                               
            }
            
            br.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Problema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Problema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void calcularDistancias() {
        
        switch(this.funcaoCalculo) {
            case "ATT":
                this.calcularDistanciasATT();
                break;
            case "EUC_2D":
                this.calcularDistanciasEUC2D();
                break;
            default:
                throw new UnsupportedOperationException("Função não implementada: " + this.funcaoCalculo);
                        
        }
        
    }
    
    // Adaptado de: https://github.com/MOEAFramework/MOEAFramework/blob/master/examples/org/moeaframework/examples/ga/tsplib/PseudoEuclideanDistance.java
    private void calcularDistanciasATT() {
        // ATT - Pseudo EUC
        Double dist;
        for(int i = 0; i < this.dimensao; i++ ) {
            for(int j = 0; j < this.dimensao; j++) {
                if (i == j)
                    dist = 0.0;
                else {
                    // Px - Qx
                    dist = Math.pow(this.coordenadas[i][0] 
                            - this.coordenadas[j][0]  , 2);
                    // Py - Qy
                    dist += Math.pow(this.coordenadas[i][1] 
                            - this.coordenadas[j][1]  , 2);
                    dist = Math.sqrt(dist / 10.0);

                    double t = Math.round(dist);
                    
                    if ( t < dist ) {
                        dist = t + 1.0;
                    } else {
                        dist = t;
                    }
                    
                }
                
                this.distancias[i][j] = dist;
            }
        }        
    }
    
    private void calcularDistanciasEUC2D() {
        // EUC_2D
        Double dist;
        for(int i = 0; i < this.dimensao; i++ ) {
            for(int j = 0; j < this.dimensao; j++) {
                if (i == j)
                    dist = 0.0;
                else {
                    // Px - Qx
                    dist = Math.pow(this.coordenadas[i][0] 
                            - this.coordenadas[j][0]  , 2);
                    // Py - Qy
                    dist += Math.pow(this.coordenadas[i][1] 
                            - this.coordenadas[j][1]  , 2);
                    dist = Math.sqrt(dist);                    
                }
                
                this.distancias[i][j] = dist;
            }
        }
    }

    @Override
    public String toString() {
        return "Problema{" + "nomeDoArquivo=" + nomeDoArquivo + ", nomeDaInstancia=" + nomeDaInstancia + ", funcaoCalculo=" + funcaoCalculo + ", dimensao=" + dimensao + ", coordenadas=" + coordenadas + ", distancias=" + distancias + '}';
    }    
    
   @Override
    public void calcularFuncaoObjetivo(Individuo individuo) {
        Double custo = 0.0;
        
        for(int i = 0; i < this.dimensao - 1; i++) {
            custo += this.distancias
                    [ (int)individuo.getCromossomos().get(i) - 1 ]
                    [ (int)individuo.getCromossomos().get(i + 1) - 1 ];
        }
        
        individuo.setFuncaoObjetivo(custo);
    }

    @Override
    public int getDimensao() {
        return this.dimensao;
    }
    
}
