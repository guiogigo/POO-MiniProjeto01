package Campeonato;

import java.util.ArrayList;

public class Teste {
    public static void main(String[] args) {

        ArrayList<Clube> clubes = new ArrayList<Clube>();
        clubes.add(new Clube("Flamengo"));
        clubes.add(new Clube("Vasco"));
        clubes.add(new Clube("Palmeiras"));
        clubes.add(new Clube("São Paulo"));

        // o true é para exibir todos os jogos, pode ser removido
        Campeonato champ = new Campeonato(clubes, true);
        champ.getCampeao(); // Não faz nada se não tiver uma classficação
        champ.jogarCampeonato();
        System.out.println(champ.getClassificacao());
        champ.getCampeao();
        
        /*
        // O campeonato pode ser rodado quantas vezes quiser
        champ.jogarCampeonato();
        System.out.println(champ.getClassificacao());
        champ.getCampeao();*/
    }
}
