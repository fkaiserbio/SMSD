/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openscience.smsd.algorithm.mcsplus;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Syed Asad Rahman <asad@ebi.ac.uk>
 *
 * @author Syed Asad Rahman <asad at ebi.ac.uk>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({org.openscience.smsd.algorithm.mcsplus.MCSPlusHandlerTest.class,
    org.openscience.smsd.algorithm.mcsplus.IsomorphismMCSPlusTest.class})
public class McsplusSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}
