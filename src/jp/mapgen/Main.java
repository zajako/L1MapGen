/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package jp.l1j.mapgen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import jp.l1j.mapgen.writer.MapWriter;
import jp.l1j.mapgen.writer.MapWriterFactory;
import jp.l1j.mapgen.writer.TileValueWriter;

public class Main {
	private static final MapInfoBuilder _src = new MapInfoBuilder();
	private static final MapInfoBuilder _csv = new MapIdsBuilder();
	private static final TileValueList _tileList = new TileValueList();

	private static void processMap(File mapDir) {
		TreeSet<String> fileList = new TreeSet<String>();
		TreeSet<Integer> xList = new TreeSet<Integer>();
		TreeSet<Integer> yList = new TreeSet<Integer>();
		int x_max = 0;
		int x_min = 999999;
		int y_max = 0;
		int y_min = 999999;


		for (File f : mapDir.listFiles(new MapFileFilter())) {
			MapFileName fileName = new MapFileName(f);
			xList.add(fileName.getX());
			yList.add(fileName.getY());
			fileList.add(fileName.getNameWithoutExtension());
			if(fileName.getX() > x_max)
				x_max = fileName.getX();
			if(fileName.getY() > y_max)
				y_max = fileName.getY();
			if(fileName.getX() < x_min)
				x_min = fileName.getX();
			if(fileName.getY() < y_min)
				y_min = fileName.getY();
		}

		int xSize = x_max - x_min + 1;
		int ySize = y_max - y_min + 1;

		ArrayList<L1MapPart> maps = new ArrayList<L1MapPart>();
		for (String fileName : fileList) {
			int x = Integer.parseInt(fileName.substring(0, 4), 16);
			int y = Integer.parseInt(fileName.substring(4, 8), 16);

			x -= xList.first();
			y -= yList.first();

			File mapFile = new File(mapDir, fileName + ".s32");
			if (!mapFile.exists()) {
				mapFile = new File(mapDir, fileName + ".seg");
				if (!mapFile.exists()) {
					throw new RuntimeException();
				}
			}

			maps.add(new L1MapPart(mapFile, x, y));
		}

		int no = Integer.parseInt(mapDir.getName());
		int xEnd = (xList.last() - 32767) * 64 + 32767;
		int yEnd = (yList.last() - 32767) * 64 + 32767;
		int xStart = xEnd - xSize * 64 + 1;
		int yStart = yEnd - ySize * 64 + 1;
		_src.add(no, xStart, xEnd, yStart, yEnd);
		_csv.add(no, xStart, xEnd, yStart, yEnd);
		L1Map map = new L1Map(maps, no, xStart, yStart, xSize, ySize);
		map.build();

		List<MapWriter> writers = MapWriterFactory.newWriters();
		for (MapWriter w : writers) {
			w.writeMap(map, new File(Config.OUTPUT_DIR));
		}

		if (Config.OUTPUT_TILEVALUES) {
			_tileList.addAll(map.getTileValueSet());
		}

		System.out.print(mapDir.getName() + ", xBlock:" + xSize + ", yBlock:"
				+ ySize);
		
		switch (no) {
		case 1005: // Antharas rare
		case 1011: // Papurionrea
		case 1017:  // Rindobiorurea
		case 1023: // Vu~arakasurea
			for (int i = 1; i <= 5; i++) {
				_src.add(no + i, xStart, xEnd, yStart, yEnd);
				_csv.add(no + i, xStart, xEnd, yStart, yEnd);
				
				try {
					copyFile(new File(Config.OUTPUT_DIR, "maps/" + no + ".txt"),
							new File(Config.OUTPUT_DIR, "maps/" + (no + i) + ".txt"));
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	public static void copyFile(File src, File dest) 
		throws IOException {

		FileChannel srcChannel = new FileInputStream(src).getChannel();
		FileChannel destChannel = new FileOutputStream(dest).getChannel();
		try {
			srcChannel.transferTo(0, srcChannel.size(), destChannel);
		} finally {
			srcChannel.close();
			destChannel.close();
		}

	}

	/**
	 * L1J マップファイルジェネレータ
	 * 
	 * Lineageオリジナルクライアントのマップファイルから、L1J用マップファイルを生成する。
	 * V1,V2対応。設定項目はconfig.propertiesを参照。
	 * 
	 * @param args
	 *            無視されます。
	 */
	public static void main(String[] args) {
		try {
			Config.load();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		File[] mapDirs = new File(Config.MAP_DIR)
				.listFiles(new MapDirectoryFilter());
		if (mapDirs == null) {
			System.out.println(Config.MAP_DIR + " は存在しないかmapディレクトリはありません。");
			return;
		}
		Arrays.sort(mapDirs, new MapDirComparator());
		int i = 1;
		for (File dir : mapDirs) {
			processMap(dir);
			System.out.println(", " + i + "/" + mapDirs.length);
			i++;
		}
		if (Config.OUTPUT_MAPINFO) {
			_src.save(new File(Config.OUTPUT_DIR, "map_info.java.txt"));
		}
		if (Config.OUTPUT_MAPIDS) {
			_csv.save(new File(Config.OUTPUT_DIR, "map_ids.csv"));
		}
		if (Config.OUTPUT_TILEVALUES) {
			TileValueWriter.writeTileValues(new File(Config.OUTPUT_DIR,
					"tiles.txt"), _tileList);
		}
	}
}
