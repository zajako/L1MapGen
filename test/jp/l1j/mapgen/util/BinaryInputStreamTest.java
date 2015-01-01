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

import jp.l1j.mapgen.util.BinaryInputStream;
import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.easymock.IMocksControl;
import org.junit.Test;

public class BinaryInputStreamTest {

	@Test
	public void testBinaryInputStream() throws IOException {
		IMocksControl mocks = createControl();
		InputStream in = mocks.createMock(InputStream.class);
		expect(in.available()).andReturn(100);
		expect(in.read()).andReturn(50); // read
		expect(in.read()).andReturn(100); // byte
		expect(in.read()).andReturn(100); // short
		expect(in.read()).andReturn(0);
		expect(in.skip(100)).andReturn((long) 100);
		expect(in.read()).andReturn(100); // int
		expect(in.read()).andReturn(0);
		expect(in.read()).andReturn(0);
		expect(in.read()).andReturn(0);
		in.close();
		mocks.replay();

		BinaryInputStream bis = new BinaryInputStream(in);
		assertThat(bis.available(), is(100));
		assertThat(bis.read(), is(50));
		assertThat(bis.readByte(), is(100));
		assertThat(bis.readShort(), is(100));
		assertThat(bis.skip(100), is((long) 100));
		assertThat(bis.readInt(), is(100));
		bis.close();

		mocks.verify();
	}
}
