/*
 * Copyright 2012 Yaqiang Wang,
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

import org.meteoinfo.global.Extent;
import org.meteoinfo.global.PointD;
import java.util.ArrayList;
import java.util.List;
import org.meteoinfo.jts.geom.Coordinate;
import org.meteoinfo.jts.geom.Geometry;
import org.meteoinfo.jts.geom.GeometryFactory;

/**
 * Point shape class
 * 
 * @author Yaqiang Wang
 */
public class PointShape extends Shape implements Cloneable{
    // <editor-fold desc="Variables">

    private PointD _point = new PointD();
    // </editor-fold>
    // <editor-fold desc="Constructor">

    /**
     * Constructor
     */
    public PointShape(){
        
    }
    
    /**
     * Constructor
     * @param geometry Geometry
     */
    public PointShape(Geometry geometry) {
        Coordinate c = geometry.getCoordinate();
        this.setPoint(new PointD(c.x, c.y));
    }
    // </editor-fold>
    // <editor-fold desc="Get Set Methods">
    
    @Override
    public ShapeTypes getShapeType(){
        return ShapeTypes.Point;
    }
    
    /**
     * To geometry method
     * @param factory GeometryFactory
     * @return Geometry
     */
    @Override
    public Geometry toGeometry(GeometryFactory factory){
        Coordinate c = new Coordinate(_point.X, _point.Y);        
        return factory.createPoint(c);
    };

    /**
     * Get point
     * 
     * @return point
     */
    public PointD getPoint() {
        return _point;
    }

    /**
     * Set point
     * 
     * @param aPoint point
     */
    public void setPoint(PointD aPoint) {
        _point = aPoint;
        Extent aExtent = new Extent();
        aExtent.minX = _point.X;
        aExtent.maxX = _point.X;
        aExtent.minY = _point.Y;
        aExtent.maxY = _point.Y;
        this.setExtent(aExtent);
    }

    // </editor-fold>
    // <editor-fold desc="Methods">

    /**
     * Get points
     * 
     * @return point list
     */
    @Override
    public List<PointD> getPoints() {
        List<PointD> pList = new ArrayList<>();
        pList.add(_point);

        return pList;
    }

    /**
     * Set points
     * 
     * @param points point list
     */
    @Override
    public void setPoints(List<? extends PointD> points) {
        setPoint(points.get(0));
    }

    /**
     * Clone
     * @return PointShape
     */
    //@Override
    public Object clone_back() {
        PointShape o = (PointShape)super.clone();
        return o;
    }
    
    /**
     * Clone
     *
     * @return PolygonShape
     */
    @Override
    public Object clone() {
        PointShape ps = new PointShape();
        ps.setValue(this.getValue());
        ps.setPoint((PointD)_point.clone());
        ps.setVisible(this.isVisible());
        ps.setSelected(this.isSelected());
        ps.setLegendIndex(this.getLegendIndex());
        
        return ps;
    }
    // </editor-fold>
}
