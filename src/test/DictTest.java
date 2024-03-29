// ================================
// Built-in modules

import java.util.ArrayList;

// ================================
// Third-party modules

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

// ================================
// User-defined modules

import main.model.dict.Dict;

// ================================
// Class DictTest

/**
 * Tests of Dict
 *
 * @author ericwen229
 */
public class DictTest {

	/**
	 * Test precise search of Dict
	 */
	@Test
	public void testPrecise() {
		Dict d = Dict.createDict();

		d.insert("eric", null);
		d.insert("arya", null);
		d.insert("frank", null);
		d.insert("price", null);
		d.insert("chuck", null);

		assertTrue(d.searchWithCommonPrefix("eric", 10).contains("eric"));
		assertTrue(d.searchWithCommonPrefix("arya", 10).contains("arya"));
		assertTrue(d.searchWithCommonPrefix("frank", 10).contains("frank"));
		assertTrue(d.searchWithCommonPrefix("price", 10).contains("price"));
		assertTrue(d.searchWithCommonPrefix("chuck", 10).contains("chuck"));

		assertTrue(d.searchWithEditDist("eric", 0).contains("eric"));
		assertTrue(d.searchWithEditDist("arya", 0).contains("arya"));
		assertTrue(d.searchWithEditDist("frank", 0).contains("frank"));
		assertTrue(d.searchWithEditDist("price", 0).contains("price"));
		assertTrue(d.searchWithEditDist("chuck", 0).contains("chuck"));
	}

	/**
	 * Test with-common-prefix search of Dict
	 */
	@Test
	public void testCommonPrefix() {
		Dict d = Dict.createDict();

		d.insert("hello", null);
		d.insert("hello there", null);
		d.insert("hello world", null);
		d.insert("hello eric", null);
		d.insert("hello arya", null);

		d.insert("ello", null);
		d.insert("hell", null);
		d.insert("hella", null);
		d.insert("hallo", null);
		d.insert("olleh", null);

		ArrayList<String> result = d.searchWithCommonPrefix("hello", 10);
		assertTrue(result.contains("hello"));
		assertTrue(result.contains("hello there"));
		assertTrue(result.contains("hello world"));
		assertTrue(result.contains("hello eric"));
		assertTrue(result.contains("hello arya"));
		assertFalse(result.contains("ello"));
		assertFalse(result.contains("hell"));
		assertFalse(result.contains("hella"));
		assertFalse(result.contains("hallo"));
		assertFalse(result.contains("olleh"));
	}

	/**
	 * Test with-edit-distance search of Dict
	 */
	@Test
	public void testEditDistance() {
		Dict d = Dict.createDict();

		d.insert("hello", null);

		d.insert("ello", null);
		d.insert("hllo", null);
		d.insert("helo", null);
		d.insert("hell", null);

		d.insert("xello", null);
		d.insert("hxllo", null);
		d.insert("hexlo", null);
		d.insert("helxo", null);
		d.insert("hellx", null);

		d.insert("xhello", null);
		d.insert("hxello", null);
		d.insert("hexllo", null);
		d.insert("helxlo", null);
		d.insert("hellxo", null);
		d.insert("hellox", null);

		ArrayList<String> result = d.searchWithEditDist("hello", 1);
		assertTrue(result.contains("hello"));
		assertTrue(result.contains("ello"));
		assertTrue(result.contains("hllo"));
		assertTrue(result.contains("helo"));
		assertTrue(result.contains("hell"));
		assertTrue(result.contains("xello"));
		assertTrue(result.contains("hxllo"));
		assertTrue(result.contains("hexlo"));
		assertTrue(result.contains("helxo"));
		assertTrue(result.contains("hellx"));
		assertTrue(result.contains("xhello"));
		assertTrue(result.contains("hxello"));
		assertTrue(result.contains("hexllo"));
		assertTrue(result.contains("helxlo"));
		assertTrue(result.contains("hellxo"));
		assertTrue(result.contains("hellox"));
	}

}
