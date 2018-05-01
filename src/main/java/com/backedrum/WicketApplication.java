package com.backedrum;

import com.backedrum.component.AuthenticatedPage;
import com.backedrum.component.CodeSnippetsPage;
import com.backedrum.component.HowTosPage;
import com.backedrum.component.ScreenshotsPage;
import com.backedrum.component.SignIn;
import com.backedrum.component.SignedInSession;
import com.backedrum.component.SignedOut;
import com.backedrum.model.HowTo;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.https.HttpsConfig;
import org.apache.wicket.protocol.https.HttpsMapper;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.component.IRequestableComponent;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Starter class.
 *
 * @see Starter#main(String[])
 */
public class WicketApplication extends WebApplication {

    public WicketApplication() {
    }

    public Class getHomePage() {
        return CodeSnippetsPage.class;
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new SignedInSession(request);
    }

    @Override
    protected void init() {
        super.init();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));

        getSecuritySettings().setAuthorizationStrategy(new IAuthorizationStrategy.AllowAllAuthorizationStrategy() {
            public <T extends IRequestableComponent> boolean isInstantiationAuthorized(
                    Class<T> componentClass) {
                if (AuthenticatedPage.class.isAssignableFrom(componentClass)) {
                    if (((SignedInSession) Session.get()).isSignedIn()) {
                        return true;
                    }

                    throw new RestartResponseAtInterceptPageException(SignIn.class);
                }

                return true;
            }
        });

        setRootRequestMapper(new HttpsMapper(getRootRequestMapper(), new HttpsConfig(8080, 8443)));
    }
}
