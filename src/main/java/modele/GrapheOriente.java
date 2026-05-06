package modele;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class GrapheOriente {
    private String nom;
    private Map<Integer, Set<Integer>> voisinsSortants;

    public GrapheOriente(String nomGraphe, Map<Integer, Set<Integer>> voisins) {
        this.nom = nomGraphe;
        this.voisinsSortants = voisins;
    }
    public int ordre (){
        return voisinsSortants.size();
    }
    public int taille (){
        int taille = 0;
        for (Integer sommet : voisinsSortants.keySet()) {
            taille += voisinsSortants.get(sommet).size();
        }
        return taille;
    }
    public String getNom() {
        return nom;
    }
    public static GrapheOriente graphePlein(int n) {
        TreeMap<Integer, Set<Integer>> voisins = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            voisins.put(i, new TreeSet<Integer>());
            for (int j = 0; j < n; j++) {
                voisins.get(i).add(j);
            }
        }
        return new GrapheOriente("Graphe Complet Oriente", voisins);
    }
    public String toString (){
        String res= "";
        for (int i : voisinsSortants.keySet() ){
            System.out.println(i+" courant ");
            res += "* sommet "+ i + " - degré " + voisinsSortants.get(i).size() +", voisins : "+ voisinsSortants.get(i)+"\n";
        }

        return "Nom du Graphe : "+ nom + "\n"
                + "Ordre : " + this.ordre() +" - "+ "Taille : " + this.taille() + "\n" +
                res;

    }
}
