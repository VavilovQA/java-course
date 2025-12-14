package tests.model;

/**
 * @author Arsentiy Vavilov
 */
public class GlossaryInner {
    private String sortAs;
    private String glossTerm;
    private String acronym;

    public String getSortAs() {

        return sortAs;
    }

    public void setSortAs(String sortAs) {

        this.sortAs = sortAs;
    }

    public String getGlossTerm() {

        return glossTerm;
    }

    public void setGlossTerm(String glossTerm) {

        this.glossTerm = glossTerm;
    }

    public String getAcronym() {

        return acronym;
    }

    public void setAcronym(String acronym) {

        this.acronym = acronym;
    }
}
