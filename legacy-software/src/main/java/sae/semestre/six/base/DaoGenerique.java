package sae.semestre.six.base;

import java.io.Serializable;
import java.util.List;

public interface DaoGenerique<T, ID extends Serializable> {
    T trouverParId(ID id);
    List<T> trouverTout();
    void sauvegarder(T entite);
    void mettreAJour(T entite);
    void supprimer(T entite);
    void supprimerParId(ID id);
} 