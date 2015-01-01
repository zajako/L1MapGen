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

import jp.l1j.mapgen.MapDirComparator;
import static org.junit.Assert.*;

import java.io.File;
import java.util.Comparator;

import org.junit.Test;

public class MapDirComparatorTest {

	@Test
	public void testCompare() {
		Comparator<File> comp = new MapDirComparator();
		assertTrue(comp.compare(new File("1"), new File("2")) < 0);
		assertTrue(comp.compare(new File("2"), new File("1")) > 0);
		assertTrue(comp.compare(new File("1"), new File("1")) == 0);

		assertTrue(comp.compare(new File("1"), new File("10")) < 0);
		assertTrue(comp.compare(new File("10"), new File("1")) > 0);
		assertTrue(comp.compare(new File("01"), new File("1")) == 0);
	}

}
