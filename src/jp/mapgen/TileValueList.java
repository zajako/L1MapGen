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

import java.util.Set;
import java.util.TreeSet;

import jp.l1j.mapgen.util.BinaryUtils;

public class TileValueList {
	private final TreeSet<Integer> _tiles = new TreeSet<Integer>();

	public void add(int value) {
		_tiles.add(value);
	}

	public void addAll(Set<Integer> s) {
		_tiles.addAll(s);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int v : _tiles) {
			result.append(v);
			result.append(" ");
			result.append(BinaryUtils.byteToBinaryString((byte) v));
			result.append("\r\n");
		}

		return result.toString();
	}
}
