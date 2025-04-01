package sae.semestre.six.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class DaoHibernateAbstrait<T, ID extends Serializable> implements DaoGenerique<T, ID> {
    
    private final Class<T> classeEntite;
    
    @PersistenceContext
    private EntityManager gestionnaireEntite;
    
    @SuppressWarnings("unchecked")
    public DaoHibernateAbstrait() {
        this.classeEntite = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    protected EntityManager getGestionnaireEntite() {
        return gestionnaireEntite;
    }
    
    @Override
    public T trouverParId(ID id) {
        try {
            return gestionnaireEntite.find(classeEntite, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<T> trouverTout() {
        try {
            return gestionnaireEntite.createQuery("from " + classeEntite.getName())
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    @Override
    public void sauvegarder(T entite) {
        try {
            gestionnaireEntite.persist(entite);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    @Override
    public void mettreAJour(T entite) {
        try {
            gestionnaireEntite.merge(entite);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    @Override
    public void supprimer(T entite) {
        try {
            gestionnaireEntite.remove(entite);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    @Override
    public void supprimerParId(ID id) {
        T entite = trouverParId(id);
        if (entite != null) {
            supprimer(entite);
        }
    }
} 