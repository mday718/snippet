package com.backedrum.component;

import com.backedrum.model.Screenshot;
import com.backedrum.service.ItemsService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.value.ValueMap;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class ScreenshotsPage extends BasePage {
    private static final long serialVersionUID = 1L;

    @Qualifier("screenshotService")
    @Inject
    private ItemsService<Screenshot> screenshotService;

    /**
     * Constructor that is invoked when page is invoked without a session.
     */
    public ScreenshotsPage() {
        add(new ScreenshotForm("screenshotsForm"));

        add(new PropertyListView<Screenshot>("screenshots", screenshotService.retrieveAllItems()) {
            @Override
            protected void populateItem(ListItem<Screenshot> listItem) {
                listItem.add(new Label("dateTime"));
                listItem.add(new Label("title"));

                listItem.add(new NonCachingImage("screenshotImage", new DynamicImageResource() {
                    @Override
                    protected byte[] getImageData(Attributes attributes) {
                        return listItem.getModelObject().getImage();
                    }
                }));
            }
        }).setVersioned(false);
    }

    public final class ScreenshotForm extends Form<ValueMap> {
        private FileUploadField fileUploadField;

        ScreenshotForm(String id) {
            super(id, new CompoundPropertyModel<>(new ValueMap()));

            setMarkupId("screenshotsForm");

            add(new TextField<>("title").setType(String.class));

            setMultiPart(true);

            add(fileUploadField = new FileUploadField("screenshotUpload"));

            setMaxSize(Bytes.kilobytes(512));
            setFileMaxSize(Bytes.kilobytes(512));
        }

        @Override
        protected void onSubmit() {
            ValueMap values = getModelObject();

            FileUpload last = fileUploadField.getFileUploads().get(fileUploadField.getFileUploads().size() - 1);

            Screenshot screenshot = Screenshot.builder()
                    .dateTime(LocalDateTime.now())
                    .title((String) values.get("title"))
                    .image(last.getBytes()).build();
            screenshotService.addItem(screenshot);

            values.put("title", "");

            setResponsePage(ScreenshotsPage.class);
        }
    }

}
