package pl.obol.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.obol.model.Publisher;
import pl.obol.service.PublisherService;

public class PublisherConverter implements Converter<String, Publisher> {

    @Autowired
    private PublisherService publisherService;

    @Override
    public Publisher convert(String s) {
        return publisherService.findPublisher(Long.parseLong(s));
    }
}
