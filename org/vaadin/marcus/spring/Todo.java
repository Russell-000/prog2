package org.vaadin.marcus.spring;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinRequest;

import javax.servlet.http.Cookie;

@Route("")
public class Todo extends VerticalLayout implements AfterNavigationObserver {

    private final VerticalLayout todoList = new VerticalLayout();
    private final TextField todoField = new TextField();

    public Todo() {
        Button button = new Button("add");
        button.addClickListener(this::onAdd);
        add(new H1("TODO"),
                new H4("Hello " + getUserName()),
                todoList,
                new HorizontalLayout(todoField, button));
    }

    public String getUserName() {
        VaadinRequest request = VaadinService.getCurrentRequest();
        if (request == null) {
            return "";
        }
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return "";
        }
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return "";
    }

    public void onAdd(ClickEvent<Button> event) {
        String todovalue = todoField.getValue();
        Checkbox checkbox = new Checkbox(todovalue);
        todoList.add(checkbox);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        VaadinRequest request = VaadinService.getCurrentRequest();
        if (request == null) {
            return;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    return;
                }
            }
        }

        getUI().ifPresent(ui -> ui.navigate("login.html"));
    }
}
