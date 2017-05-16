package org.meteoinfo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.meteoinfo.data.GridData;
import org.meteoinfo.data.meteodata.DrawMeteoData;
import org.meteoinfo.data.meteodata.Variable;
import org.meteoinfo.data.meteodata.netcdf.NetCDFDataInfo;
import org.meteoinfo.layer.VectorLayer;
import org.meteoinfo.legend.ColorBreak;
import org.meteoinfo.legend.LegendManage;
import org.meteoinfo.legend.LegendScheme;
import org.meteoinfo.legend.LegendType;
import org.meteoinfo.map.MapView;
import org.meteoinfo.shape.ShapeTypes;


public class Test {

	public static void main(String[] args) {
		 // TODO add your handling code here:
        try {
			//Create a MeteoDataInfo object
			MapView mapView = new MapView();
			NetCDFDataInfo aDataInfo = new NetCDFDataInfo();

			//Open GrADS data file
			String dataDir = "F:\\";
			String fileName = dataDir + "air.mon.ltm.nc";
			aDataInfo.readDataInfo(fileName);
			
			GridData gridData = aDataInfo.getGridData_LonLat(0, 3, 0);
			/*GridArray gridArray = aDataInfo.getGridArray("Rain");
			aDataInfo.arrayToGrid();*/
			
			ColorBreak colorBreak1 = new ColorBreak();
			colorBreak1.setStartValue(0);
			colorBreak1.setEndValue(0.1);
			colorBreak1.setColor(new Color(Color.TRANSLUCENT));
			ColorBreak colorBreak2 = new ColorBreak();
			colorBreak2.setStartValue(0.1);
			colorBreak2.setEndValue(9.9);
			colorBreak2.setColor(new Color(166,242,143));
			ColorBreak colorBreak3 = new ColorBreak();
			colorBreak3.setStartValue(10);
			colorBreak3.setEndValue(24.9);
			colorBreak3.setColor(new Color(61,186,61));
			ColorBreak colorBreak4 = new ColorBreak();
			colorBreak4.setStartValue(25);
			colorBreak4.setEndValue(49.9);
			colorBreak4.setColor(new Color(97,184,255));
			ColorBreak colorBreak5 = new ColorBreak();
			colorBreak5.setStartValue(50.0);
			colorBreak5.setEndValue(99.9);
			colorBreak5.setColor(new Color(0,0,225));
			ColorBreak colorBreak6 = new ColorBreak();
			colorBreak6.setStartValue(100);
			colorBreak6.setEndValue(250);
			colorBreak6.setColor(new Color(250,0,250));
			ColorBreak colorBreak7 = new ColorBreak();
			colorBreak7.setStartValue(250);
			colorBreak7.setEndValue(Double.MAX_VALUE);
			colorBreak7.setColor(new Color(128,0,64));
			ArrayList<ColorBreak> list = new ArrayList<ColorBreak>();
			list.add(colorBreak1);
			list.add(colorBreak2);
			list.add(colorBreak3);
			list.add(colorBreak4);
			list.add(colorBreak5);
			list.add(colorBreak6);
			list.add(colorBreak7);
			//Create a legend scheme
			LegendScheme ls = LegendManage.createLegendSchemeFromGridData(gridData,LegendType.UniqueValue,ShapeTypes.Image );
			ls.setLegendBreaks(list);
			VectorLayer layer = DrawMeteoData.createContourLayer(gridData,ls,"NetCDF","ll",true );
			//Create a barb layer
			mapView.addLayer(layer);
			mapView.exportToPicture("F:\\a.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
