package com.backedrum;

import com.backedrum.component.CodeSnippetsPage;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void testRenderMyPage()
	{
		//start and render the test page
		tester.startPage(CodeSnippetsPage.class);

		//assert rendered page class
		tester.assertRenderedPage(CodeSnippetsPage.class);
	}
}
