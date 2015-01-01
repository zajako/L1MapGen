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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import jp.l1j.mapgen.TileValueList;
import jp.l1j.mapgen.util.StreamUtils;

public class TileValueWriter {
	public static void writeTileValues(File f, TileValueList valueList) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);

			bw.write(valueList.toString());
			bw.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			StreamUtils.close(bw, fw);
		}
	}
}
