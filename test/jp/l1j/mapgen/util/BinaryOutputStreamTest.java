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

import jp.l1j.mapgen.util.BinaryOutputStream;
import static org.easymock.EasyMock.*;

import java.io.IOException;
import java.io.OutputStream;

import org.easymock.IMocksControl;
import org.junit.Test;

public class BinaryOutputStreamTest {
	@Test
	public void testBinaryOutputStream() throws IOException {
		byte buf[] = new byte[] { 100, 0, 0, 0 };
		IMocksControl mocks = createControl();
		OutputStream out = mocks.createMock(OutputStream.class);
		out.write(50);
		out.write(buf);
		out.write(buf, 2, 2);
		out.write(100);
		out.write(100);
		out.write(0);
		out.write(100);
		out.write(0);
		out.write(0);
		out.write(0);
		out.flush();
		out.close();
		mocks.replay();

		BinaryOutputStream bos = new BinaryOutputStream(out);
		bos.write(50);
		bos.write(buf);
		bos.write(buf, 2, 2);
		bos.writeByte(100);
		bos.writeShort(100);
		bos.writeInt(100);
		bos.flush();
		bos.close();

		mocks.verify();
	}
}
