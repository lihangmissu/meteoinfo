/* Copyright 2012 Yaqiang Wang,
 * yaqiang.wang@gmail.com
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or (at
 * your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser
 * General Public License for more details.
 */
package org.meteoinfo.shape;

import org.meteoinfo.geoprocess.GeoComputation;
import org.meteoinfo.global.Extent;
import org.meteoinfo.global.MIMath;
import org.meteoinfo.global.PointD;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.meteoinfo.global.DataConvert;
import org.meteoinfo.jts.geom.Coordinate;
import org.meteoinfo.jts.geom.Geometry;
import org.meteoinfo.jts.geom.GeometryFactory;
import org.meteoinfo.jts.geom.LinearRing;

/**
 * Polygon class
 *
 * @author Yaqiang Wang
 */
public class Polygon {
    // <editor-fold desc="Variables">

    private List<PointD> _outLine;
    private List<List<PointD>> _holeLines;
    private Extent _extent;
    // </editor-fold>
    // <editor-fold desc="Constructor">

    public Polygon() {
        _outLine = new ArrayList<>();
        _holeLines = new ArrayList<>();
    }
    // </editor-fold>
    // <editor-fold desc="Get Set Methods">

    /**
     * Get outLine
     *
     * @return outLine point list
     */
    public List<PointD> getOutLine() {
        return _outLine;
    }

    /**
     * Set outLine point list
     *
     * @param outLine outLine point list
     */
    public void setOutLine(List<PointD> outLine) {
        _outLine = outLine;
        _extent = MIMath.getPointsExtent(outLine);
    }

    /**
     * Get hole lines
     *
     * @return hole lines
     */
    public List<List<PointD>> getHoleLines() {
        return _holeLines;
    }

    /**
     * Set hole lines
     *
     * @param holeLines hole lines list
     */
    public void setHoleLines(List<List<PointD>> holeLines) {
        _holeLines = holeLines;
    }

    /**
     * Get extent
     *
     * @return extent
     */
    public Extent getExtent() {
        return _extent;
    }

    /**
     * Get rings
     *
     * @return Rings
     */
    public List<List<PointD>> getRings() {
        List<List<PointD>> rings = new ArrayList<>();
        rings.add(_outLine);
        if (hasHole()) {
            rings.addAll(_holeLines);
        }

        return rings;
    }

    /**
     * Determine if the polygon has hole
     *
     * @return boolean
     */
    public boolean hasHole() {
        return (_holeLines.size() > 0);
    }

    /**
     * Get ring number - outline number + holeline number
     *
     * @return ring number
     */
    public int getRingNumber() {
        return _holeLines.size() + 1;
    }

    // </editor-fold>
    // <editor-fold desc="Methods">
    /**
     * Add a hole line
     *
     * @param points point list
     */
    public void addHole(List<PointD> points) {
        if (GeoComputation.isClockwise(points)) {
            Collections.reverse(points);
        }
        _holeLines.add(points);
    }
    
    /**
     * Remove a hole line
     * @param holeIdx Hole index
     */
    public void removeHole(int holeIdx){
        this._holeLines.remove(holeIdx);
    }

    /**
     * To geometry
     *
     * @param factory GeometryFactory
     * @return Geometry
     */
    public Geometry toGeometry(GeometryFactory factory) {
        PointD p;
        Coordinate[] cs = new Coordinate[_outLine.size()];
        for (int i = 0; i < cs.length; i++) {
            p = _outLine.get(i);
            cs[i] = new Coordinate(p.X, p.Y);
        }
        if (cs[0].x != cs[cs.length -1].x){
            cs = (Coordinate[])DataConvert.resizeArray(cs, cs.length + 1);
            cs[cs.length - 1] = new Coordinate(cs[0].x, cs[1].y);
        }
        LinearRing shell = factory.createLinearRing(cs);
        LinearRing[] holes = new LinearRing[this._holeLines.size()];
        int n;
        boolean isclose;
        for (int j = 0; j < holes.length; j++) {
            List<PointD> hole = this._holeLines.get(j);
            n = hole.size();
            isclose = true;
            if (n == 3) {
                n = 4;
                isclose = false;
            }
            cs = new Coordinate[n];
            for (int i = 0; i < hole.size(); i++) {
                p = hole.get(i);
                cs[i] = new Coordinate(p.X, p.Y);
            }      
            if (!isclose){
                cs[n - 1] = new Coordinate(hole.get(0).X, hole.get(0).Y);
            }
            holes[j] = factory.createLinearRing(cs);
        }
        return factory.createPolygon(shell, holes);
    }
    // </editor-fold>
}
