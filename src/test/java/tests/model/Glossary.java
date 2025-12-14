package tests.model;
/**
 * @author Arsentiy Vavilov
 */
public class Glossary {

    private String title;
    private Integer id;
    private GlossaryInner glossary;

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public GlossaryInner getGlossary() {

        return glossary;
    }

    public void setGlossary(GlossaryInner glossary) {

        this.glossary = glossary;
    }
}
