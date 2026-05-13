package modele;

import org.javatuples.Pair;

import java.lang.reflect.Array;
import java.util.*;

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
    public Map<Integer,Integer> degresEntrants () {
        Map<Integer,Integer> degresEntrants = new TreeMap<>();
        for (Integer sommet : voisinsSortants.keySet()) {
            degresEntrants.put(sommet, 0);
        }
        for  (Integer sommet : voisinsSortants.keySet()) {
            for (Integer accessible : voisinsSortants.get(sommet)){
                degresEntrants.put(accessible, degresEntrants.get(accessible)+1);
            };
        }
        return degresEntrants;
    }
    public ArrayList<Integer> topologicalSort() {
        Map<Integer,Integer> degresEntrants = degresEntrants();
        ArrayList<Integer> liste_topo = new ArrayList<>();
        //on ajoute à la liste les sommets de degré 0
        for (Integer sommet : degresEntrants.keySet()) {
            if (degresEntrants.get(sommet) == 0) {
                liste_topo.add(sommet);}}
        ArrayList<Integer> num = new ArrayList<>();
        while (!liste_topo.isEmpty()) {
            Integer sommet = liste_topo.get(0);
            for (Integer voisin : voisinsSortants.get(sommet)) {
                degresEntrants.put(voisin, degresEntrants.get(voisin)-1);
                if (degresEntrants.get(voisin) == 0) {
                    liste_topo.add(voisin);
                }
            }
            num.add(sommet);
            liste_topo.remove(0);
        }
        return num;

    }
    public boolean verifieTopologic (ArrayList<Integer> ordre){
        if (!(ordre.size() == voisinsSortants.keySet().size())){
            return false;
        }
        TreeSet<Integer> visites = new TreeSet();
        for (Integer sommet : ordre ){
            return true ;
        }
        return true;
    }
    public Pair<ArrayList<Integer>,ArrayList<Integer>> parcoursEnProfondeur (Integer depart){
        ArrayList<Integer> prof = new ArrayList<>();
        Integer courant ;
        ArrayList<Integer> pile = new ArrayList<>();
        pile.add(depart);
        ArrayList<Integer> depilement = new ArrayList<>();
        while (!pile.isEmpty()){
            courant = pile.get(0);
            if (!prof.contains(courant)){
                prof.add(courant);
                for (Integer voisin : voisinsSortants.get(courant)){
                    if (!prof.contains(voisin)){
                        pile.add(voisin);
                    }
                }
            }
            pile.remove(0);
            depilement.add(courant);

        }
        return new Pair<>(prof,depilement);
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
