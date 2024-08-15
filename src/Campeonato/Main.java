package Campeonato;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Clube> clubes = new ArrayList<Clube>();
        clubes.add(new Clube("Flamengo"));
        clubes.add(new Clube("Vasco"));
        clubes.add(new Clube("Palmeiras"));
        clubes.add(new Clube("São Paulo"));

        // o true é para debug, pode ser removido
        Campeonato champ = new Campeonato(clubes);
        champ.getCampeao(); // Não faz nada se não tiver uma classficação
        champ.jogarCampeonato();
        champ.getClassificacao();
        champ.getCampeao();
        
        // O campeonato pode ser rodado quantas vezes quiser
        
        champ.jogarCampeonato();
        champ.getClassificacao();
        champ.getCampeao();
        
        
    }
}
