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

package jp.l1j.mapgen.writer;

import java.util.ArrayList;
import java.util.List;

import jp.l1j.mapgen.Config;

public class MapWriterFactory {
	public static List<MapWriter> newWriters() {
		List<MapWriter> result = new ArrayList<MapWriter>();
		if (Config.OUTPUT_V1MAPS) {
			result.add(new V1MapWriter());
		}
		if (Config.OUTPUT_V2MAPS) {
			result.add(new V2MapWriter());
		}
		return result;
	}
}
