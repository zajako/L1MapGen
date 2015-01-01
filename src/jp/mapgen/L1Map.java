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

import java.util.ArrayList;
import java.util.TreeSet;

public class L1Map {
	private final ArrayList<L1MapPart> _maps;
	private final int _xLoc;
	private final int _yLoc;
	private final int _xSize;
	private final int _ySize;
	private final int _num;

	private int _tiles[] = null;

	private int getOffset(int xOff, int yOff, int x, int y) {
		return (_xSize * 64 * 2) * (yOff * 64 + y) + (xOff * 64 * 2) + x;
	}

	public L1Map(ArrayList<L1MapPart> maps, int num, int xLoc, int yLoc,
			int xSize, int ySize) {
		_maps = maps;
		_num = num;
		_xLoc = xLoc;
		_yLoc = yLoc;
		_xSize = xSize;
		_ySize = ySize;

	}

	public void build() {
		_tiles = new int[_xSize * _ySize * 64 * 64 * 2];

		for (L1MapPart part : _maps) {
			part.load();
			int xOff = part.getXOff();
			int yOff = part.getYOff();

			for (int y = 0; y < 64; y++) {
				int row[] = part.getRow(y);

				for (int x = 0; x < 64 * 2; x++) {
					_tiles[getOffset(xOff, yOff, x, y)] = row[x];
				}
			}
		}
	}

	public TreeSet<Integer> getTileValueSet() {
		TreeSet<Integer> result = new TreeSet<Integer>();
		for (int v : _tiles) {
			result.add(v);
		}
		return result;
	}

	public int getTile(int idx) {
		return _tiles[idx];
	}

	public int[] getRawTile() {
		return _tiles;
	}

	public int getXLoc() {
		return _xLoc;
	}

	public int getYLoc() {
		return _yLoc;
	}

	public int getXSize() {
		return _xSize;
	}

	public int getYSize() {
		return _ySize;
	}

	public int getNum() {
		return _num;
	}
}