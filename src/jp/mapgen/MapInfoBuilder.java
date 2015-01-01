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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import jp.l1j.mapgen.util.StreamUtils;
import jp.l1j.mapgen.util.StringUtils;

public class MapInfoBuilder {
	ArrayList<String> _infos = new ArrayList<String>();

	public void add(int no, int xStart, int xEnd, int yStart, int yEnd) {
		String s = "{ "
				+ StringUtils.join(
						new int[] { no, xStart, xEnd, yStart, yEnd }, ", ")
				+ " }";
		_infos.add(s);
	}

	public void save(File f) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);

			bw.write(this.toString());

			bw.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			StreamUtils.close(bw, fw);
		}
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("private static final int[][] MAP_INFO = {\r\n\t\t");
		result.append(StringUtils.join(_infos.toArray(), ",\r\n\t\t"));
		result.append(" };\r\n");
		return result.toString();
	}
}