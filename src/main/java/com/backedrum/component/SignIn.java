package com.backedrum.component;

import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class SignIn extends BasePage {

    public SignIn() {
        add(new SignInPanel("signInPanel", false));
    }
}
