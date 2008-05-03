/* $Revision$ $Author$ $Date$
 * 
 * Copyright (C) 2007  Egon Willighagen <egonw@users.sf.net>
 * 
 * Contact: cdk-devel@lists.sourceforge.net
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
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
package org.openscience.cdk.tools.manipulator;

import org.junit.Assert;
import org.junit.Test;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.RingSet;
import org.openscience.cdk.interfaces.IRing;
import org.openscience.cdk.NewCDKTestCase;
import org.openscience.cdk.tools.manipulator.RingSizeComparator;

/**
 * @cdk.module test-standard
 */
public class RingSizeComparatorTest extends NewCDKTestCase {
    
    public RingSizeComparatorTest() {
        super();
    }

    @Test
    public void testRingSizeComparator_int() {
		RingSizeComparator comp = new RingSizeComparator(RingSet.LARGE_FIRST);
		Assert.assertNotNull(comp);
	}

    @Test
    public void testCompare() {
        DefaultChemObjectBuilder builder = DefaultChemObjectBuilder.getInstance();
		IRing cycloPentane = builder.newRing(5, "C");
		IRing cycloHexane = builder.newRing(6, "C");
        IRing cycloHexane2 = builder.newRing(6, "C");

        RingSizeComparator ringSizeComparator = new RingSizeComparator(RingSizeComparator.LARGE_FIRST);
        Assert.assertTrue(ringSizeComparator.compare(cycloHexane, cycloPentane) == -1);
        Assert.assertTrue(ringSizeComparator.compare(cycloPentane, cycloHexane) == 1);
        Assert.assertTrue(ringSizeComparator.compare(cycloHexane, cycloHexane2) == 0);

        ringSizeComparator = new RingSizeComparator(RingSizeComparator.SMALL_FIRST);
        Assert.assertTrue(ringSizeComparator.compare(cycloHexane, cycloPentane) == 1);
        Assert.assertTrue(ringSizeComparator.compare(cycloPentane, cycloHexane) == -1);
        Assert.assertTrue(ringSizeComparator.compare(cycloHexane, cycloHexane2) == 0);
    }

}


