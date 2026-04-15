package lecture;

// Ajouter l'indication du package
// et les imports nécessaires

import java.io.File;
import java.io.FileNotFoundException;
import modele.Graphe;
import java.util.*;

public class LectureGraphe {
    /**
     * La méthode lecture construit puis renvoie un graphe
     * à partir de données contenues dans le fichier reçu en paramètre
     * @param file : le fichier contenant les informations pour construire le graphe
     * @return le graphe construit
     */
    public static Graphe lecture (File file) {
        TreeMap<Integer, Set<Integer>> lesVoisins = new TreeMap<>();
        try {
            Scanner scannerLigne = new Scanner(file);
            Scanner scannerToken  ;
            String ligne;
            while (scannerLigne.hasNext()) {
                ligne = scannerLigne.nextLine();
                scannerToken = new Scanner(ligne);
                if (ligne.startsWith("V")) { // ligne intervalle des sommets
                    scannerToken.next(); //"V"
                    // le token suivant est un intervalle
                    // [0..4] par exemple pour petitgraphe1.txt
                    String intervalle = scannerToken.next();
                    String[] minEtMax = intervalle.split("[.\\[\\]]+");
                    int min = Integer.parseInt(minEtMax[1]);
                    int max = Integer.parseInt(minEtMax[2]);
                    for (int i = min; i <= max; i++) {
                        lesVoisins.put(i, new TreeSet<Integer>());
                    }
                }

                if (ligne.startsWith ("E")){  // ligne arête
                    scannerToken.next(); //"E"
                    int numSommetDepart = scannerToken.nextInt();
                    int numSommetArrivee = scannerToken.nextInt();
                    lesVoisins.get(numSommetDepart).add (numSommetArrivee);

                }
                scannerToken.close();
            }
            scannerLigne.close();

        }
        catch (NoSuchElementException |
               IllegalStateException |
               FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        return new Graphe(file.getName(), lesVoisins);
    }
}