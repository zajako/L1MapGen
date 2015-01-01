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

import jp.l1j.mapgen.util.StreamUtils;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

import org.easymock.IMocksControl;
import org.junit.Test;

public class StreamUtilsTest {

	@Test
	public void testClose() throws IOException {
		IMocksControl mocks = createControl();
		Closeable c1 = mocks.createMock(Closeable.class);
		Closeable c2 = mocks.createMock(Closeable.class);
		Closeable c3 = mocks.createMock(Closeable.class);
		c1.close();
		c2.close();
		expectLastCall().andThrow(new IOException());
		c3.close();
		mocks.replay();

		StreamUtils.close(c1, c2, c3);

		mocks.verify();
	}

	@Test
	public void testForceSkip() throws IOException {
		IMocksControl mocks = createControl();
		InputStream in = mocks.createMock(InputStream.class);
		expect(in.skip(100)).andReturn(50L);
		expect(in.skip(50)).andReturn(50L);
		mocks.replay();

		StreamUtils.forceSkip(in, 100);

		mocks.verify();
	}

	@Test
	public void testFailForceSkip() throws IOException {
		IMocksControl mocks = createControl();
		InputStream in = mocks.createMock(InputStream.class);
		expect(in.skip(100)).andReturn(50L);
		expect(in.skip(50)).andReturn(0L);
		mocks.replay();

		try {
			StreamUtils.forceSkip(in, 100);
		} catch (IOException e) {
			mocks.verify();
			return;
		}
		fail("IOException was not thrown.");
	}
}
