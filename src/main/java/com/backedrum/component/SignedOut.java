package com.backedrum.component;

public class SignedOut extends BasePage {

    public SignedOut() {
        getSession().invalidate();
    }
}
