package com.backedrum.component;

import com.backedrum.model.HowTo;
import com.backedrum.service.ItemsService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.value.ValueMap;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class HowTosPage extends BasePage {

	private static final long serialVersionUID = 1L;

	@Qualifier("howtoService")
	@Inject
    private ItemsService<HowTo> howtoService;

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 */
    public HowTosPage() {
        add(new HowToForm("howtosForm"));

        add(new PropertyListView<HowTo>("howtos", howtoService.retrieveAllItems()) {
            @Override
            protected void populateItem(ListItem<HowTo> listItem) {
                listItem.add(new Label("dateTime"));
                listItem.add(new Label("title"));
                listItem.add(new MultiLineLabel("text"));
            }
        }).setVersioned(false);
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
		}
	}
}
