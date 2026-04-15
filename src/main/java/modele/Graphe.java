package modele;

import java.util.*;

import com.sun.source.tree.Tree;
import org.javatuples.Pair;

import static java.lang.Double.POSITIVE_INFINITY;


public class Graphe {
    private String nomFichier;
    private TreeMap<Integer, Set<Integer>> voisinage ;
    private TreeMap<Integer, String> nomSommets;


    public Graphe(String nom, TreeMap<Integer, Set<Integer>> voisins ){
        nomFichier = nom;
        voisinage = voisins;
        nomSommets = new TreeMap<Integer, String>();
    }
    public Graphe (String nom, TreeMap<Integer, Set<Integer>> voisins, TreeMap<Integer, String> noms ){
        nomFichier = nom;
        voisinage = voisins;
        nomSommets = noms;
    }
    public int ordre (){
        return voisinage.size();
    }
    public int degre (Integer cible){
        return voisinage.get(cible).size();

    }

    public int taille (){
        int taille = 0;
        for (Set<Integer> voisins : voisinage.values() ) {
            taille += voisins.size();
        }
        return taille/2 ;
    }
    public Pair degreMinDegreMax(){
        int min = this.degre(voisinage.firstKey());
        int max = min;

        for (Integer sommet : voisinage.keySet()){
            int deg_sommet = this.degre(sommet);
            if (deg_sommet < min){
                min = deg_sommet;
            }
            else if (deg_sommet > max) {
                max = deg_sommet;
            }
        }
        return new Pair(min,max);
    };
    public static Graphe grapheComplet(int n) {
        TreeMap<Integer,Set<Integer>> voisins = new TreeMap<>();
        for (int idx = 0;idx < n;idx++) {
            voisins.put(idx,new TreeSet<Integer>());
            for (int indice = 0; indice < n; indice++) {
                if (idx != indice) {
                    voisins.get(idx).add(indice);
                }
            }
        }
        return new Graphe("Graphe Complet",voisins);
    }
    public static Graphe cycle (int n ){
        TreeMap<Integer,Set<Integer>> voisins = new TreeMap<>();
        for (int idx = 1;idx < n-1;idx++) {
            voisins.put(idx,new TreeSet<Integer>());
            voisins.get(idx).add(idx+1);
            voisins.get(idx).add(idx-1);
        }
        voisins.put(0,new TreeSet<Integer>());
        voisins.get(0).add(1);
        voisins.get(0).add(n-1);
        voisins.put(n-1,new TreeSet<Integer>());
        voisins.get(n-1).add(0);
        voisins.get(n-1).add(n-2);
        return new Graphe("cycle de longueur : "+ n ,voisins);
    }

    public Map<Integer,Pair<Integer,Integer>> parcoursEnLargeur (Integer depart){
        TreeMap<Integer,Integer> predec = new TreeMap();
        TreeMap<Integer,Integer> distance = new TreeMap();
        distance.put(depart,0);
        ArrayList file = new ArrayList();
        file.add(depart);
        for (Integer sommet : voisinage.keySet()){
            predec.put(sommet,null);
            if (sommet.compareTo(depart) != 0){
                distance.put(sommet,10000);
            }
        }
        while (file.size()>0){
            Integer courant = (Integer) file.get(0);
            file.remove(courant);
            for (Integer voisinCourant : voisinage.get(courant)){
                //System.out.println(voisinage.get(courant));
                if (predec.get(voisinCourant) == null && voisinCourant != depart){
                    file.add(voisinCourant);
                    predec.replace(voisinCourant,courant);
                    distance.replace(voisinCourant, distance.get(courant)+1);
                }
            }
        }
        TreeMap<Integer,Pair<Integer,Integer>> retour = new TreeMap<>();
        for (Integer sommet : voisinage.keySet()){
            retour.put(sommet,new Pair<>(predec.get(sommet),distance.get(sommet)));
        }
        return  retour ;
    }
    public ArrayList <Integer> plusCourtChemin (Integer depart, Integer arrivee){
        Map<Integer,Pair<Integer,Integer>> parcours =  parcoursEnLargeur(depart);
        Integer courant = arrivee;
        ArrayList retour = new ArrayList();
        retour.add(courant);
        while (courant != depart){
            Integer predec = parcours.get(courant).getValue0();
            retour.add(predec);
            courant = predec;
        }
        Collections.reverse(retour);
        return retour;
    }
    public String toString (){
        return nomFichier + "\n"+
                "ordre : " + this.ordre() + "\n"+
                "taille : " + this.taille()+ "\n"+
                "degré min : "+this.degreMinDegreMax().getValue0()+"  |  degré max"+this.degreMinDegreMax().getValue1()+ "\n nom des sommets"+
                nomSommets.toString()+ "\n voisinage : "+
                voisinage.toString();
    }
}
