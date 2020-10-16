/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Joás
 */
public class Populacao {
    private int tamanho;
    private ArrayList cromossomos;
    private float fitnessMedio = 0;
    
    public Populacao( int pTamanho ){
        this.tamanho = pTamanho;
        this.cromossomos = new ArrayList();
        this.create();    
    }

    public Cromossomo[] getCromossomos() {
        int tamanho = this.cromossomos.size();
        Cromossomo cromossomos[] = new Cromossomo[ tamanho ];
        for (int i = 0; i < tamanho; i++) {
            cromossomos[i] = (Cromossomo) this.cromossomos.get(i);
        }
        return cromossomos;
    }
    
    public void create(){
        Cromossomo objCromossomo;
        for (int i = 0; i < tamanho; i++) {
            objCromossomo = new Cromossomo( 8 );
            this.cromossomos.add( objCromossomo );
        }
    }
    
    public void fitnessMedio(){
        Cromossomo cromossomo;
        int soma = 0;
        for ( int i = 0; i < this.cromossomos.size(); i++ ) {
            cromossomo = (Cromossomo) cromossomos.get(i);
            soma = soma + cromossomo.getFitness();
        }
        this.fitnessMedio = soma / this.tamanho;
    }
    
    public int getTamanho(){
        return tamanho;
    }
    public float getFitnessMedio(){
        return fitnessMedio;
    }
    
    public void addNews( Cromossomo pCromossomos[] ){
        for (Cromossomo cromossomo : pCromossomos) {
            this.cromossomos.add( cromossomo );
            //this.cromossomos.remove(0);
        }
    }
    
    public Cromossomo getBest(){
        int size = this.cromossomos.size();
        Cromossomo cromossomos_all[] = this.getCromossomos();
        Arrays.sort( cromossomos_all, Comparator.comparing(Cromossomo::getFitness));
        return (Cromossomo) this.cromossomos.get( size - 1 );
    }
    
    public void mutar(){
        Cromossomo cromossomo;
        for (int i = 0; i < cromossomos.size(); i++) {
            cromossomo = (Cromossomo) cromossomos.get(i);
            cromossomo.mutar(); 
            i = i + 5;
        }
    }
}
