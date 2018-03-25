package com.backedrum.component;

import com.backedrum.model.SourceCodeSnippet;
import com.backedrum.service.SnippetService;
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
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.value.ValueMap;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;

	@Inject
    private SnippetService snippetService;

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 */
    public HomePage(final PageParameters parameters) {
        super(parameters);

        add(new SourceCodeSnippetForm("snippetsForm"));

        add(new PropertyListView<SourceCodeSnippet>("snippets", snippetService.retrieveAllSnippets()) {
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
			snippetService.addSnippet(snippet);

			values.put("title", "");
			values.put("sourceCode", "");
		}
	}
}
