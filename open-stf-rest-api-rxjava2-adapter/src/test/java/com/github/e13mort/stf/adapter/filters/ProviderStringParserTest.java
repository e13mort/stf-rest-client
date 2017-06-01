package com.github.e13mort.stf.adapter.filters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.github.e13mort.stf.adapter.filters.ProviderPredicate.Type.EXCLUDE;
import static com.github.e13mort.stf.adapter.filters.ProviderPredicate.Type.INCLUDE;
import static org.junit.Assert.assertEquals;

public class ProviderStringParserTest {

    private ProviderStringParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new ProviderStringParser();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseEmptyStringWillThrowException() throws Exception {
        parse("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseNullStringWillThrowException() throws Exception {
        parse(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseSignStringWillThrowException() throws Exception {
        parse("-");
    }

    @Test
    public void testTwoItemsStringWillReturnNotNullResult() throws Exception {
        ProviderDescription result = parse("item1,item2");
        Assert.assertNotNull(result);
    }

    @Test
    public void testNegativeTwoItemsStringWillReturnNotNullResult() throws Exception {
        ProviderDescription result = parse("!item1,item2");
        Assert.assertNotNull(result);
    }

    @Test
    public void testOneItemsStringWillReturnIncludeType() throws Exception {
        ProviderDescription result = parse("item1");
        assertEquals(INCLUDE, result.getType());
    }

    @Test
    public void testOneItemsStringWillReturnExcludeType() throws Exception {
        ProviderDescription result = parse("-item1");
        assertEquals(EXCLUDE, result.getType());
    }

    @Test
    public void testTwoItemsStringWillReturnIncludeType() throws Exception {
        ProviderDescription result = parse("item1,item2");
        assertEquals(INCLUDE, result.getType());
    }

    @Test
    public void testTwoItemsStringWillReturnExcludeType() throws Exception {
        ProviderDescription result = parse("-item1,item2");
        assertEquals(EXCLUDE, result.getType());
    }

    @Test
    public void testOneItemStringHasValidData() throws Exception {
        ProviderDescription result = parse("item1");
        assertEquals("item1", result.getTemplates().get(0));
    }

    @Test
    public void testNegativeOneItemStringHasValidData() throws Exception {
        ProviderDescription result = parse("-item1");
        assertEquals("item1", result.getTemplates().get(0));
    }

    @Test
    public void testTwoItemsStringHasValidData() throws Exception {
        ProviderDescription result = parse("item1,item2");
        assertEquals("item1", result.getTemplates().get(0));
        assertEquals("item2", result.getTemplates().get(1));
    }

    @Test
    public void testNegativeTwoItemsStringHasValidData() throws Exception {
        ProviderDescription result = parse("-item1,item2");
        assertEquals("item1", result.getTemplates().get(0));
        assertEquals("item2", result.getTemplates().get(1));
    }

    private ProviderDescription parse(String string) {
        return parser.parse(string);
    }
}