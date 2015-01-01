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

import jp.l1j.mapgen.TileValueList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class TileValueListTest {

	@Test
	public void testToString() throws IOException {
		TileValueList tileList = new TileValueList();
		tileList.add(1);
		tileList.add(170);

		BufferedReader sr = new BufferedReader(new StringReader(tileList
				.toString()));
		assertThat(sr.readLine(), is("1 0000 0001"));
		assertThat(sr.readLine(), is("170 1010 1010"));
		assertThat(sr.readLine(), is(nullValue()));
	}
}
