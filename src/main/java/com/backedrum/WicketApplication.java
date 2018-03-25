package com.backedrum;

import com.backedrum.component.CodeSnippetsPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Starter class.
 * 
 * @see Starter#main(String[])
 */
public class WicketApplication extends WebApplication
{    
	public WicketApplication() {}

	public Class getHomePage() {
		return CodeSnippetsPage.class;
	}

	@Override
	protected void init() {
		super.init();
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
	}
}
