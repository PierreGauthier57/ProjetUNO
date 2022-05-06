package Parser;

public class ParserChangeSens extends Parser {

        public ParserChangeSens(Parser suivant){
            super(suivant);
        }
        public boolean saitParser(String ligne){
            if(ligne.matches(".*CASE DEPART.*"))
                return true;
            return false;
        }


    }

