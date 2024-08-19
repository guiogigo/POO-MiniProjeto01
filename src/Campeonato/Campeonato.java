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
        //int debugPontos = 0;

        if(golsM == golsV) {
            m.empatar();
            v.empatar();
     
            debugVitoria = "Foi empate";
            
        } else if(golsM > golsV) {
            m.ganhar(golsM - golsV);
            v.perder(golsM - golsV);
            
            debugVitoria = m.nome;
            //debugPontos = golsM - golsV;
        } else {
            v.ganhar(golsV - golsM);
            m.perder(golsV - golsM);
            
            debugVitoria = v.nome;
            //debugPontos = golsV - golsM;
        }

        // Debug
        if(debug) {
        	//int maiorNomeDeClub = getMaiorNomeDeClube();
            System.out.println("=> " + m.nome + " " + golsM + " x " + golsV + " " + v.nome);
            System.out.println("| " + debugVitoria + " foi o vencedor!\n");
        }

    }

    public String getClassificacao(){
    	
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

        // Aqui é pra monstar a string da tabela
        // Eu sei que ta feio mas eu juro que faz sentido

        int maiorNomeDeClub = getMaiorNomeDeClube();
        String tabelaClassificacao = "+-"+("-".repeat(maiorNomeDeClub))+"----------"+"--------+\n";
        tabelaClassificacao += "| NOME"+(" ".repeat(maiorNomeDeClub > 4 ? maiorNomeDeClub - 4 : 0))+" | PONTOS | SALDO |\n";
        tabelaClassificacao += "+-"+("-".repeat(maiorNomeDeClub))+"----------"+"--------+\n";

        for(int i=0; i<clubesTam; i++) {
            tabelaClassificacao += "| " + classificacao[i].nome + (" ".repeat(maiorNomeDeClub - classificacao[i].nome.length())) + " |"
            + "   " + (classificacao[i].pontos >= 10 ? "" : "0") + classificacao[i].pontos + "   |" 
            + "   " + (classificacao[i].saldoGols >= 10 || classificacao[i].saldoGols < 0 ? "" : "0") + classificacao[i].saldoGols + "  |\n";
            tabelaClassificacao += "+-"+("-".repeat(maiorNomeDeClub))+"----------"+"--------+\n";            
        }

        return tabelaClassificacao;
    	
    }

    public void getCampeao() {
    	if(this.classificacao[0] != null) {
            // Só firula
            System.out.println("                       ___    \r");
            System.out.println("   o__        o__     |   |\\  \r");
            System.out.println("  /|          /\\      |   |X\\ \r");
            System.out.println("  / > o        <\\     |   |XX\\");

            String texto = "PARABÉNS " + classificacao[0].nome.toUpperCase() + " VOCÊ FOI O GRANDE CAMPEÃO!!!";
    		System.out.println("=".repeat(texto.length()));
    		System.out.println(texto);
    		System.out.println("=".repeat(texto.length()));
    	}

    }

    private int getMaiorNomeDeClube() {
        int maior = 0;
        for(Clube clube : clubes){
            if(clube.nome.length() > maior) {
                maior = clube.nome.length();
            }
        }
        return maior;
    }
}
