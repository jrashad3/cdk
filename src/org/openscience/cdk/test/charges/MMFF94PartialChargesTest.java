/*
 *  $RCSfile$
 *  $Author$
 *  $Date$
 *  $Revision$
 *
 *  Copyright (C) 2004-2005  The Chemistry Development Kit (CDK) project
 *
 *  Contact: cdk-devel@lists.sourceforge.net
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2.1
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.openscience.cdk.test.charges;

import org.openscience.cdk.modeling.builder3d.*;
import org.openscience.cdk.charges.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.openscience.cdk.tools.HydrogenAdder;
import org.openscience.cdk.Molecule;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.exception.CDKException;

/**
 *  TestSuite that runs a test for the MMFF94PartialCharges.
 *
 * @cdk.module test
 *
 *@author        cubic
 *@created       2004-11-04
 */

public class MMFF94PartialChargesTest extends TestCase {

	/**
	 *  Constructor for the MMFF94PartialChargesTest object
	 */
	public MMFF94PartialChargesTest() { }


	/**
	 *  A unit test suite for JUnit
	 *
	 *@return    The test suite
	 */
	public static Test suite() {
		return new TestSuite(MMFF94PartialChargesTest.class);
	}


	/**
	 *  A unit test for JUnit with beta-amino-acetic-acid
	 *
	 *@exception  ClassNotFoundException  Description of the Exception
	 *@exception  CDKException            Description of the Exception
	 *@exception  java.lang.Exception     Description of the Exception
	 */
	public void testMMFF94PartialCharges() throws ClassNotFoundException, CDKException, java.lang.Exception {
		double [] testResult={-0.99,0.314,0.66,-0.57,-0.65,0.36,0.36,0,0,0.5};
		HydrogenAdder hAdder = new HydrogenAdder();
		SmilesParser sp = new SmilesParser();
		Molecule ac = sp.parseSmiles("NCC(=O)O");
		hAdder.addExplicitHydrogensToSatisfyValency(ac);
		MMFF94PartialCharges mmff = new MMFF94PartialCharges();
		mmff.assignMMFF94PartialCharges(ac);
		for (int i = 0; i < ac.getAtoms().length; i++) {
			assertEquals(testResult[i], ((Double)ac.getAtomAt(i).getProperty("MMFF94charge")).doubleValue(), 0.05);
			//System.out.println("CHARGE AT " + ac.getAtomAt(i).getSymbol() + " " + ac.getAtomAt(i).getProperty("MMFF94charge"));
		}
	}
}

