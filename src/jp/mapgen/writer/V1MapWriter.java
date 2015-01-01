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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import jp.l1j.mapgen.L1Map;
import jp.l1j.mapgen.util.StreamUtils;

public class V1MapWriter implements MapWriter {
	// L1MapToolより
	private int toV1Value(int tile1, int tile2) {
		int result = 0;
		if ((tile1 & 0x0c) == 0) { // 0b1100 == 0x0c
			result += 0; // NormalZone
		} else if ((tile1 & 0x04) == 0x04) { // 0b0100 == 0x04
			result += 16; // SafetyZone
		} else {
			result += 32; // CombatZone
		}
		if ((tile1 >>> 4) == 0) { //Upper 4 bits are 0
			if ((tile1 & 0x01) == 0) {
				result += 10; //Movable and arrow can pass through (uncertain)
			}
		} else {
			if (((tile1 >>> 4) & 0x01) == 0) {
				result += 8; // Arrow can pass
			}
			if ((tile1 & 0x01) == 0) {
				result += 2; // Movable
			}
		}

		if ((tile2 >>> 4) == 0) {
			if ((tile2 & 0x01) == 0) {
				result += 5; //Movable and arrow can pass through (uncertain)
			}
		} else {
			if (((tile2 >>> 4) & 0x01) == 0) {
				result += 4; // Arrow can Pass
			}
			if ((tile2 & 0x01) == 0) {
				result += 1; // Movable
			}
		}

		return result;
	}

	@Override
	public void writeMap(L1Map map, File dir) {
		dir = new File(dir, "maps");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			fos = new FileOutputStream(new File(dir, map.getNum() + ".txt"));
			bos = new BufferedOutputStream(fos);

			writeMap(map, bos);

			bos.flush();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			StreamUtils.close(bos, fos);
		}
	}

	@Override
	public void writeMap(L1Map map, OutputStream out) throws IOException {
		int[][] m = optimization(map);
		
		for(int y = 0; y < m.length; y++){
			for (int x = 0; x < m[y].length; x++) {
				String s = Integer.toString(m[y][x]);
				out.write(s.getBytes());
				if (x < m[y].length - 1) {
					out.write(",".getBytes());
				}
			}
			out.write("\r\n".getBytes());
		}
	}
	
	private int[][] optimization(L1Map map) {
		int xSize = map.getXSize();
		int ySize = map.getYSize();
		int[][] m = new int[ySize * 64][xSize * 64];
		boolean isMargin;

		for (int y = 0; y < ySize * 64; y++) {
			for (int x = 0; x < xSize * 64; x++) {
				int off = (y * (64 * 2 * xSize)) + x * 2;
				m[y][x] = toV1Value(map.getTile(off), map.getTile(off + 1));
			}
		}
		
		for(int y = 0; y * 64 < m.length; y++){
			for(int x = 0; x * 64 < m[y].length; x++){
				// 64 * 64 check cell or a vacant lot
				isMargin = true;
				for(int i = y * 64; i < (y + 1) * 64; i++){
					for(int j = x * 64; j < (x + 1) * 64; j++){
						if(m[i][j] != 15){
							isMargin = false;
							break;
						}
					}
					if(isMargin == false){
						break;
					}
				}
				// In the case of margin, fill-in-the-blank
				if(isMargin){
					for(int i = y * 64; i < (y + 1) * 64; i++){
						for(int j = x * 64; j < (x + 1) * 64; j++){
							m[i][j] = 0;
						}
					}
				}
			}
		}
		
		return m;
	}
	
}
