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

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;

import jp.l1j.mapgen.L1Map;
import jp.l1j.mapgen.util.BinaryOutputStream;
import jp.l1j.mapgen.util.StreamUtils;

public class V2MapWriter implements MapWriter {
	private byte[] buildHeader(L1Map map) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		BinaryOutputStream out = new BinaryOutputStream(bos);

		try {
			out.writeInt(map.getNum());
			out.writeInt(map.getXLoc());
			out.writeInt(map.getYLoc());
			out.writeInt(map.getXSize() * 64);
			out.writeInt(map.getYSize() * 64);
		} catch (IOException e) {
			new Error();
		} finally {
			StreamUtils.close(out, bos);
		}

		return bos.toByteArray();
	}

	@Override
	public void writeMap(L1Map map, File dir) {
		dir = new File(dir, "v2maps");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		DeflaterOutputStream dos = null;
		try {
			fos = new FileOutputStream(new File(dir, map.getNum() + ".md"));
			dos = new DeflaterOutputStream(fos);
			bos = new BufferedOutputStream(dos);

			writeMap(map, bos);

			bos.flush();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			StreamUtils.close(bos, dos, fos);
		}
	}

	@Override
	public void writeMap(L1Map map, OutputStream out) throws IOException {
		out.write(buildHeader(map));
		int tiles[] = map.getRawTile();
		for (int i = 0; i < tiles.length; i++) {
			out.write(tiles[i]);
		}
	}

}
