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

package jp.l1j.mapgen.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtils {
	public static void close(Closeable... closeables) {
		for (Closeable c : closeables) {
			try {
				if (c != null) {
					c.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public static void forceSkip(InputStream is, long n) throws IOException {
		while (0 < n) {
			long i = is.skip(n);
			if (i == 0) {
				throw new IOException("faild force skip");
			}
			n -= i;
		}
	}
}
