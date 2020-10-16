/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic;

import static java.lang.Thread.sleep;
import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joás
 */
public class AlgoritmoGenetico {

    private Populacao objPopulacao;
    private int taxa = 70;
    
    public AlgoritmoGenetico( int quantidade ) {
        this.objPopulacao = new Populacao( quantidade );
    }
    
    
    
    public static void main(String []args){
        int a = 15;
        AlgoritmoGenetico objAlgoritmoGenetico;
        objAlgoritmoGenetico = new AlgoritmoGenetico( 20 );
        objAlgoritmoGenetico.geracoes();
        
    }
    public void geracoes( ){
        Cromossomo cromossomosSelect[];
        Cromossomo cromossomosNew[];
        Cromossomo best;
        float fitnessMedio;
        int target = 16;
        int fitnessBest = 0;
        int count = 0;
        while( fitnessBest < target ){
            cromossomosSelect = this.select( taxa );
            cromossomosNew = this.crossover( cromossomosSelect );
            this.objPopulacao.addNews( cromossomosNew );
            this.objPopulacao.mutar();
            this.objPopulacao.fitnessMedio();
            fitnessMedio = this.objPopulacao.getFitnessMedio();
            best = this.objPopulacao.getBest();
            fitnessBest = best.getFitness();
            
            System.out.println("Count: "+count+
                    ", Best: "+best.toString()+
                    ", Médio: "+fitnessMedio);
            count++;
            
            try {
                sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(AlgoritmoGenetico.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public Cromossomo[] select( int percent ){
        int quantidade = getQuantidade( percent );
        Cromossomo cromossomos[] = new Cromossomo[ quantidade ];
        Cromossomo cromossomos_all[] = this.objPopulacao.getCromossomos();
        Arrays.sort( cromossomos_all, Comparator.comparing(Cromossomo::getFitness));
        int i = cromossomos_all.length - 1;
        int j = 0;
        while ( quantidade > 0 ) {
            cromossomos[j] = cromossomos_all[i];
            i--;
            quantidade--;
            j++;
        }
        return cromossomos;
       
    }
    
    public Cromossomo[] crossover( Cromossomo[] cromossomos ){
        Cromossomo objCromossomos[];
        Cromossomo cromossomoNew, cromossomoOne, cromossomoTwo;
        int count = cromossomos.length / 2;
        objCromossomos = new Cromossomo[ count ];
        int cromossomoSize = cromossomos[0].size();
        int meio = cromossomoSize / 2;
        int j = cromossomos.length - 1;
        boolean leftGene[], rightGene[];
        leftGene = new boolean[ meio ];
        rightGene = new boolean[ meio ];
        int k, l = 0;
        for (int i = 0; i < count; i++) {
            cromossomoNew = new Cromossomo( cromossomoSize );
            cromossomoOne =  cromossomos[i];
            cromossomoTwo =  cromossomos[j];
            j--;
            for (k = 0; k < meio; k++) {
                rightGene[k] = cromossomoOne.getGene(l);
                l++;
            }
            k = 0;
            for (; l < cromossomoSize; l++) {
                leftGene[k] = cromossomoOne.getGene(l);
                k++;
            }
            l = 0;
            cromossomoNew.setGenesLeft(leftGene);
            cromossomoNew.setGenesRight(rightGene);            
        }
        return cromossomos;
    }
    
    public int getQuantidade( int percent ){
        int tamanho = this.objPopulacao.getTamanho();
        int x = tamanho * percent / 100;
        return x;
    }
    
    
    
    
    
}
