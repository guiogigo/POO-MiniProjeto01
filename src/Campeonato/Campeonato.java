package Campeonato;

import java.util.ArrayList;
import java.util.Random;

public class Campeonato {
    ArrayList<Clube> clubes = new ArrayList<Clube>();
    boolean debug = false;
    private int clubesTam;
    private Clube[] classificacao;

    public Campeonato(ArrayList<Clube> clubes) {
        clubes.forEach(clube -> this.clubes.add(clube));
        clubesTam = clubes.size();    	
    	classificacao = new Clube[clubesTam];
    }
    
    public Campeonato(ArrayList<Clube> clubes, boolean debug) {
    	this(clubes);
    	this.debug = debug;
    }

    public void jogarCampeonato(){

        this.clubes.forEach(clube -> clube.reset()); // Garante que todos os times vão começar com 0 pontos

        for(int i = 0; i<this.clubes.size(); i++) {
            for(int j = 0; j<this.clubes.size(); j++) {
                if(i != j) {
                    jogarPartida(this.clubes.get(i), this.clubes.get(j));
                }
            }
        }
    }

    private void jogarPartida(Clube m, Clube v) {
    	
    	// Preparando os valores dos gols
        Random random = new Random();
        int golsM = random.nextInt(5);
        int golsV = random.nextInt(5);

        // Debug
        String debugVitoria = "";
        int debugPontos = 0;

        if(golsM == golsV) {
            m.empatar();
            v.empatar();
     
            debugVitoria = "Foi empate";
            
        } else if(golsM > golsV) {
            m.ganhar(golsM - golsV);
            v.perder(golsM - golsV);
            
            debugVitoria = m.nome;
            debugPontos = golsM - golsV;
        } else {
            v.ganhar(golsV - golsM);
            m.perder(golsV - golsM);
            
            debugVitoria = v.nome;
            debugPontos = golsV - golsM;
        }

        // Debug
        if(debug) {
        	System.out.println("=========================");
            System.out.println("Ganhou: " + debugVitoria);
            System.out.println("Pontos: " + debugPontos);
            System.out.println("T1: " + m.nome);
            System.out.println("Pontos: " + golsM);
            System.out.println("T2: " + v.nome);
            System.out.println("Pontos: " + golsV);
            System.out.println("=========================");
            System.out.println("");
        }

    }

    public void getClassificacao(){
    	
        /* 
        Eu sei que isso seria infinitamente mais facil com Array comum
        mas eu queria ver uma forma de fazer isso com ArrayList ai virou essa bomba aí, mas funciona perfeitamente :D
        */
    	ArrayList<Clube> clubes = new ArrayList<Clube>(this.clubes); // Aqui eu crio uma cópia da lista original para não perder ela
    	
    	Clube lider = clubes.getFirst();
    	
    	int j = 0;
    	while(clubes.size() > 0) {
    		lider = clubes.getFirst();
    		
    		for(int i=0; i<clubes.size(); i++) {
            	Clube prox = clubes.get(i);
            	
            	if(lider.pontos == prox.pontos) {
            		if(lider.saldoGols < prox.saldoGols) {
            			lider = prox;
            		}
            	}
            	
            	if(lider.pontos < prox.pontos) {
            		lider = prox;
            	}
            }
    		
    		classificacao[j++] = lider;

    		int remover = clubes.indexOf(lider);
    		clubes.remove(remover);
    		
    	}
    	
    	
    	for(int i=0; i<clubesTam; i++) {
    		System.out.println("=========================");
    		System.out.println(classificacao[i].nome);
    		System.out.println(classificacao[i].pontos);
    		System.out.println(classificacao[i].saldoGols);
    		System.out.println("=========================");
    	}
    	
    }

    public void getCampeao() {
    	if(this.classificacao[0] != null) {
    		System.out.println("=========================");
    		System.out.println("PARABÉNS " + classificacao[0].nome.toUpperCase() + " VOCÊ FOI O GRANDE CAMPEÃO!!!");
    		System.out.println("=========================");
    	}
    }
}
