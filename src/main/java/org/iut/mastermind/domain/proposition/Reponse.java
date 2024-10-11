package org.iut.mastermind.domain.proposition;

import java.util.ArrayList;
import java.util.List;
import static java.util.Collections.unmodifiableList;

public class Reponse {
    private final String motSecret;
    private final List<Lettre> resultat = new ArrayList<>();
    private int position;

    public Reponse(String mot) {
        this.motSecret = mot;
        for(int i = 0; i<motSecret.length(); i++) {resultat.add(Lettre.INCORRECTE);
        }
    }

    // on récupère la lettre à la position dans le résultat
    public Lettre lettre(int position) {
        return resultat.get(position);
    }

    // on construit le résultat en analysant chaque lettre
    // du mot proposé
    public void compare(String essai) {
        for(position = 0; position < motSecret.length(); position++) {
            Lettre lettre = evaluationCaractere(essai.charAt(position));
            resultat.set(position,lettre);
        }
    }

    // si toutes les lettres sont placées
    public boolean lettresToutesPlacees() {
        if(resultat.size()!=motSecret.length())
            return false;

        return resultat.stream().allMatch(l-> l.equals(Lettre.PLACEE));
    }

    public List<Lettre> lettresResultat() {
        return unmodifiableList(resultat);
    }

    // renvoie le statut du caractère
    private Lettre evaluationCaractere(char carCourant) {
        if(estPlace(carCourant)) {
            return Lettre.PLACEE;
        }
        if (estPresent(carCourant)){
            return Lettre.NON_PLACEE;
        }
        return Lettre.INCORRECTE;
    }

    // le caractère est présent dans le mot secret
    private boolean estPresent(char carCourant) {
        return motSecret.contains(carCourant+"");
    }

    // le caractère est placé dans le mot secret
    private boolean estPlace(char carCourant) {
        return carCourant == motSecret.charAt(position);
    }//par rapport à la position
}
