package Parser;

public class ParserChangeSens extends Parser {

    public ParserChangeSens(Parser suivant) {
        super(suivant);
    }

    public boolean saitParser(String ligne) {
        if (ligne.matches(".*CASE DEPART.*"))
            return true;
        return false;
    }

    public void parser(String ligne) throws Exception {
        String tab[] = ligne.split(";");
        System.out.println("La case départ est a la position" + tab[0]);
    }

}

