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

import jp.l1j.mapgen.MapDirectoryFilter;
import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileFilter;

import org.easymock.IMocksControl;
import org.junit.Test;

public class MapDirectoryFilterTest {

	@Test
	public void testAccept() {
		IMocksControl mocks = createControl();
		File fileFailInvalidName = mocks.createMock(File.class);
		File fileFailNotDir = mocks.createMock(File.class);
		File fileSuccess = mocks.createMock(File.class);
		expect(fileFailInvalidName.getName()).andReturn("1.ini");
		expect(fileFailNotDir.getName()).andReturn("1");
		expect(fileFailNotDir.isDirectory()).andReturn(false);
		expect(fileSuccess.getName()).andReturn("1");
		expect(fileSuccess.isDirectory()).andReturn(true);

		mocks.replay();

		FileFilter filter = new MapDirectoryFilter();
		assertThat(filter.accept(fileFailInvalidName), is(false));
		assertThat(filter.accept(fileFailNotDir), is(false));
		assertThat(filter.accept(fileSuccess), is(true));

		mocks.verify();
	}

}
