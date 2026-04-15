package client;

import java.io.File;
import lecture.LectureGraphe;
import modele.Graphe;

public class ClientGraphe {
    public static void main(String[] args) {
        Graphe petit1 = LectureGraphe.lecture(new File("graphes_exemples/petits_graphes/petitgraphe1.txt"));
        System.out.println(LectureGraphe.lecture(new File("graphes_exemples/petits_graphes/petitgraphe1.txt")));
        System.out.println("###############################################################");
        System.out.println(LectureGraphe.lecture(new File("graphes_exemples/petits_graphes/petitgraphe1.txt")));
        System.out.println("###############################################################");
        System.out.println(LectureGraphe.lecture(new File("graphes_exemples/petits_graphes/petitgraphe3.txt")));
        System.out.println("###############################################################");
        System.out.println(Graphe.grapheComplet(5).toString());
        System.out.println("###############################################################");
        System.out.println(Graphe.cycle(5));
        System.out.println("###############################################################");

        System.out.println(petit1.parcoursEnLargeur(0));
        System.out.println("###############################################################");
        System.out.println(Graphe.cycle(7).parcoursEnLargeur(0));
        System.out.println("###############################################################");
        System.out.println(Graphe.cycle(8).parcoursEnLargeur(0));
        System.out.println("###############################################################");
        System.out.println(Graphe.cycle(8));
        System.out.println(Graphe.cycle(8).plusCourtChemin(0,4));

    }
}
