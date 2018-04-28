package com.backedrum.component;

import com.backedrum.model.HowTo;
import com.backedrum.service.ItemsService;
import lombok.val;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.value.ValueMap;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

public class HowTosPage extends BasePage implements AuthenticatedPage {

    private static final long serialVersionUID = 1L;

    @Qualifier("howtoService")
    @Inject
    private ItemsService<HowTo> howtoService;

    /**
     * Constructor that is invoked when page is invoked without a session.
     */
    public HowTosPage() {
        val form = new HowToForm("howtosForm");

        add(form);

        IModel<List<HowTo>> howToList = (IModel<List<HowTo>>) () -> howtoService.retrieveAllItems();

        val listContainer = new WebMarkupContainer("howtosContainer");
        listContainer.setOutputMarkupId(true);
        listContainer.add(new PropertyListView<HowTo>("howtos", howToList) {
            @Override
            protected void populateItem(ListItem<HowTo> listItem) {
                listItem.add(new Label("dateTime"));

                val removeLink = new AjaxSubmitLink("removeHowTo", form) {
                    @Override
                    public void onSubmit(AjaxRequestTarget target) {
                        howtoService.removeItem(listItem.getModelObject().getId());
                        target.add(listContainer);
                    }
                };
                removeLink.setDefaultFormProcessing(false);
                listItem.add(removeLink);

                listItem.add(new Label("title"));
                listItem.add(new MultiLineLabel("text"));
            }
        });

        add(listContainer).setVersioned(false);
    }

    public final class HowToForm extends Form<ValueMap> {

        HowToForm(String id) {
            super(id, new CompoundPropertyModel<>(new ValueMap()));

            setMarkupId("howtosForm");

            add(new TextField<>("title").setType(String.class));
            add(new TextArea<>("text").setType(String.class));
        }

        @Override
        protected void onSubmit() {
            ValueMap values = getModelObject();

            HowTo howto = HowTo.builder()
                    .dateTime(LocalDateTime.now())
                    .title((String) values.get("title"))
                    .text((String) values.get("text")).build();
            howtoService.addItem(howto);

            values.put("title", "");
            values.put("text", "");

            setResponsePage(HowTosPage.class);
        }
    }
}
