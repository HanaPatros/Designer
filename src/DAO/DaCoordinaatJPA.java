/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Coördinaat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author sa59053
 */
public class DaCoordinaatJPA {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesigntoolPU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    Coördinaat coordinaat = new Coördinaat();
    List<Coördinaat> coordinaten = new ArrayList();
    public ArrayList<Coördinaat> getAll(){
         if (!em.isOpen()) {
            em = emf.createEntityManager();
        }
         
         coordinaten = new ArrayList<>();
        List<Coördinaat> coordinaten = (List<Coördinaat>) em.createQuery("SELECT t FROM Coördinaat t ").getResultList();
        return (ArrayList<Coördinaat>) coordinaten;
    }
}
