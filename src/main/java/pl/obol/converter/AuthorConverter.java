package pl.obol.converter;

import org.springframework.core.convert.converter.Converter;
import pl.obol.model.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorConverter implements Converter<String, List<Author>> {
    @Override

    public List<Author> convert(String s) {
        String[] authors = s.split(",");
        List<Author> authorList = new ArrayList<>();
        for(String a: authors){
            authorList.add(new Author(a.trim()));
        }
        return authorList;
    }
}
