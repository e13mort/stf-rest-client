package com.github.e13mort.stf.adapter.filters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static com.github.e13mort.stf.adapter.filters.InclusionType.EXCLUDE;
import static com.github.e13mort.stf.adapter.filters.InclusionType.INCLUDE;
import static org.junit.jupiter.api.Assertions.*;

public class StringsFilterParserTest {

    private StringsFilterParser parser;

    @BeforeEach
    public void setUp() throws Exception {
        parser = new StringsFilterParser();
    }

    @Test()
    public void testParseEmptyStringWillThrowException() throws Exception {
        assertThrows(IllegalArgumentException.class, test(""));
    }

    @Test()
    public void testParseNullStringWillThrowException() throws Exception {
        assertThrows(IllegalArgumentException.class, test(null));
    }

    @Test()
    public void testParseSignStringWillThrowException() throws Exception {
        assertThrows(IllegalArgumentException.class, test("~"));
    }

    @Test
    public void testTwoItemsStringWillReturnNotNullResult() throws Exception {
        StringsFilterDescription result = parse("item1,item2");
        assertNotNull(result);
    }

    @Test
    public void testNegativeTwoItemsStringWillReturnNotNullResult() throws Exception {
        StringsFilterDescription result = parse("!item1,item2");
        assertNotNull(result);
    }

    @Test
    public void testOneItemsStringWillReturnIncludeType() throws Exception {
        StringsFilterDescription result = parse("item1");
        assertEquals(INCLUDE, result.getType());
    }

    @Test
    public void testOneItemsStringWillReturnExcludeType() throws Exception {
        StringsFilterDescription result = parse("~item1");
        assertEquals(EXCLUDE, result.getType());
    }

    @Test
    public void testTwoItemsStringWillReturnIncludeType() throws Exception {
        StringsFilterDescription result = parse("item1,item2");
        assertEquals(INCLUDE, result.getType());
    }

    @Test
    public void testTwoItemsStringWillReturnExcludeType() throws Exception {
        StringsFilterDescription result = parse("~item1,item2");
        assertEquals(EXCLUDE, result.getType());
    }

    @Test
    public void testOneItemStringHasValidData() throws Exception {
        StringsFilterDescription result = parse("item1");
        assertEquals("item1", result.getTemplates().get(0));
    }

    @Test
    public void testNegativeOneItemStringHasValidData() throws Exception {
        StringsFilterDescription result = parse("~item1");
        assertEquals("item1", result.getTemplates().get(0));
    }

    @Test
    public void testTwoItemsStringHasValidData() throws Exception {
        StringsFilterDescription result = parse("item1,item2");
        assertEquals("item1", result.getTemplates().get(0));
        assertEquals("item2", result.getTemplates().get(1));
    }

    @Test
    public void testNegativeTwoItemsStringHasValidData() throws Exception {
        StringsFilterDescription result = parse("~item1,item2");
        assertEquals("item1", result.getTemplates().get(0));
        assertEquals("item2", result.getTemplates().get(1));
    }

    private StringsFilterDescription parse(String string) {
        return parser.parse(string);
    }

    private Executable test(final String string) {
        return new Executable() {
            @Override
            public void execute() throws Throwable {
                parse(string);
            }
        };
    }
}