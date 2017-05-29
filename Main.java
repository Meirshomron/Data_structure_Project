import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;



public class Main {

	public static void main(String[] args) 
	{
		testA();
		testB();
		testC();

	}

	
	private static void testA()
	{
		Point[] points = {
				new Point(0, 0), 
				new Point(3, 1),
				new Point(2, 3),
				new Point(1, 2),
				new Point(4, 4),
				};
		
		PDT pdt=new PointDataStructure(points,points[3]);
		String testName;
		int expected;
		int result;
		
		testName = "A0";
		testExpectedPoints(testName, points, pdt.getAllPoints());

		int XLeft=3;
		int XRight=7;
		testName = "A1";
		expected = 2;
		result = pdt.numOfPointsInRange(XLeft, XRight);
		testExpected(testName, expected, result);
		
		XLeft=1;
		XRight=3;
		testName = "A2";
		double expectedD = 2.0;
		double resultD = pdt.averageHeightInRange(XLeft, XRight);
		testExpected(testName, expectedD, resultD);
		
		testName = "A3.1";
		Point[] expectedPoints1 = {
				new Point(1, 2),
				};
		testExpectedPoints(testName, expectedPoints1, pdt.getMedianPoints(1));
		testName = "A3.2";
		Point[] expectedPoints2 = { 
				new Point(3, 1),
				new Point(1, 2),
				};
		testExpectedPoints(testName, expectedPoints2, pdt.getMedianPoints(2));
		testName = "A3.3";
		Point[] expectedPoints3 = { 
				new Point(3, 1),
				new Point(2, 3),
				new Point(1, 2),
				};
		testExpectedPoints(testName, expectedPoints3, pdt.getMedianPoints(3));
		
		Point[] points2 = {
				new Point(1, 4), 
				new Point(2, 7),
				new Point(3, 15),
				new Point(7, 7),
				new Point(8, 2),
				new Point(9, 9),
				new Point(12, 5),
				new Point(13, 7),
				new Point(15, 15),
				new Point(35, 12),
				};
		
		pdt=new PointDataStructure(points2,points2[7]);
		
		
		testName = "A4";
		XLeft=12;
		XRight=100;
		Point[] expectedLargePoints = {
				new Point(12, 5),
				new Point(13, 7),
				new Point(15, 15),
				new Point(35, 12),
				};
		testExpectedPoints(testName, expectedLargePoints, pdt.getPointsInRange(XLeft, XRight));

		XLeft=3;
		XRight=7;
		testName = "A5";
		expected = 2;
		result = pdt.numOfPointsInRange(XLeft, XRight);
		testExpected(testName, expected, result);
		
		XLeft=7;
		XRight=9;
		testName = "A6";
		expectedD = 6.0;
		resultD = pdt.averageHeightInRange(XLeft, XRight);
		testExpected(testName, expectedD, resultD);
		
		testName = "A7.1";
		Point[] expectedPoints4 = {
				new Point(13, 7),
				};
		testExpectedPoints(testName, expectedPoints4, pdt.getMedianPoints(1));
		testName = "A7.2";
		Point[] expectedPoints5 = { 
				new Point(7, 7),
				new Point(13, 7),
				};
		testExpectedPoints(testName, expectedPoints5, pdt.getMedianPoints(2));
		testName = "A7.3";
		Point[] expectedPoints6 = { 
				new Point(7, 7),
				new Point(13, 7),
				new Point(9, 9),
				};
		testExpectedPoints(testName, expectedPoints6, pdt.getMedianPoints(3));
		testName = "A7.4";
		Point[] expectedPoints7 = { 
				new Point(2, 7),
				new Point(7, 7),
				new Point(13, 7),
				new Point(9, 9),
				};
		testExpectedPoints(testName, expectedPoints7, pdt.getMedianPoints(4));
		testName = "A7.5";
		Point[] expectedPoints8 = {
				new Point(2, 7),
				new Point(7, 7),
				new Point(13, 7),
				new Point(9, 9),
				new Point(35, 12),
				};
		testExpectedPoints(testName, expectedPoints8, pdt.getMedianPoints(5));
		
	}


	


	private static void testB()
	{
		
		final int NUM = 100;
		
		Point[] points = new Point[NUM];
		
		for (int i=0; i<NUM; ++i) 
		{
			points[i] = new Point(NUM-i-1, i);
		}
		
		PDT pdt=new PointDataStructure(points,points[NUM/2]);
		
		String testName;
		int expected;
		int result;
		
		testName = "B0";
		testExpectedPoints(testName, points, pdt.getAllPoints());
		
		int XLeft=3;
		int XRight=7;
		testName = "B1";
		expected = 5;
		result = pdt.numOfPointsInRange(XLeft, XRight);
		testExpected(testName, expected, result);
		
		XLeft=1;
		XRight=3;
		testName = "B2.1";
		double expectedD = 97.0;
		double resultD = pdt.averageHeightInRange(XLeft, XRight);
		testExpected(testName, expectedD, resultD);
		testName = "B2.2";
		XLeft=0;
		XRight=NUM;
		expectedD =((double) (NUM-1)*NUM)/(2*NUM);
		resultD = pdt.averageHeightInRange(XLeft, XRight);
		testExpected(testName, expectedD, resultD);
		
		XLeft=7;
		XRight=12;
		testName = "B3";
		Point[] expectedPoints1=
		{
			new Point(7,NUM-7-1),
			new Point(8,NUM-8-1),
			new Point(9,NUM-9-1),
			new Point(10,NUM-10-1),
			new Point(11,NUM-11-1),
			new Point(12,NUM-12-1),
		};
		testExpectedPoints(testName, expectedPoints1, pdt.getPointsInRange(XLeft,XRight));	
		
		
		int k=6;
		testName = "B4";
		int mid=NUM/2;
		Point[] expectedPoints2=
		{
			new Point(NUM-mid-1,mid),
			new Point(NUM-(mid-1)-1,mid-1),
			new Point(NUM-(mid+1)-1,mid+1),
			new Point(NUM-(mid-2)-1,mid-2),
			new Point(NUM-(mid+2)-1,mid+2),
			new Point(NUM-(mid-3)-1,mid-3),
		};
		testExpectedPoints(testName, expectedPoints2, pdt.getMedianPoints(k));
		
		testName = "B5";
		Point[] expectedPoints3=
		{
			new Point(NUM-mid-1,mid),
			new Point(NUM-(mid-1)-1,mid-1),
			new Point(NUM-(mid+1)-1,mid+1),
			new Point(NUM-(mid-2)-1,mid-2),
			new Point(NUM-(mid+2)-1,mid+2),
			new Point(NUM-(mid-3)-1,mid-3),
			new Point(NUM-(mid+3)-1,mid+3),
		};
		testExpectedPoints(testName, expectedPoints3, pdt.getMedianPoints(k+1));
		
		pdt.addPoint(new Point(NUM,NUM));
		
		
		testName = "B6.1";
		Point[] expectedPoints4=
		{
				new Point(NUM-mid-1,mid),
				new Point(NUM-(mid-1)-1,mid-1),
				new Point(NUM-(mid+1)-1,mid+1),
				new Point(NUM-(mid-2)-1,mid-2),
				new Point(NUM-(mid+2)-1,mid+2),
				new Point(NUM-(mid-3)-1,mid-3),
		};
		testExpectedPoints(testName, expectedPoints4, pdt.getMedianPoints(k));
		
		testName = "B6.2";
		Point[] expectedPoints5=
		{
				new Point(NUM-mid-1,mid),
				new Point(NUM-(mid-1)-1,mid-1),
				new Point(NUM-(mid+1)-1,mid+1),
				new Point(NUM-(mid-2)-1,mid-2),
				new Point(NUM-(mid+2)-1,mid+2),
				new Point(NUM-(mid-3)-1,mid-3),
				new Point(NUM-(mid+3)-1,mid+3),
		};
		testExpectedPoints(testName, expectedPoints5, pdt.getMedianPoints(k+1));
		
		XLeft=1;
		XRight=3;
		testName = "B6.3";
		expectedD = 97.0;
		resultD = pdt.averageHeightInRange(XLeft, XRight);
		testExpected(testName, expectedD, resultD);
		testName = "B6.4";
		XLeft=0;
		XRight=NUM;
		expectedD =((double) (NUM)*(NUM+1))/(2*(NUM+1));
		resultD = pdt.averageHeightInRange(XLeft, XRight);
		testExpected(testName, expectedD, resultD);

		pdt.removeMedianPoint();

		testName = "B7.1";
		Point[] expectedPoints6=
		{
				//new Point(NUM-mid-1,mid),
				new Point(NUM-(mid-1)-1,mid-1),
				new Point(NUM-(mid+1)-1,mid+1),
				new Point(NUM-(mid-2)-1,mid-2),
				new Point(NUM-(mid+2)-1,mid+2),
				new Point(NUM-(mid-3)-1,mid-3),
				new Point(NUM-(mid+3)-1,mid+3),
		};
		testExpectedPoints(testName, expectedPoints6, pdt.getMedianPoints(k));
		
		testName = "B7.2";
		Point[] expectedPoints7=
		{
				//new Point(NUM-mid-1,mid),
				new Point(NUM-(mid-1)-1,mid-1),
				new Point(NUM-(mid+1)-1,mid+1),
				new Point(NUM-(mid-2)-1,mid-2),
				new Point(NUM-(mid+2)-1,mid+2),
				new Point(NUM-(mid-3)-1,mid-3),
				new Point(NUM-(mid+3)-1,mid+3),
				//
				new Point(NUM-(mid+4)-1,mid+4),
		};
		testExpectedPoints(testName, expectedPoints7, pdt.getMedianPoints(k+1));
	}

	

	private static void testC()
    {
        Point[] points = {
                new Point(43, 694),
                new Point(157, 2672),
                new Point(216, 912),
                new Point(231, 491),
                new Point(251, 879),
                new Point(261, 1991),
                new Point(268, 2203),
                new Point(360, 1507),
                new Point(385, 2437),
                new Point(392, 1188),
                new Point(400, 357),
                new Point(416, 1167),
                new Point(473, 2307),
                new Point(614, 577),
                new Point(629, 2662),
                new Point(630, 2155),
                new Point(649, 2131),
                new Point(722, 1140),
                new Point(727, 2502),
                new Point(729, 730),
                new Point(789, 2652),
                new Point(826, 841),
                new Point(1013, 818),
                new Point(1026, 174),
                new Point(1029, 2576),
                new Point(1228, 2382),
                new Point(1398, 1588),
                new Point(1476, 1135),
                new Point(1493, 43),
                new Point(1513, 1017),
                new Point(1534, 1586),
                new Point(1569, 324),
                new Point(1578, 857),
                new Point(1780, 2080),
                new Point(1783, 3),
                new Point(1829, 2522),
                new Point(1839, 2256),
                new Point(1867, 41),
                new Point(1907, 751),
                new Point(2044, 2004),
                new Point(2071, 1120),
                new Point(2076, 992),
                new Point(2094, 572),
                new Point(2150, 1019),
                new Point(2174, 1157),
                new Point(2226, 1404),
                new Point(2238, 721),
                new Point(2286, 2570),
                new Point(2338, 583),
                new Point(2476, 960),
        };


        PDT pdt=new PointDataStructure(points,points[17]);

        String testName_median_array_23 = "median median_array_23";
        Point[] median_array_23 = {
                new Point(1013, 818),
                new Point(826, 841),
                new Point(1578, 857),
                new Point(251, 879),
                new Point(216, 912),
                new Point(2476, 960),
                new Point(2076, 992),
                new Point(1513, 1017),
                new Point(2150, 1019),
                new Point(2071, 1120),
                new Point(1476, 1135),
                new Point(722, 1140),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1534, 1586),
                new Point(1398, 1588),
                new Point(261, 1991),
                new Point(2044, 2004),
                new Point(1780, 2080),
                new Point(649, 2131),
        };


        testExpectedPoints(testName_median_array_23, median_array_23, pdt.getMedianPoints(23));


        String testName_median_array_7 = "median median_array_7";
        Point[] median_array_7 = {
                new Point(2150, 1019),
                new Point(2071, 1120),
                new Point(1476, 1135),
                new Point(722, 1140),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
        };


        testExpectedPoints(testName_median_array_7, median_array_7, pdt.getMedianPoints(7));


        String testName_median_array_5 = "median median_array_5";
        Point[] median_array_5 = {
                new Point(2071, 1120),
                new Point(1476, 1135),
                new Point(722, 1140),
                new Point(2174, 1157),
                new Point(416, 1167),
        };


        testExpectedPoints(testName_median_array_5, median_array_5, pdt.getMedianPoints(5));


        String testName_median_array_6 = "median median_array_6";
        Point[] median_array_6 = {
                new Point(2150, 1019),
                new Point(2071, 1120),
                new Point(1476, 1135),
                new Point(722, 1140),
                new Point(2174, 1157),
                new Point(416, 1167),
        };


        testExpectedPoints(testName_median_array_6, median_array_6, pdt.getMedianPoints(6));


        String testName_median_array_9 = "median median_array_9";
        Point[] median_array_9 = {
                new Point(1513, 1017),
                new Point(2150, 1019),
                new Point(2071, 1120),
                new Point(1476, 1135),
                new Point(722, 1140),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(2226, 1404),
        };


        testExpectedPoints(testName_median_array_9, median_array_9, pdt.getMedianPoints(9));


        String testName_median_array_17 = "median median_array_17";
        Point[] median_array_17 = {
                new Point(251, 879),
                new Point(216, 912),
                new Point(2476, 960),
                new Point(2076, 992),
                new Point(1513, 1017),
                new Point(2150, 1019),
                new Point(2071, 1120),
                new Point(1476, 1135),
                new Point(722, 1140),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1534, 1586),
                new Point(1398, 1588),
                new Point(261, 1991),
        };


        testExpectedPoints(testName_median_array_17, median_array_17, pdt.getMedianPoints(17));


        String testName_median_array_19 = "median median_array_19";
        Point[] median_array_19 = {
                new Point(1578, 857),
                new Point(251, 879),
                new Point(216, 912),
                new Point(2476, 960),
                new Point(2076, 992),
                new Point(1513, 1017),
                new Point(2150, 1019),
                new Point(2071, 1120),
                new Point(1476, 1135),
                new Point(722, 1140),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1534, 1586),
                new Point(1398, 1588),
                new Point(261, 1991),
                new Point(2044, 2004),
        };


        testExpectedPoints(testName_median_array_19, median_array_19, pdt.getMedianPoints(19));


        String testName_median_array_12 = "median median_array_12";
        Point[] median_array_12 = {
                new Point(2476, 960),
                new Point(2076, 992),
                new Point(1513, 1017),
                new Point(2150, 1019),
                new Point(2071, 1120),
                new Point(1476, 1135),
                new Point(722, 1140),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(2226, 1404),
                new Point(360, 1507),
        };


        testExpectedPoints(testName_median_array_12, median_array_12, pdt.getMedianPoints(12));


        String testName_median_array_8 = "median median_array_8";
        Point[] median_array_8 = {
                new Point(1513, 1017),
                new Point(2150, 1019),
                new Point(2071, 1120),
                new Point(1476, 1135),
                new Point(722, 1140),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
        };


        testExpectedPoints(testName_median_array_8, median_array_8, pdt.getMedianPoints(8));


        String testName_median_array_13 = "median median_array_13";
        Point[] median_array_13 = {
                new Point(2476, 960),
                new Point(2076, 992),
                new Point(1513, 1017),
                new Point(2150, 1019),
                new Point(2071, 1120),
                new Point(1476, 1135),
                new Point(722, 1140),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1534, 1586),
        };


        testExpectedPoints(testName_median_array_13, median_array_13, pdt.getMedianPoints(13));


        String testName_median_array_21 = "median median_array_21";
        Point[] median_array_21 = {
                new Point(826, 841),
                new Point(1578, 857),
                new Point(251, 879),
                new Point(216, 912),
                new Point(2476, 960),
                new Point(2076, 992),
                new Point(1513, 1017),
                new Point(2150, 1019),
                new Point(2071, 1120),
                new Point(1476, 1135),
                new Point(722, 1140),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1534, 1586),
                new Point(1398, 1588),
                new Point(261, 1991),
                new Point(2044, 2004),
                new Point(1780, 2080),
        };


        testExpectedPoints(testName_median_array_21, median_array_21, pdt.getMedianPoints(21));


        String testName_median_array_3 = "median median_array_3";
        Point[] median_array_3 = {
                new Point(1476, 1135),
                new Point(722, 1140),
                new Point(2174, 1157),
        };


        testExpectedPoints(testName_median_array_3, median_array_3, pdt.getMedianPoints(3));


        pdt.addPoint(new Point(1205,1811));
        pdt.addPoint(new Point(1143,3876));
        pdt.addPoint(new Point(993,1994));
        pdt.addPoint(new Point(905,2402));
        pdt.addPoint(new Point(1256,3096));
        pdt.addPoint(new Point(663,1892));
        pdt.addPoint(new Point(910,2904));
        pdt.addPoint(new Point(1311,551));
        pdt.addPoint(new Point(545,1015));
        pdt.addPoint(new Point(1248,341));
        pdt.addPoint(new Point(863,576));
        pdt.addPoint(new Point(554,3912));
        pdt.addPoint(new Point(1244,3912));
        pdt.addPoint(new Point(1824,1244));
        pdt.addPoint(new Point(337,2371));
        pdt.addPoint(new Point(599,3186));
        pdt.addPoint(new Point(135,1332));
        pdt.addPoint(new Point(8,4070));
        pdt.addPoint(new Point(140,378));
        pdt.addPoint(new Point(1,3246));
        pdt.addPoint(new Point(131,97));
        pdt.addPoint(new Point(895,418));
        pdt.addPoint(new Point(1048,2804));
        pdt.addPoint(new Point(1278,1838));
        pdt.addPoint(new Point(711,3425));
        pdt.addPoint(new Point(809,1151));
        pdt.addPoint(new Point(606,332));
        pdt.addPoint(new Point(1184,3783));
        pdt.addPoint(new Point(492,2711));
        pdt.addPoint(new Point(1250,484));
        pdt.addPoint(new Point(1153,3356));
        pdt.addPoint(new Point(3238,4034));
        pdt.addPoint(new Point(27,4054));
        pdt.addPoint(new Point(624,2985));
        pdt.addPoint(new Point(353,3254));
        pdt.addPoint(new Point(289,382));
        pdt.addPoint(new Point(540,982));
        pdt.addPoint(new Point(513,3132));
        pdt.addPoint(new Point(763,3388));
        pdt.addPoint(new Point(212,1790));
        pdt.addPoint(new Point(1000,2392));
        pdt.addPoint(new Point(893,3490));
        pdt.removeMedianPoint();
        pdt.removeMedianPoint();
        pdt.removeMedianPoint();
        String testName_median_array_8_mew = "median median_array_8_mew";
        Point[] median_array_8_mew = {
                new Point(392, 1188),
                new Point(1824, 1244),
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
                new Point(1278, 1838),
                new Point(663, 1892),
        };


        testExpectedPoints(testName_median_array_8_mew, median_array_8_mew, pdt.getMedianPoints(8));


        String testName_median_array_13_mew = "median median_array_13_mew";
        Point[] median_array_13_mew = {
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(1824, 1244),
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
                new Point(1278, 1838),
                new Point(663, 1892),
                new Point(261, 1991),
                new Point(993, 1994),
                new Point(2044, 2004),
        };


        testExpectedPoints(testName_median_array_13_mew, median_array_13_mew, pdt.getMedianPoints(13));


        String testName_median_array_21_mew = "median median_array_21_mew";
        Point[] median_array_21_mew = {
                new Point(2071, 1120),
                new Point(1476, 1135),
                new Point(722, 1140),
                new Point(809, 1151),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(1824, 1244),
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
                new Point(1278, 1838),
                new Point(663, 1892),
                new Point(261, 1991),
                new Point(993, 1994),
                new Point(2044, 2004),
                new Point(1780, 2080),
                new Point(649, 2131),
                new Point(630, 2155),
                new Point(268, 2203),
        };


        testExpectedPoints(testName_median_array_21_mew, median_array_21_mew, pdt.getMedianPoints(21));


        String testName_median_array_5_mew = "median median_array_5_mew";
        Point[] median_array_5_mew = {
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
                new Point(1278, 1838),
        };


        testExpectedPoints(testName_median_array_5_mew, median_array_5_mew, pdt.getMedianPoints(5));


        String testName_median_array_7_mew = "median median_array_7_mew";
        Point[] median_array_7_mew = {
                new Point(1824, 1244),
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
                new Point(1278, 1838),
                new Point(663, 1892),
        };


        testExpectedPoints(testName_median_array_7_mew, median_array_7_mew, pdt.getMedianPoints(7));


        String testName_median_array_6_mew = "median median_array_6_mew";
        Point[] median_array_6_mew = {
                new Point(1824, 1244),
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
                new Point(1278, 1838),
        };


        testExpectedPoints(testName_median_array_6_mew, median_array_6_mew, pdt.getMedianPoints(6));


        String testName_median_array_19_mew = "median median_array_19_mew";
        Point[] median_array_19_mew = {
                new Point(1476, 1135),
                new Point(722, 1140),
                new Point(809, 1151),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(1824, 1244),
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
                new Point(1278, 1838),
                new Point(663, 1892),
                new Point(261, 1991),
                new Point(993, 1994),
                new Point(2044, 2004),
                new Point(1780, 2080),
                new Point(649, 2131),
                new Point(630, 2155),
        };


        testExpectedPoints(testName_median_array_19_mew, median_array_19_mew, pdt.getMedianPoints(19));


        String testName_median_array_14_mew = "median median_array_14_mew";
        Point[] median_array_14_mew = {
                new Point(809, 1151),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(1824, 1244),
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
                new Point(1278, 1838),
                new Point(663, 1892),
                new Point(261, 1991),
                new Point(993, 1994),
                new Point(2044, 2004),
        };


        testExpectedPoints(testName_median_array_14_mew, median_array_14_mew, pdt.getMedianPoints(14));


        String testName_median_array_15_mew = "median median_array_15_mew";
        Point[] median_array_15_mew = {
                new Point(809, 1151),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(1824, 1244),
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
                new Point(1278, 1838),
                new Point(663, 1892),
                new Point(261, 1991),
                new Point(993, 1994),
                new Point(2044, 2004),
                new Point(1780, 2080),
        };


        testExpectedPoints(testName_median_array_15_mew, median_array_15_mew, pdt.getMedianPoints(15));


        String testName_median_array_3_mew = "median median_array_3_mew";
        Point[] median_array_3_mew = {
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
        };


        testExpectedPoints(testName_median_array_3_mew, median_array_3_mew, pdt.getMedianPoints(3));


        String testName_median_array_24_mew = "median median_array_24_mew";
        Point[] median_array_24_mew = {
                new Point(1513, 1017),
                new Point(2150, 1019),
                new Point(2071, 1120),
                new Point(1476, 1135),
                new Point(722, 1140),
                new Point(809, 1151),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(1824, 1244),
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
                new Point(1278, 1838),
                new Point(663, 1892),
                new Point(261, 1991),
                new Point(993, 1994),
                new Point(2044, 2004),
                new Point(1780, 2080),
                new Point(649, 2131),
                new Point(630, 2155),
                new Point(268, 2203),
                new Point(1839, 2256),
        };


        testExpectedPoints(testName_median_array_24_mew, median_array_24_mew, pdt.getMedianPoints(24));


        String testName_median_array_10_mew = "median median_array_10_mew";
        Point[] median_array_10_mew = {
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(1824, 1244),
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
                new Point(1278, 1838),
                new Point(663, 1892),
                new Point(261, 1991),
        };


        testExpectedPoints(testName_median_array_10_mew, median_array_10_mew, pdt.getMedianPoints(10));


        String testName_median_array_4_mew = "median median_array_4_mew";
        Point[] median_array_4_mew = {
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
        };


        testExpectedPoints(testName_median_array_4_mew, median_array_4_mew, pdt.getMedianPoints(4));


        String testName_median_array_16_mew = "median median_array_16_mew";
        Point[] median_array_16_mew = {
                new Point(722, 1140),
                new Point(809, 1151),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(1824, 1244),
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
                new Point(1278, 1838),
                new Point(663, 1892),
                new Point(261, 1991),
                new Point(993, 1994),
                new Point(2044, 2004),
                new Point(1780, 2080),
        };


        testExpectedPoints(testName_median_array_16_mew, median_array_16_mew, pdt.getMedianPoints(16));


        String testName_median_array_12_mew = "median median_array_12_mew";
        Point[] median_array_12_mew = {
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(1824, 1244),
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
                new Point(1278, 1838),
                new Point(663, 1892),
                new Point(261, 1991),
                new Point(993, 1994),
        };


        testExpectedPoints(testName_median_array_12_mew, median_array_12_mew, pdt.getMedianPoints(12));


        String testName_median_array_9_mew = "median median_array_9_mew";
        Point[] median_array_9_mew = {
                new Point(392, 1188),
                new Point(1824, 1244),
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
                new Point(1278, 1838),
                new Point(663, 1892),
                new Point(261, 1991),
        };


        testExpectedPoints(testName_median_array_9_mew, median_array_9_mew, pdt.getMedianPoints(9));


        String testName_median_array_18_mew = "median median_array_18_mew";
        Point[] median_array_18_mew = {
                new Point(1476, 1135),
                new Point(722, 1140),
                new Point(809, 1151),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(1824, 1244),
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
                new Point(1278, 1838),
                new Point(663, 1892),
                new Point(261, 1991),
                new Point(993, 1994),
                new Point(2044, 2004),
                new Point(1780, 2080),
                new Point(649, 2131),
        };


        testExpectedPoints(testName_median_array_18_mew, median_array_18_mew, pdt.getMedianPoints(18));


        String testName_median_array_17_mew = "median median_array_17_mew";
        Point[] median_array_17_mew = {
                new Point(722, 1140),
                new Point(809, 1151),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(1824, 1244),
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
                new Point(1278, 1838),
                new Point(663, 1892),
                new Point(261, 1991),
                new Point(993, 1994),
                new Point(2044, 2004),
                new Point(1780, 2080),
                new Point(649, 2131),
        };


        testExpectedPoints(testName_median_array_17_mew, median_array_17_mew, pdt.getMedianPoints(17));


        String testName_median_array_20_mew = "median median_array_20_mew";
        Point[] median_array_20_mew = {
                new Point(2071, 1120),
                new Point(1476, 1135),
                new Point(722, 1140),
                new Point(809, 1151),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(1824, 1244),
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
                new Point(1278, 1838),
                new Point(663, 1892),
                new Point(261, 1991),
                new Point(993, 1994),
                new Point(2044, 2004),
                new Point(1780, 2080),
                new Point(649, 2131),
                new Point(630, 2155),
        };


        testExpectedPoints(testName_median_array_20_mew, median_array_20_mew, pdt.getMedianPoints(20));


        String testName_median_array_23_mew = "median median_array_23_mew";
        Point[] median_array_23_mew = {
                new Point(2150, 1019),
                new Point(2071, 1120),
                new Point(1476, 1135),
                new Point(722, 1140),
                new Point(809, 1151),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(1824, 1244),
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
                new Point(1278, 1838),
                new Point(663, 1892),
                new Point(261, 1991),
                new Point(993, 1994),
                new Point(2044, 2004),
                new Point(1780, 2080),
                new Point(649, 2131),
                new Point(630, 2155),
                new Point(268, 2203),
                new Point(1839, 2256),
        };


        testExpectedPoints(testName_median_array_23_mew, median_array_23_mew, pdt.getMedianPoints(23));


        String testName_median_array_22_mew = "median median_array_22_mew";
        Point[] median_array_22_mew = {
                new Point(2150, 1019),
                new Point(2071, 1120),
                new Point(1476, 1135),
                new Point(722, 1140),
                new Point(809, 1151),
                new Point(2174, 1157),
                new Point(416, 1167),
                new Point(392, 1188),
                new Point(1824, 1244),
                new Point(135, 1332),
                new Point(2226, 1404),
                new Point(360, 1507),
                new Point(1205, 1811),
                new Point(1278, 1838),
                new Point(663, 1892),
                new Point(261, 1991),
                new Point(993, 1994),
                new Point(2044, 2004),
                new Point(1780, 2080),
                new Point(649, 2131),
                new Point(630, 2155),
                new Point(268, 2203),
        };


        testExpectedPoints(testName_median_array_22_mew, median_array_22_mew, pdt.getMedianPoints(22));


        String testName_median_array_1_mew = "median median_array_1_mew";
        Point[] median_array_1_mew = {
                new Point(360, 1507),
        };


        testExpectedPoints(testName_median_array_1_mew, median_array_1_mew, pdt.getMedianPoints(1));



    }
	
	private static class PointCompare implements Comparator<Point>
	 {

		@Override
		public int compare(Point point1, Point point2) 
		{
			if (point1.getY()<point2.getY()) return -1;
			else if (point1.getY()==point2.getY() && point1.getX()<point2.getX()) return -1;
			return 1;
		}
		 
	 }
		
	
	private static <T> void testExpected(String testName, T expected, T actual) 
	{
		if (!actual.equals(expected)) {
			System.out.println("Test " +testName+ ": wrong! expected=" + expected + ", actual=" + actual);
		} else {
			System.out.println("Test " +testName+ ": passed :)");
		}
		
	}
	
	private static void testExpectedPoints(String testName, Point[] expectedPoints,	Point[] actualPoints) 
	{
		if (!equalAsSets(actualPoints,expectedPoints)) {
			System.out.println("Test " +testName+ ": wrong! expected=" + Arrays.toString(expectedPoints) + ", actual=" + Arrays.toString(actualPoints));
		} else {
			System.out.println("Test " +testName+ ": passed :)");
		}
	}
	
	//checks (inefficiently) that the sets are equal
	//NOTE: if one of the sets contains 2 points with same coordinates, might return erroneous result 
	private static boolean equalAsSets(Point[] actualPoints, Point[] expectedPoints) 
	{
		if (actualPoints.length!=expectedPoints.length) return false;
		for (int i=0;i<expectedPoints.length;i++)
		{
			boolean exists=false;
			for (int j=0;j<actualPoints.length;j++)
			{ 
				if (expectedPoints[i].equals(actualPoints[j])) exists=true;
			}
			if (!exists) return false;
		}
		return true;
	}


	private static Point[] readPointsFile(String fileName) 
	{
		Vector<Point> points = new Vector<Point>();;
		BufferedReader input;
		FileReader fileReader;
		
		try {
			fileReader = new FileReader(fileName);
			input = new BufferedReader(fileReader);
			String line = null;
			String name;
			int x, y;

			while ((line = input.readLine()) != null) {
				String[] lineArray = line.split(";");
				name = lineArray[0];
				x = Integer.parseInt(lineArray[1]);
				y = Integer.parseInt(lineArray[2]);

				Point p = new Point(x, y, name);
				points.add(p);
			}
			input.close();
			fileReader.close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Point[] result = new Point[points.size()];
		points.toArray(result);
		return result;
	}

	
	
	
}
