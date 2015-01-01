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

import jp.l1j.mapgen.writer.MapWriter;
import jp.l1j.mapgen.writer.MapWriterFactory;
import jp.l1j.mapgen.writer.V2MapWriter;
import jp.l1j.mapgen.writer.V1MapWriter;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import jp.l1j.mapgen.Config;

import org.junit.Test;

public class MapWriterFactoryTest {
	private void assertContainsClass(List<MapWriter> list, Class<?> expected) {
		for (MapWriter e : list) {
			if (e.getClass().getName().equals(expected.getName()))
				return;
		}
		fail("writers does not contain " + expected.getSimpleName());
	}

	@Test
	public void testNewWriters() throws IOException {
		List<MapWriter> writers = null;
		Config.load("OutputV1Maps: no\nOutputV2Maps: no");
		writers = MapWriterFactory.newWriters();
		assertThat(writers.isEmpty(), is(true));

		Config.load("OutputV1Maps: yes\nOutputV2Maps: yes");
		writers = MapWriterFactory.newWriters();
		assertThat(writers.size(), is(2));
		assertContainsClass(writers, V1MapWriter.class);
		assertContainsClass(writers, V2MapWriter.class);
	}
}
