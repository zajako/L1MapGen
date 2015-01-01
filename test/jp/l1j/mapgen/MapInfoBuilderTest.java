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

import jp.l1j.mapgen.MapInfoBuilder;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class MapInfoBuilderTest {

	@Test
	public void testSave() throws IOException {
		MapInfoBuilder info = new MapInfoBuilder();
		info.add(0, 0, 10, 10, 15);
		info.add(1, 1, 11, 11, 20);

		BufferedReader br = new BufferedReader(
				new StringReader(info.toString()));
		assertThat(br.readLine(),
				is("private static final int[][] MAP_INFO = {"));
		assertThat(br.readLine(), is("\t\t{ 0, 0, 10, 10, 15 },"));
		assertThat(br.readLine(), is("\t\t{ 1, 1, 11, 11, 20 } };"));
		assertThat(br.readLine(), is(nullValue()));
	}

}
