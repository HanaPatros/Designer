/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Model.Coordinaat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author sa59053
 */
public class Preset {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("DesigntoolPU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    Coordinaat cd = new Coordinaat();
    private int x;
    private int y;
    private int width;
    private int height;
    private String desk;
    Canvas canvas;


    
    public Preset(){
        
    }
    public void tweeOpTwee(){
        desk1();
        desk2();
        desk5();
        desk6();
    }
    public void vierOpVier(){
        desk1();
        desk2();
        desk3();
        desk4();
        desk5();
        desk6();
        desk7();
        desk8();
    }
    public void achtOpAcht(){
        desk1();
        desk2();
        desk3();
        desk4();
        desk5();
        desk6();
        desk7();
        desk8();
        desk9();
        desk10();
        desk11();
        desk12();
        desk13();
        desk14();
        desk15();
        desk16();
    }
    public void desk1(){
        canvas = new Canvas();
        x = 10;
        y = 10;
        width = 150;
        height = 150;
        desk = "desk1";
         opslaan();
        
    }
    public void desk2(){
        x = 170;
        y = 10;
        width = 150;
        height = 150;
        desk = "desk2";
         opslaan();       
        
    }
     public void desk3(){
        x = 330;
        y = 10;
        width = 150;
        height = 150;
        desk = "desk3";
         opslaan();       
        
    }
      public void desk4(){
        x = 490;
        y = 10;
        width = 150;
        height = 150;
        desk = "desk4";
         opslaan();       
        
    }
       public void desk5(){
        x = 10;
        y = 170;
        width = 150;
        height = 150;
        desk = "desk5";
         opslaan();       
        
    }
       public void desk6(){
        x = 170;
        y = 170;
        width = 150;
        height = 150;
        desk = "desk6";
         opslaan();       
        
    }
        public void desk7(){
        x = 330;
        y = 170;
        width = 150;
        height = 150;
        desk = "desk7";
         opslaan();       
        
    }
        public void desk8(){
        x = 490;
        y = 170;
        width = 150;
        height = 150;
        desk = "desk8";
         opslaan();       
        
    }
        public void desk9(){
        x = 10;
        y = 360;
        width = 150;
        height = 150;
        desk = "desk9";
         opslaan();       
        
    }
        public void desk10(){
        x = 170;
        y = 360;
        width = 150;
        height = 150;
        desk = "desk10";
         opslaan();       
        
    }
        public void desk11(){
        x = 330;
        y = 360;
        width = 150;
        height = 150;
        desk = "desk11";
         opslaan();       
        
    }
        public void desk12(){
        x = 490;
        y = 360;
        width = 150;
        height = 150;
        desk = "desk12";
         opslaan();       
        
    }
        public void desk13(){
        x = 10;
        y = 520;
        width = 150;
        height = 150;
        desk = "desk13";
         opslaan();       
        
    }
        public void desk14(){
        x = 170;
        y = 520;
        width = 150;
        height = 150;
        desk = "desk14";
         opslaan();       
        
    }
        public void desk15(){
        x = 330;
        y = 520;
        width = 150;
        height = 150;
        desk = "desk15";
         opslaan();       
        
    }
        public void desk16(){
        x = 490;
        y = 520;
        width = 150;
        height = 150;
        desk = "desk16";
         opslaan();       
        
    }
        
      
    public void opslaan() {
        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }            
       
        cd.setX1(x);
        cd.setY1(y);
        cd.setWidth1(width);
        cd.setHeight1(height); 
        cd.setDeskId(desk);
        em.getTransaction().begin();

        try {           
            em.merge(cd);
            em.getTransaction().commit();

        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }        
    }
    
}
