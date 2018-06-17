/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Coördinaat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author sa59053
 */
public class DaCoördinaatJPA {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesigntoolPU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    Coördinaat coördinaat = new Coördinaat();
    List<Coördinaat> coördinaten;

    public List<Coördinaat> getAllDesks() {
        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }

        coördinaten = new ArrayList<>();
        coördinaten = (List<Coördinaat>) em.createQuery("SELECT t FROM Coördinaat t ").getResultList();
        return coördinaten;
    }

    public void removeAll() {
        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }
        em.getTransaction().begin();

        try {

            TypedQuery<Coördinaat> query = (TypedQuery<Coördinaat>) em.createQuery("DELETE FROM Coördinaat c ", Coördinaat.class);
            query.executeUpdate();
            em.getTransaction().commit();

        } catch (Exception e) {
        } finally {
            em.close();
        }
    }

    public void save(Coördinaat coördinaat) {

        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }
        em.getTransaction().begin();
        try {

            em.merge(coördinaat);

            em.getTransaction().commit();

        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Attention: Desk is already in use!",
                    "                                                        Error message",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        } finally {
            em.close();
        }

    }

    public void edit(Coördinaat coördinaat) throws IOException {
        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }
        em.getTransaction().begin();
        try {

            em.merge(coördinaat);

            em.getTransaction().commit();

        } catch (Exception ex) {

            javax.swing.JOptionPane.showMessageDialog(null, "Attention: The selected desk doesn't exist!",
                    "Error message",
                    javax.swing.JOptionPane.ERROR_MESSAGE);

        } finally {
            em.close();
        }

    }

    public void removeById(String deskId) {
        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }
        em.getTransaction().begin();
        try {
            Coördinaat coord = em.createQuery("SELECT d FROM Coördinaat d WHERE d.deskId = :deskId  ", Coördinaat.class).setParameter("deskId", deskId).getSingleResult();
            Query query = em.createQuery("DELETE FROM Coördinaat c WHERE c.id = :coord", Coördinaat.class).setParameter("coord", coord.getId());
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Attention: the selected desk doesn't exist!",
                    "Error message",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        } finally {
            em.close();
        }

    }

    public Coördinaat getDeskById(String deskId) {
        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }
        em.getTransaction().begin();

        try {
            coördinaat = em.createQuery("SELECT d FROM Coördinaat d WHERE d.deskId = :deskId  ", Coördinaat.class).setParameter("deskId", deskId).getSingleResult();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Attention: The selected desk doesn't exist!",
                    "Error message",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            em.close();
        }

        return coördinaat;

    }

}
