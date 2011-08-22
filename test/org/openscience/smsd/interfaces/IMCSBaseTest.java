
/* Copyright (C) 2009-2011 Syed Asad Rahman <asad@ebi.ac.uk>
 *
 * Contact: cdk-devel@lists.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * All we ask is that proper credit is given for our work, which includes
 * - but is not limited to - adding the above copyright notice to the beginning
 * of your source code files, and to any copyright notice that you may distribute
 * with programs based on this work.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.openscience.smsd.interfaces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IMolecule;
import org.openscience.cdk.isomorphism.matchers.IQueryAtomContainer;
import org.openscience.smsd.AtomAtomMapping;


/**
 * @author Syed Asad Rahman <asad@ebi.ac.uk>
 *
 * @cdk.module test-smsd
 */
public class IMCSBaseTest {

    public IMCSBaseTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of set method, of class IMCSBase.
     * @throws Exception
     */
    @Test
    public void testSet_AtomContainer_AtomContainer() throws Exception {
        System.out.println("set");
        IAtomContainer source = null;
        IAtomContainer target = null;
        IMCSBase instance = new IMCSBaseImpl();
        instance.set(source, target);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class IMCSBase.
     * @throws Exception
     */
    @Test
    public void testSet_IMolecule_IMolecule() throws Exception {
        System.out.println("set");
        IMolecule source = null;
        IMolecule target = null;
        IMCSBase instance = new IMCSBaseImpl();
        instance.set(source, target);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class IMCSBase.
     * @throws Exception
     */
    @Test
    public void testSet_IAtomContainer_IAtomContainer() throws Exception {
        System.out.println("set");
        IAtomContainer source = null;
        IAtomContainer target = null;
        IMCSBase instance = new IMCSBaseImpl();
        instance.set(source, target);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllAtomMapping method, of class IMCSBase.
     */
    @Test
    public void testGetAllAtomMapping() {
        System.out.println("getAllAtomMapping");
        IMCSBase instance = new IMCSBaseImpl();
        List<Map<IAtom, IAtom>> expResult = null;
        List<AtomAtomMapping> result = instance.getAllAtomMapping();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllMapping method, of class IMCSBase.
     */
    @Test
    public void testGetAllMapping() {
        System.out.println("getAllMapping");
        IMCSBase instance = new IMCSBaseImpl();
        List<Map<Integer, Integer>> expResult = null;
        List<Map<Integer, Integer>> result = instance.getAllMapping();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstAtomMapping method, of class IMCSBase.
     */
    @Test
    public void testGetFirstAtomMapping() {
        System.out.println("getFirstAtomMapping");
        IMCSBase instance = new IMCSBaseImpl();
        Map expResult = null;
        AtomAtomMapping result = instance.getFirstAtomMapping();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstMapping method, of class IMCSBase.
     */
    @Test
    public void testGetFirstMapping() {
        System.out.println("getFirstMapping");
        IMCSBase instance = new IMCSBaseImpl();
        Map expResult = null;
        Map result = instance.getFirstMapping();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IMCSBaseImpl implements IMCSBase {

        @Override
        public void set(IAtomContainer source, IAtomContainer target) throws CDKException {
        }

        @Override
        public void set(IQueryAtomContainer source, IAtomContainer target) throws CDKException {
        }

        @Override
        public List<AtomAtomMapping> getAllAtomMapping() {
            return null;
        }

        @Override
        public List<Map<Integer, Integer>> getAllMapping() {
            return null;
        }

        @Override
        public AtomAtomMapping getFirstAtomMapping() {
            return null;
        }

        @Override
        public Map<Integer, Integer> getFirstMapping() {
            return null;
        }
    }
}
