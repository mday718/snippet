package com.backedrum;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.value.ValueMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;

	private static final List<SourceCodeSnippet> snippets = new ArrayList<>();

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public HomePage(final PageParameters parameters) {
        super(parameters);

        add(new SourceCodeSnippetForm("snippetsForm"));

        add(new PropertyListView<SourceCodeSnippet>("snippets", snippets) {
            @Override
            protected void populateItem(ListItem<SourceCodeSnippet> listItem) {
                listItem.add(new Label("date"));
                listItem.add(new Label("title"));
                listItem.add(new MultiLineLabel("sourceCode"));
            }
        }).setVersioned(false);
    }

    public final class SourceCodeSnippetForm extends Form<ValueMap> {

		SourceCodeSnippetForm(String id) {
			super(id, new CompoundPropertyModel<>(new ValueMap()));

			setMarkupId("snippetsForm");

			add(new TextField<>("title").setType(String.class));
			add(new TextArea<>("sourceCode").setType(String.class));
		}

		@Override
		protected void onSubmit() {
			ValueMap values = getModelObject();

			SourceCodeSnippet snippet = SourceCodeSnippet.builder()
					.date(LocalDateTime.now())
					.title((String) values.get("title"))
					.sourceCode((String) values.get("sourceCode")).build();
			snippets.add(0, snippet);

			values.put("title", "");
			values.put("sourceCode", "");
		}
	}
}
