package com.backedrum.component;


import com.backedrum.service.UserServiceImpl;
import lombok.Getter;
import lombok.val;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Base64;

public final class SignedInSession extends AuthenticatedWebSession {
    @Getter
    private String user;

    @SpringBean(name = "userService")
    private UserServiceImpl userService;

    public SignedInSession(Request request) {
        super(request);
    }

    @Override
    public final boolean authenticate(final String username, final String password) {
        if (user == null) {
            Injector.get().inject(this);

            val u = userService.findUser(username);
            if (u != null && Base64.getEncoder().encodeToString(password.getBytes()).equals(u.getPassword())) {
                user = username;
            }
        }

        return user != null;
    }

    @Override
    public Roles getRoles() {
        return isSignedIn() ? new Roles(Roles.ADMIN) : null;
    }
}
