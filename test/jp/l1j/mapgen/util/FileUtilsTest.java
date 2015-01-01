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

import jp.l1j.mapgen.util.FileUtils;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class FileUtilsTest {

	@Test
	public void testGetExtensionFile() {
		String result = FileUtils.getExtension(new File("test.ext"));
		assertThat(result, is("ext"));
		result = FileUtils.getExtension(new File("test..ext"));
		assertThat(result, is("ext"));
		result = FileUtils.getExtension(new File("ext"));
		assertThat(result, is(""));
		result = FileUtils.getExtension(new File(""));
		assertThat(result, is(""));
	}

	@Test
	public void testGetNameWithoutExtensionFile() {
		String result = FileUtils.getNameWithoutExtension(new File("test.ext"));
		assertThat(result, is("test"));
		result = FileUtils.getNameWithoutExtension(new File("test..ext"));
		assertThat(result, is("test."));
		result = FileUtils.getNameWithoutExtension(new File("test"));
		assertThat(result, is("test"));
		result = FileUtils.getNameWithoutExtension(new File(""));
		assertThat(result, is(""));
	}

}
