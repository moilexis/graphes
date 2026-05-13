package client;

import lecture.LectureGraphe;
import modele.GrapheOriente;

import java.io.File;

public class ClientGrapheOriente {
    public static void main (String args[]) {
        GrapheOriente g1 = LectureGraphe.lectureOriente(new File("graphes_exemples/graphes_orientes/graphe_oriente1.txt"));
        System.out.println(g1);
        /*
        System.out.println("###############################################################");
        GrapheOriente g2 = LectureGraphe.lectureOriente(new File("graphes_exemples/graphes_orientes/graphe_oriente2.txt"));
        System.out.println(g2);
        System.out.println("###############################################################");
        GrapheOriente g3 = LectureGraphe.lectureOriente(new File("graphes_exemples/graphes_orientes/graphe_oriente3.txt"));
        System.out.println(g3);
        System.out.println("###############################################################");
        GrapheOriente g4 = LectureGraphe.lectureOriente(new File("graphes_exemples/graphes_orientes/graphe_oriente4.txt"));
        System.out.println(g4);
        System.out.println("###############################################################");

         */
        System.out.println(g1.degresEntrants());
        System.out.println(g1.topologicalSort());
    }

}
