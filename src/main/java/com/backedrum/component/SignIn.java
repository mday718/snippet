package com.backedrum.component;

import org.apache.wicket.authroles.authentication.panel.SignInPanel;

public class SignIn extends BasePage {

    public SignIn() {
        add(new SignInPanel("signInPanel", false));
    }
}
