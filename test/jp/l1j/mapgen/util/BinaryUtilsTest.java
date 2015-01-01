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

import jp.l1j.mapgen.util.BinaryUtils;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class BinaryUtilsTest {

	@Test
	public void testGetBit() {
		// 170 = 1010 1010
		assertThat(BinaryUtils.getBit(170, 1), is(0));
		assertThat(BinaryUtils.getBit(170, 2), is(1));
		assertThat(BinaryUtils.getBit(170, 3), is(0));
		assertThat(BinaryUtils.getBit(170, 4), is(1));
		assertThat(BinaryUtils.getBit(170, 5), is(0));
		assertThat(BinaryUtils.getBit(170, 6), is(1));
		assertThat(BinaryUtils.getBit(170, 7), is(0));
		assertThat(BinaryUtils.getBit(170, 8), is(1));
	}

	@Test
	public void testIntToBinaryString() {
		assertThat(BinaryUtils.byteToBinaryString((byte) 170), is("1010 1010"));
	}

}
