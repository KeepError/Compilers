import java.util.List;

import Token.Token;
import Statement.ProgramStatement;

public class SyntaxAnalyser {
    public static ProgramStatement analyse(List<Token> tokens) {
        return (ProgramStatement) ProgramStatement.parse(tokens, 0).statement();
    }
}
