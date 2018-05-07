/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestUnit;

import Model.Floor;
import UI.JFrameGebouwen;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sa59053
 */
public class JFrameGebouwenTest1 {
    
    public JFrameGebouwenTest1() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRow method, of class JFrameGebouwen.
     */
    @Test
    public void testGetRow() throws IOException {
        System.out.println("getRow");
        JFrameGebouwen instance = new JFrameGebouwen();
        int expResult = 0;
        int result = instance.getRow();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRow method, of class JFrameGebouwen.
     */
    @Test
    public void testSetRow() throws IOException {
        System.out.println("setRow");
        int row = 0;
        JFrameGebouwen instance = new JFrameGebouwen();
        instance.setRow(row);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populateLSTSVG method, of class JFrameGebouwen.
     */
    @Test
    public void testPopulateLSTSVG() throws IOException {
        System.out.println("populateLSTSVG");
        JFrameGebouwen instance = new JFrameGebouwen();
        instance.populateLSTSVG();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populateGebouwTabel method, of class JFrameGebouwen.
     */
    @Test
    public void testPopulateGebouwTabel() throws Exception {
        System.out.println("populateGebouwTabel");
        JFrameGebouwen instance = new JFrameGebouwen();
        instance.populateGebouwTabel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fillJlist method, of class JFrameGebouwen.
     */
    @Test
    public void testFillJlist() throws Exception {
        System.out.println("fillJlist");
        JFrameGebouwen instance = new JFrameGebouwen();
        instance.fillJlist();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of JInputWH method, of class JFrameGebouwen.
     */
    @Test
    public void testJInputWH() throws IOException {
        System.out.println("JInputWH");
        JFrameGebouwen instance = new JFrameGebouwen();
        instance.JInputWH();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of JDraw method, of class JFrameGebouwen.
     */
    @Test
    public void testJDraw() throws IOException {
        System.out.println("JDraw");
        JFrameGebouwen instance = new JFrameGebouwen();
        instance.JDraw();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class JFrameGebouwen.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        JFrameGebouwen.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFloor method, of class JFrameGebouwen.
     */
    @Test
    public void testGetFloor() throws Exception {
        System.out.println("getFloor");
        JFrameGebouwen instance = new JFrameGebouwen();
        instance.getFloor();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVloeren method, of class JFrameGebouwen.
     */
    @Test
    public void testGetVloeren() throws IOException {
        System.out.println("getVloeren");
        JFrameGebouwen instance = new JFrameGebouwen();
        List<Floor> expResult = null;
        List<Floor> result = instance.getVloeren();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVloeren method, of class JFrameGebouwen.
     */
    @Test
    public void testSetVloeren() throws IOException {
        System.out.println("setVloeren");
        List<Floor> vloeren = null;
        JFrameGebouwen instance = new JFrameGebouwen();
        instance.setVloeren(vloeren);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSelectedFloorId method, of class JFrameGebouwen.
     */
    @Test
    public void testGetSelectedFloorId() throws IOException {
        System.out.println("getSelectedFloorId");
        JFrameGebouwen instance = new JFrameGebouwen();
        Long expResult = null;
        Long result = instance.getSelectedFloorId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSelectedFloorId method, of class JFrameGebouwen.
     */
    @Test
    public void testSetSelectedFloorId() throws IOException {
        System.out.println("setSelectedFloorId");
        Long selectedFloorId = null;
        JFrameGebouwen instance = new JFrameGebouwen();
        instance.setSelectedFloorId(selectedFloorId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSvg method, of class JFrameGebouwen.
     */
    @Test
    public void testGetSvg() throws IOException {
        System.out.println("getSvg");
        JFrameGebouwen instance = new JFrameGebouwen();
        String expResult = "";
        String result = instance.getSvg();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSvg method, of class JFrameGebouwen.
     */
    @Test
    public void testSetSvg() throws IOException {
        System.out.println("setSvg");
        String svg = "";
        JFrameGebouwen instance = new JFrameGebouwen();
        instance.setSvg(svg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearInput method, of class JFrameGebouwen.
     */
    @Test
    public void testClearInput() throws IOException {
        System.out.println("clearInput");
        JFrameGebouwen instance = new JFrameGebouwen();
        instance.clearInput();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putDesk method, of class JFrameGebouwen.
     */
    @Test
    public void testPutDesk() throws IOException {
        System.out.println("putDesk");
        JFrameGebouwen instance = new JFrameGebouwen();
        List<String> expResult = new ArrayList();
        expResult.add("Selecteer een desk ...");
        List<String> result = instance.putDesk();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of preset method, of class JFrameGebouwen.
     */
    @Test
    public void testPreset() throws IOException {
        System.out.println("preset");
        JFrameGebouwen instance = new JFrameGebouwen();
        List<String> expResult = null;
        List<String> result = instance.preset();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDesk1 method, of class JFrameGebouwen.
     */
    @Test
    public void testGetDesk1() throws IOException {
        System.out.println("getDesk1");
        JFrameGebouwen instance = new JFrameGebouwen();
        List<String> expResult = new ArrayList();
        expResult.add(null);
        List<String> result = instance.getDesk1();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDesk1 method, of class JFrameGebouwen.
     */
    @Test
    public void testSetDesk1() throws IOException {
        System.out.println("setDesk1");
        List<String> desk = null;
        JFrameGebouwen instance = new JFrameGebouwen();
        instance.setDesk1(desk);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class JFrameGebouwen.
     */
    @Test
    public void testGetValue() throws IOException {
        System.out.println("getValue");
        JFrameGebouwen instance = new JFrameGebouwen();
        String expResult = "";
        String result = instance.getValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValue method, of class JFrameGebouwen.
     */
    @Test
    public void testSetValue() throws IOException {
        System.out.println("setValue");
        String value = "";
        JFrameGebouwen instance = new JFrameGebouwen();
        instance.setValue(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPresets method, of class JFrameGebouwen.
     */
    @Test
    public void testGetPresets() throws IOException {
        System.out.println("getPresets");
        JFrameGebouwen instance = new JFrameGebouwen();
        List<String> expResult = null;
        List<String> result = instance.getPresets();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPresets method, of class JFrameGebouwen.
     */
    @Test
    public void testSetPresets() throws IOException {
        System.out.println("setPresets");
        List<String> presets = null;
        JFrameGebouwen instance = new JFrameGebouwen();
        instance.setPresets(presets);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
