package org.meteoinfo;

import java.awt.Color;
import java.util.ArrayList;

import org.meteoinfo.data.GridData;
import org.meteoinfo.data.StationData;
import org.meteoinfo.data.mapdata.MapDataManage;
import org.meteoinfo.data.meteodata.DrawMeteoData;
import org.meteoinfo.data.meteodata.GridDataSetting;
import org.meteoinfo.data.meteodata.MeteoDataInfo;
import org.meteoinfo.geoprocess.analysis.InterpolationMethods;
import org.meteoinfo.geoprocess.analysis.InterpolationSetting;
import org.meteoinfo.global.Extent;
import org.meteoinfo.layer.MapLayer;
import org.meteoinfo.layer.VectorLayer;
import org.meteoinfo.legend.PolygonBreak;
import org.meteoinfo.legend.ColorBreak;
import org.meteoinfo.legend.LegendManage;
import org.meteoinfo.legend.LegendScheme;
import org.meteoinfo.legend.LegendType;
import org.meteoinfo.legend.PolygonBreak;
import org.meteoinfo.map.MapView;
import org.meteoinfo.shape.ShapeTypes;

public class Test1 {
	public static void main(String[] args) {
		try {
			MapView mapView = new MapView();
			mapView.setSize(860, 697);
		 Extent aExtent =  new Extent();
		 aExtent.maxX=140;
		 aExtent.minX=70;
		 aExtent.maxY=55;
		 aExtent.minY=16;
		 mapView.zoomToExtent(aExtent);
			MeteoDataInfo aDataInfo=new MeteoDataInfo();
			aDataInfo.openLonLatData("F:\\MeteoInfoDemo_2013-31-2\\MeteoInfoDemo\\bin\\Debug\\Sample\\rain_2008072220.csv");
			StationData stationData = aDataInfo.getStationData("Precipitation6h");
			 GridDataSetting aGDP = new GridDataSetting();
			 aGDP.dataExtent.minX = 60;
			 aGDP.dataExtent.maxX = 140;
			 aGDP.dataExtent.minY = 10;
			 aGDP.dataExtent.maxY = 60;
			 aGDP.xNum = 80;
			 aGDP.yNum = 80;
			 InterpolationSetting gridInterp = new InterpolationSetting();
			 gridInterp.setGridDataSetting(aGDP); 
			 
			 gridInterp.setInterpolationMethod(InterpolationMethods.IDW_Radius);
			 gridInterp.setRadius(2);
			 gridInterp.setMinPointNum(1);
			 double[] X = new double[1];
			 double[] Y = new double[1];
			 
			 
			 stationData.createGridXY(gridInterp.getGridDataSetting());
			 double[][] S = stationData.data;
//			 GridData gridData= stationData.interpolate_Radius(S, X, Y, gridInterp.getMinPointNum(), gridInterp.getRadius(),stationData.missingValue);
			 stationData.filterData_Radius(gridInterp.getRadius(), gridInterp.getGridDataSetting().dataExtent);
			 GridData gridData = stationData.interpolateData(gridInterp);
			/* PolygonBreak colorBreak1 = new PolygonBreak();
				colorBreak1.setStartValue(0);
				colorBreak1.setEndValue(0.1);
				colorBreak1.setColor(new Color(1,true));*/
				PolygonBreak colorBreak2 = new PolygonBreak();
				colorBreak2.setStartValue(0.1);
				colorBreak2.setEndValue(9.9);
				colorBreak2.setColor(new Color(166,242,143));
				PolygonBreak colorBreak3 = new PolygonBreak();
				colorBreak3.setStartValue(10);
				colorBreak3.setEndValue(24.9);
				colorBreak3.setColor(new Color(61,186,61));
				PolygonBreak colorBreak4 = new PolygonBreak();
				colorBreak4.setStartValue(25);
				colorBreak4.setEndValue(49.9);
				colorBreak4.setColor(new Color(97,184,255));
				PolygonBreak colorBreak5 = new PolygonBreak();
				colorBreak5.setStartValue(50.0);
				colorBreak5.setEndValue(99.9);
				colorBreak5.setColor(new Color(0,0,225));
				PolygonBreak colorBreak6 = new PolygonBreak();
				colorBreak6.setStartValue(100);
				colorBreak6.setEndValue(250);
				colorBreak6.setColor(new Color(250,0,250));
				/*PolygonBreak colorBreak7 = new PolygonBreak();
				colorBreak7.setStartValue(250);
				colorBreak7.setEndValue(Double.MAX_VALUE);
				colorBreak7.setColor(new Color(128,0,64));*/
				ArrayList<ColorBreak> list = new ArrayList<ColorBreak>();
				/*list.add(colorBreak1);*/
				list.add(colorBreak2);
				list.add(colorBreak3);
				list.add(colorBreak4);
				list.add(colorBreak5);
				list.add(colorBreak6);
				/*list.add(colorBreak7);*/
				//Create a legend scheme
				/*LegendScheme ls = LegendManage.createLegendSchemeFromGridData(gridData,LegendType.UniqueValue,ShapeTypes.Polygon );
				ls.setLegendBreaks(list);
				VectorLayer layer = DrawMeteoData.createContourLayer(gridData,ls, "Rain", "Rain",true );
				//Create a barb layer
				 */	
				  //Create legend scheme
	            boolean hasNoData = true;
	            LegendScheme aLS = LegendManage.createLegendSchemeFromGridData(gridData, LegendType.GraduatedColor,
	                ShapeTypes.Polygon);
	            ((PolygonBreak)aLS.getLegendBreaks().get(0)).setDrawFill(true);
	            aLS.setLegendBreaks(list);
	            //Create layer
	            VectorLayer aLayer  = DrawMeteoData.createShadedLayer(gridData, aLS, "Rain", "Rain",false);
	            String aFile1 =  "D:\\Users\\lihang\\Downloads\\MeteoInfo_Java_1.4.3R1_Files\\MeteoInfo\\map\\bou1_4l.shp";
		   		 MapLayer layer1 = MapDataManage.loadLayer(aFile1);
		   		/*String aFile2 =  "D:\\Users\\lihang\\Downloads\\MeteoInfo_Java_1.4.3R1_Files\\MeteoInfo\\map\\bou2_4p.shp";
		   		 MapLayer layer2 = MapDataManage.loadLayer(aFile2);*/
//		   		mapView.addLayer(layer2);
		   		mapView.addLayer(layer1);
				mapView.addLayer(aLayer);
				mapView.exportToPicture("F:\\a.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	     
	}
}
