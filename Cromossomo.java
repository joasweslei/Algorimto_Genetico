/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic;

import java.util.Random;

/**
 *
 * @author Joás
 */
public class Cromossomo {
    private boolean genes[];
    private int fitness;
    
    private int beneficios[] = {3, 3, 2, 4, 2, 3, 5, 2};
    private int pesos[] = {5, 4, 7, 8, 4, 4, 6, 8};
    
    public Cromossomo( int size ){
        genes = new boolean[ size ];
        this.create();        
    }
    
    public void create(){
        boolean gene = true;
        Random objRandom = new Random();
        for (int i = 0; i < this.genes.length; i++) {
            gene = objRandom.nextBoolean();
            this.genes[i] = gene;           
        }
        this.setFitness();
    }
    public int size(){
        return this.genes.length;
    }
    public boolean getGene( int index ){
        return this.genes[ index ];
    }
    
    public void setGenesLeft( boolean pGenes[] ){
        for (int i = 0; i < pGenes.length; i++) {
            this.genes[i] = pGenes[i];
        }
    }
    public void setGenesRight( boolean pGenes[] ){
        int i = this.genes.length - 1;
        int k = pGenes.length - 1; 
        for ( ; k >=0 ; k--) {
            this.genes[i] = pGenes[k];
            i--;
        }        
    }
    
    public void setFitness( ){
        boolean gene = false;
        int beneficio = 0;
        int peso = 0;
        for (int i = 0; i < genes.length; i++) {
            gene = genes[ i ];
            if( gene ){
                beneficio = beneficio + beneficios[ i ];
                peso = peso + pesos[ i ];
            }
        }
        if( peso > 25 ){
            beneficio = 0;
        }
        this.fitness = beneficio;
        
    }

    public int getFitness() {
        return fitness;
    }
    @Override
    public String toString(){
       String aux =  " [";
        for (int i = 0; i < genes.length - 1; i++) {
            aux = aux  +genes[i] + ", ";
        }
        aux = aux  +genes[genes.length - 1] + "]";
        aux = aux + " = " +this.fitness;
       return aux;
    }
    
    public void mutar( ){
        int index;
        for (int i = 0; i < 3; i++) {
            index = (int) (Math.random() * genes.length);
            genes[ index ] = !genes[ index ];            
        }        
    }         
}
