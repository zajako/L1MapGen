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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jp.l1j.mapgen.util.BinaryInputStream;
import jp.l1j.mapgen.util.FileUtils;
import jp.l1j.mapgen.util.StreamUtils;

class L1MapPart {
	private final File _file;
	private final int _xOff;
	private final int _yOff;
	private final int _tiles[] = new int[64 * 64 * 2];

	public L1MapPart(File file, int xOff, int yOff) {
		_file = file;
		_xOff = xOff;
		_yOff = yOff;
	}

	private void loadS32(BinaryInputStream bin) throws IOException {
		StreamUtils.forceSkip(bin, 0x8000);

		int numOfOptData = bin.readShort();
		StreamUtils.forceSkip(bin, numOfOptData * 6);

		for (int i = 0; i < 0x2000; i++) {
			_tiles[i] = bin.readShort();
		}
	}

	private void loadSeg(BinaryInputStream bin) throws IOException {
		StreamUtils.forceSkip(bin, 0x4000);

		int numOfOptData = bin.readShort();
		StreamUtils.forceSkip(bin, numOfOptData * 4);

		for (int i = 0; i < 0x2000; i++) {
			_tiles[i] = bin.readByte();
		}
	}

	public void load() {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BinaryInputStream bin = null;
		try {
			fis = new FileInputStream(_file);
			bis = new BufferedInputStream(fis);
			bin = new BinaryInputStream(bis);

			String ext = FileUtils.getExtension(_file).toLowerCase();
			if (ext.equals("seg")) {
				loadSeg(bin);
			} else if (ext.equals("s32")) {
				loadS32(bin);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			StreamUtils.close(bin, bis, fis);
		}
	}

	public int[] getRow(int r) {
		int row[] = new int[64 * 2];

		for (int i = 0; i < 64 * 2; i++) {
			row[i] = _tiles[64 * 2 * r + i];
		}

		return row;
	}

	public int getXOff() {
		return _xOff;
	}

	public int getYOff() {
		return _yOff;
	}
}