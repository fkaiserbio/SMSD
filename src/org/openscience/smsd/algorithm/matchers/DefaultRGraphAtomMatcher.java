/* Copyright (C) 2009-2011  Syed Asad Rahman <asad@ebi.ac.uk>
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
 *
 * MX Cheminformatics Tools for Java
 *
 * Copyright (c) 2007-2009 Metamolecular, LLC
 *
 * http://metamolecular.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.openscience.smsd.algorithm.matchers;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.annotations.TestClass;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.isomorphism.matchers.IQueryAtom;
import org.openscience.cdk.isomorphism.matchers.IQueryAtomContainer;
import org.openscience.smsd.algorithm.matchers.ring.IRingMatcher;
import org.openscience.smsd.algorithm.matchers.ring.RingMatcher;

/**
 * Checks if atom is matching between query and target molecules.
 * @cdk.module smsd
 * @cdk.githash
 * @author Syed Asad Rahman <asad@ebi.ac.uk>
 */
@TestClass("org.openscience.cdk.smsd.algorithm.vflib.VFLibTest")
public final class DefaultRGraphAtomMatcher implements AtomMatcher {

    static final long serialVersionUID = -7861469841127327812L;
    private int maximumNeighbors;
    private String symbol = null;
    private IAtom qAtom = null;
    private boolean shouldMatchBonds;
    private boolean shouldMatchRings;
    private IRingMatcher ringMatcher;
    private IQueryAtom smartQueryAtom;

    /**
     * @return the shouldMatchBonds
     */
    public boolean isBondMatchFlag() {
        return shouldMatchBonds;
    }

    /**
     * @param shouldMatchBonds the shouldMatchBonds to set
     */
    public final void setBondMatchFlag(boolean shouldMatchBonds) {
        this.shouldMatchBonds = shouldMatchBonds;
    }

    /**
     * Constructor
     */
    public DefaultRGraphAtomMatcher() {
        this.qAtom = null;
        symbol = null;
        maximumNeighbors = -1;
        ringMatcher = null;
        smartQueryAtom = null;
        shouldMatchBonds = false;
        shouldMatchRings = false;
    }

    /**
     * Constructor
     * @param queryContainer query atom container
     * @param atom query atom
     * @param shouldMatchBonds bond matching flag
     * @param shouldMatchRings ring matching flag 
     */
    public DefaultRGraphAtomMatcher(IAtomContainer queryContainer, IAtom atom, boolean shouldMatchBonds, boolean shouldMatchRings) {
        this();
        this.qAtom = atom;
        this.symbol = atom.getSymbol();
        this.ringMatcher = new RingMatcher(atom);
        this.shouldMatchRings = shouldMatchRings;
        setBondMatchFlag(shouldMatchBonds);
    }

    /**
     * Constructor
     * @param smartQueryAtom query atom
     * @param container 
     */
    public DefaultRGraphAtomMatcher(IQueryAtom smartQueryAtom, IQueryAtomContainer container) {
        this();
        this.smartQueryAtom = smartQueryAtom;
        this.symbol = smartQueryAtom.getSymbol();
    }

    /**
     * Constructor
     * @param queryContainer query atom container
     * @param template query atom
     * @param blockedPositions
     * @param shouldMatchBonds bond matching flag
     * @param shouldMatchRings ring matching flag 
     */
    public DefaultRGraphAtomMatcher(IAtomContainer queryContainer, IAtom template, int blockedPositions, boolean shouldMatchBonds, boolean shouldMatchRings) {
        this(queryContainer, template, shouldMatchBonds, shouldMatchRings);
        this.shouldMatchRings = shouldMatchRings;
        this.maximumNeighbors = countSaturation(queryContainer, template) - blockedPositions;
    }

    /** {@inheritDoc}
     *
     * @param targetContainer
     * @param targetAtom
     * @return
     */
    @Override
    public boolean matches(IAtomContainer targetContainer, IAtom targetAtom) {
        if (smartQueryAtom != null && qAtom == null && smartQueryAtom.getSymbol() == null) {
            return smartQueryAtom.matches(targetAtom) ? true : false;
        } else if (!matchSymbol(targetAtom)) {
            return false;
        }

//        if (!matchMaximumNeighbors(targetContainer, targetAtom)) {
//            return false;
//        }

        if (shouldMatchRings) {
            if (matchRingAtoms(targetAtom)) {
                return ringMatcher.matches(targetAtom);
            } else {
                return matchNonRingAtoms(targetAtom);
            }
        }

        return true;
    }

    private boolean matchRingAtoms(IAtom tAtom) {
        return isRingAtom(qAtom) && isRingAtom(tAtom) ? true : false;
    }

    private boolean matchNonRingAtoms(IAtom tAtom) {
        return !isRingAtom(qAtom) && !isRingAtom(tAtom) ? true : false;
    }

    private boolean isRingAtom(IAtom atom) {
        return atom.getFlag(CDKConstants.ISINRING) ? true : false;
    }

    /**
     *
     * @param maximum numbers of connected atoms allowed
     */
    public void setMaximumNeighbors(int maximum) {
        this.maximumNeighbors = maximum;
    }

    /** {@inheritDoc}
     * @param symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    private boolean matchSymbol(IAtom atom) {
        if (symbol == null) {
            return false;
        }
        return symbol.equals(atom.getSymbol());
    }

    private boolean matchMaximumNeighbors(IAtomContainer targetContainer, IAtom targetAtom) {
        if (maximumNeighbors == -1 || !isBondMatchFlag()) {
            return true;
        }

        int maximumTargetNeighbors = countSaturation(targetContainer, targetAtom);
        return maximumTargetNeighbors >= maximumNeighbors;
    }

    private int countImplicitHydrogens(IAtom atom) {
        return (atom.getImplicitHydrogenCount() == null)
                ? 0 : atom.getImplicitHydrogenCount();
    }

    private int countSaturation(IAtomContainer container, IAtom atom) {
        return countNeighbors(container, atom) + countImplicitHydrogens(atom);
    }

    private int countNeighbors(IAtomContainer container, IAtom atom) {
        return container.getConnectedAtomsCount(atom);
    }
}
