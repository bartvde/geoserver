/* Copyright (c) 2001 - 2013 OpenPlans - www.openplans.org. All rights reserved.
 * This code is licensed under the GPL 2.0 license, available at the root
 * application directory.
 */
package org.geoserver.wfs.json;

import java.util.HashMap;
import java.util.Map;

import org.geoserver.json.JSONType;
import org.junit.Test;

import junit.framework.TestCase;

public class JSONTypeTest extends TestCase {

    @Test
    public void testMimeType() {

        // MimeType
        assertNotSame(JSONType.json, JSONType.jsonp);
        assertTrue(JSONType.isJsonMimeType(JSONType.json));

        // disable JsonP
        JSONType.setJsonpEnabled(true);
        // check jsonp is enabled
        assertTrue(JSONType.useJsonp(JSONType.jsonp));

        // disable JsonP
        JSONType.setJsonpEnabled(false);
        assertFalse(JSONType.useJsonp(JSONType.jsonp));
    }

    @Test
    public void testJSONType() {
        // ENUM type
        JSONType json = JSONType.JSON;
        assertEquals(JSONType.JSON, json);
        JSONType jsonp = JSONType.JSONP;
        assertEquals(JSONType.JSONP, jsonp);

        assertEquals(JSONType.JSON, JSONType.getJSONType(JSONType.json));
        assertEquals(JSONType.JSONP, JSONType.getJSONType(JSONType.jsonp));
    }

    @Test
    public void testCallbackFunction() {
        Map<String, Map<String, String>> kvp = new HashMap<String, Map<String, String>>();

        assertEquals(JSONType.CALLBACK_FUNCTION, JSONType.getCallbackFunction(kvp));

        Map<String, String> formatOpts = new HashMap<String, String>();
        kvp.put("FORMAT_OPTIONS", formatOpts);

        assertEquals(JSONType.CALLBACK_FUNCTION, JSONType.getCallbackFunction(kvp));

        formatOpts.put(JSONType.CALLBACK_FUNCTION_KEY, "functionName");

        assertEquals("functionName", JSONType.getCallbackFunction(kvp));
    }

}
