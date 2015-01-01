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

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	private static final String CONFIG_FILE = "./config/config.properties.xml";
	private static Properties _prop = new Properties();

	public static String MAP_DIR = null;
	public static String OUTPUT_DIR = null;
	public static boolean OUTPUT_V1MAPS = false;
	public static boolean OUTPUT_V2MAPS = false;
	public static boolean OUTPUT_MAPINFO = false;
	public static boolean OUTPUT_MAPIDS = false;
	public static String ZONE_FILE = null;
	public static boolean OUTPUT_TILEVALUES = false;

	private static void loadFromStream(InputStream is) throws IOException {
		_prop.loadFromXML(is);

		MAP_DIR = _prop.getProperty("LineageMapDirectory");
		OUTPUT_DIR = _prop.getProperty("OutputDirectory");
		OUTPUT_V1MAPS = _prop.getProperty("OutputV1Maps", "yes")
				.equalsIgnoreCase("yes");
		OUTPUT_V2MAPS = _prop.getProperty("OutputV2Maps", "no")
				.equalsIgnoreCase("yes");
		OUTPUT_MAPINFO = _prop.getProperty("OutputV1MapInfo", "yes")
				.equalsIgnoreCase("yes");
		OUTPUT_MAPIDS = _prop.getProperty("OutputMapIds", "yes")
				.equalsIgnoreCase("yes");
		ZONE_FILE = _prop.getProperty("ZoneFilePath");
		OUTPUT_TILEVALUES = _prop.getProperty("OutputTileValue", "yes")
				.equalsIgnoreCase("yes");
	}

	public static void load(String props) throws IOException {
		InputStream is = new ByteArrayInputStream(props.getBytes());
		loadFromStream(is);
		is.close();
	}

	public static void load() throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(new File(
				CONFIG_FILE)));
		loadFromStream(is);
		is.close();
	}
}
