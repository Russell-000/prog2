package org.vaadin.marcus.spring;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.marcus.spring.model.User;

/**
 * @author joe
 * @date 2021/6/15
 */
@Route("login.html")
public class LoginView extends VerticalLayout {

    private final LoginForm loginForm = new LoginForm();

    public LoginView() {
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        loginForm.setForgotPasswordButtonVisible(false);

        H1 h1 = new H1("TODO");

        HorizontalLayout regLayout = new HorizontalLayout();
        regLayout.setAlignItems(Alignment.CENTER);
        Label tips = new Label("Don't have an account?");
        Button regBtn = new Button("Sign up here.");
        regBtn.setThemeName("tertiary");
        regBtn.addClickListener(this::onSign);
        regLayout.add(tips, regBtn);

        loginForm.addLoginListener(this::onLogin);

        add(h1, regLayout, loginForm);
    }

    public void onSign(ClickEvent<Button> event) {
        getUI().ifPresent(ui -> ui.navigate("reg.html"));
    }

    public void onLogin(AbstractLogin.LoginEvent event) {
        String username = event.getUsername();
        String password = event.getPassword();
        for (User user : Reg.users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                Notification notification = new Notification(
                        "Login success", 3000, Notification.Position.MIDDLE);
                notification.open();
                return;
            }
        }
        loginForm.setError(true);
    }
}
