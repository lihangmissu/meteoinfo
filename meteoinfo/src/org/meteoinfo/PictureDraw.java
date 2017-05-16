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
import org.meteoinfo.legend.ColorBreak;
import org.meteoinfo.legend.LegendManage;
import org.meteoinfo.legend.LegendScheme;
import org.meteoinfo.legend.LegendType;
import org.meteoinfo.legend.PolygonBreak;
import org.meteoinfo.map.MapView;
import org.meteoinfo.shape.ShapeTypes;

public class PictureDraw {
	private static MapView mapView;
	private static MeteoDataInfo aDataInfo;
	private static GridDataSetting aGDP;
	private static InterpolationSetting gridInterp;
	private static ArrayList<ColorBreak> list;
	private static LegendScheme aLS;
	private static ArrayList<MapLayer> layers;
	private static Extent aExtent;
	private static StationData stationData;
	private static String bou1_4l = "D:\\Users\\lihang\\Downloads\\MeteoInfo_Java_1.4.3R1_Files\\MeteoInfo\\map\\bou1_4l.shp";
	private static String bou2_4p = "D:\\Users\\lihang\\Downloads\\MeteoInfo_Java_1.4.3R1_Files\\MeteoInfo\\map\\bou2_4p.shp";
	private static String lonLatData = "F:\\MeteoInfoDemo_2013-31-2\\MeteoInfoDemo\\bin\\Debug\\Sample\\rain_2008072220.csv";
	private static String lonLatData_field = "Precipitation6h";
	private static String ctlData = "D:\\Users\\lihang\\Downloads\\MeteoInfo_Java_1.4.3R1_Files\\MeteoInfo\\sample\\GrADS\\model.ctl";

	public static MapView getMapView() {
		return mapView;
	}

	public static void setMapView(MapView mapView) {
		PictureDraw.mapView = mapView;
	}

	public static MeteoDataInfo getaDataInfo() {
		return aDataInfo;
	}

	public static void setaDataInfo(MeteoDataInfo aDataInfo) {
		PictureDraw.aDataInfo = aDataInfo;
	}

	public static GridDataSetting getaGDP() {
		return aGDP;
	}

	public static void setaGDP(GridDataSetting aGDP) {
		PictureDraw.aGDP = aGDP;
	}

	public static InterpolationSetting getGridInterp() {
		return gridInterp;
	}

	public static void setGridInterp(InterpolationSetting gridInterp) {
		PictureDraw.gridInterp = gridInterp;
	}


	public static ArrayList<ColorBreak> getList() {
		return list;
	}

	public static void setList(ArrayList<ColorBreak> list) {
		PictureDraw.list = list;
	}

	public static LegendScheme getaLS() {
		return aLS;
	}

	public static void setaLS(LegendScheme aLS) {
		PictureDraw.aLS = aLS;
	}

	public static ArrayList<MapLayer> getLayers() {
		return layers;
	}

	public static void setLayers(ArrayList<MapLayer> layers) {
		PictureDraw.layers = layers;
	}

	public static Extent getaExtent() {
		return aExtent;
	}

	public static void setaExtent(Extent aExtent) {
		PictureDraw.aExtent = aExtent;
	}

	public PictureDraw(Integer width, Integer height, Double minX, Double maxX, Double minY, Double maxY) {
		list = new ArrayList<ColorBreak>();
		layers = new ArrayList<MapLayer>();
		mapView = new MapView();
		aDataInfo = new MeteoDataInfo();
		aGDP = new GridDataSetting();
		gridInterp = new InterpolationSetting();
		gridInterp.setGridDataSetting(aGDP);
		mapView.setSize(width, height);
		aExtent = new Extent(minX, maxX, minY, maxY);
		mapView.zoomToExtent(aExtent);
	}

	public static void contourPolygon(Double minX, Double maxX, Double minY, Double maxY, Integer xNum, Integer yNum) {
		try {
			aDataInfo.openLonLatData(lonLatData);
			stationData = aDataInfo.getStationData(lonLatData_field);
			aGDP.dataExtent.minX = minX;
			aGDP.dataExtent.maxX = maxX;
			aGDP.dataExtent.minY = minY;
			aGDP.dataExtent.maxY = maxY;
			aGDP.xNum = xNum;
			aGDP.yNum = yNum;
			gridInterp.setInterpolationMethod(InterpolationMethods.IDW_Radius);
			gridInterp.setRadius(2);
			gridInterp.setMinPointNum(1);
			stationData.createGridXY(gridInterp.getGridDataSetting());
			double[][] S = stationData.data;
			stationData.filterData_Radius(gridInterp.getRadius(), gridInterp.getGridDataSetting().dataExtent);
			GridData gridData = stationData.interpolateData(gridInterp);
			boolean hasNoData = true;
			aLS = LegendManage.createLegendSchemeFromGridData(gridData, LegendType.GraduatedColor, ShapeTypes.Polygon);
			((PolygonBreak) aLS.getLegendBreaks().get(0)).setDrawFill(false);
			VectorLayer aLayer = DrawMeteoData.createShadedLayer(gridData, aLS, "Rain", "Rain", false);
			// Create layer
			layers.add(MapDataManage.loadLayer(bou1_4l));
			layers.add(MapDataManage.loadLayer(bou2_4p));
			layers.add(aLayer);
			for (MapLayer l : layers) {
				mapView.addLayer(l);
			}
			mapView.exportToPicture("F:\\a.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void contourPolyline(Double minX, Double maxX, Double minY, Double maxY, Integer xNum, Integer yNum) {
		try {
			aDataInfo.openLonLatData(lonLatData);
			stationData = aDataInfo.getStationData(lonLatData_field);
			aGDP.dataExtent.minX = minX;
			aGDP.dataExtent.maxX = maxX;
			aGDP.dataExtent.minY = minY;
			aGDP.dataExtent.maxY = maxY;
			aGDP.xNum = xNum;
			aGDP.yNum = yNum;
			gridInterp.setInterpolationMethod(InterpolationMethods.IDW_Radius);
			gridInterp.setRadius(2);
			gridInterp.setMinPointNum(1);
			stationData.createGridXY(gridInterp.getGridDataSetting());
			double[][] S = stationData.data;
			stationData.filterData_Radius(gridInterp.getRadius(), gridInterp.getGridDataSetting().dataExtent);
			GridData gridData = stationData.interpolateData(gridInterp);
			boolean hasNoData = true;
			aLS = LegendManage.createLegendSchemeFromGridData(gridData, LegendType.UniqueValue, ShapeTypes.Polygon);
/*			PolygonBreak colorBreak1 = new PolygonBreak();
			colorBreak1.setStartValue(0);
			colorBreak1.setEndValue(0.1);
			colorBreak1.setColor(new Color(Color.TRANSLUCENT));
*/			PolygonBreak colorBreak2 = new PolygonBreak();
			colorBreak2.setStartValue(0.1);
			colorBreak2.setEndValue(9.9);
			colorBreak2.setColor(new Color(166, 242, 143));
			PolygonBreak colorBreak3 = new PolygonBreak();
			colorBreak3.setStartValue(10);
			colorBreak3.setEndValue(24.9);
			colorBreak3.setColor(new Color(61, 186, 61));
			PolygonBreak colorBreak4 = new PolygonBreak();
			colorBreak4.setStartValue(25);
			colorBreak4.setEndValue(49.9);
			colorBreak4.setColor(new Color(97, 184, 255));
			PolygonBreak colorBreak5 = new PolygonBreak();
			colorBreak5.setStartValue(50.0);
			colorBreak5.setEndValue(99.9);
			colorBreak5.setColor(new Color(0, 0, 225));
			PolygonBreak colorBreak6 = new PolygonBreak();
			colorBreak6.setStartValue(100);
			colorBreak6.setEndValue(250);
			colorBreak6.setColor(new Color(250, 0, 250));
			PolygonBreak colorBreak7 = new PolygonBreak();
			colorBreak7.setStartValue(250);
			colorBreak7.setEndValue(Double.MAX_VALUE);
			colorBreak7.setColor(new Color(128, 0, 64));
			/*list.add(colorBreak1);*/
			list.add(colorBreak2);
			list.add(colorBreak3);
			list.add(colorBreak4);
			list.add(colorBreak5);
			list.add(colorBreak6);
			list.add(colorBreak7);
			aLS.setLegendBreaks(list);
			VectorLayer aLayer = DrawMeteoData.createShadedLayer(gridData, aLS, "Rain", "Rain", false);
			// Create layer
			layers.add(MapDataManage.loadLayer(bou1_4l));
			layers.add(MapDataManage.loadLayer(bou2_4p));
			layers.add(aLayer);
			for (MapLayer l : layers) {
				mapView.addLayer(l);
			}
			mapView.exportToPicture("F:\\b.png");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void uvwind() {
		try {
			aDataInfo.openGrADSData(ctlData);
			// Get GridData
			aDataInfo.setTimeIndex(2);
			aDataInfo.setLevelIndex(3);
			GridData uData = aDataInfo.getGridData("U");
			GridData vData = aDataInfo.getGridData("V");
			// Create a legend scheme
			LegendScheme aLS = LegendManage.createSingleSymbolLegendScheme(ShapeTypes.Point, Color.BLUE, 10);

			// Create a contour layer
			VectorLayer aLayer = DrawMeteoData.createGridBarbLayer(uData, vData, uData, aLS, true, "Barb_UV", true);
			layers.add(MapDataManage.loadLayer(bou1_4l));
			layers.add(MapDataManage.loadLayer(bou2_4p));
			layers.add(aLayer);
			for (MapLayer l : layers) {
				mapView.addLayer(l);
			}
			mapView.exportToPicture("F:\\c.png");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void label() {
		/*VectorLayer cityLayer = (VectorLayer) aLayer;
		LabelSet label = new LabelSet();
		label.setLabelColor(Color.RED);
		label.setLabelFont(new Font("宋体", 1, 8));
		label.setShadowColor(Color.white);
		label.setYOffset(0);
		label.setFieldName("NAME");
		label.setAvoidCollision(true);
		label.setLabelAlignType(AlignType.Center);
		label.setDrawShadow(false);
		label.setColorByLegend(false);
		cityLayer.setLabelSet(label);
		cityLayer.addLabels();*/

	}

	public static void main(String[] args) {
		PictureDraw pictureDraw = new PictureDraw(860, 697, 70.0, 140.0, 10.0, 60.0);
		// pictureDraw.uvwind();
		pictureDraw.contourPolygon(60.0, 140.0, 10.0, 60.0, 80, 80);
	}
}
