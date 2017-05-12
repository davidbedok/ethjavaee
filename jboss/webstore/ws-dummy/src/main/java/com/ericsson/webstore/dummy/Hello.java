package com.ericsson.webstore.dummy;

import com.ericsson.webstore.common.doclet.Description;

@Description("This is a Hello class")
public class Hello {

	@Description("This is a Dummy generator")
	public String world(int a, int b) {
		return "Dummy";
	}

}
