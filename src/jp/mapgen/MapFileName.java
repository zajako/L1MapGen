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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.l1j.mapgen.util.FileUtils;

public class MapFileName {
	private final int _x;
	private final int _y;
	private final String _name;

	public MapFileName(File fileName) {
		this(fileName.getName());
	}

	public MapFileName(String name) {
		Pattern p = Pattern.compile("([0-9a-f]{4})([0-9a-f]{4})\\.(seg|s32)",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(name);
		if (!m.matches())
			throw new IllegalArgumentException();
		_name = name;
		_x = Integer.parseInt(m.group(1), 16);
		_y = Integer.parseInt(m.group(2), 16);
	}

	public String getNameWithoutExtension() {
		return FileUtils.getNameWithoutExtension(_name);
	}

	public int getX() {
		return _x;
	}

	public int getY() {
		return _y;
	}
}
