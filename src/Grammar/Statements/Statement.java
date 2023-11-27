package Grammar.Statements;

import Grammar.Grammar;
import Grammar.SyntaxError;
import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Symbols.Values.Value;
import Tokens.Token;

import java.util.List;

public abstract class Statement extends Grammar {
    public Statement(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static Statement findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Statement statement;
        statement = DeclarationStatement.findNext(tokens, startToken);
        if (statement != null) return statement;
        statement = AssignmentStatement.findNext(tokens, startToken);
        if (statement != null) return statement;
        statement = IfStatement.findNext(tokens, startToken);
        if (statement != null) return statement;
        statement = WhileLoopStatement.findNext(tokens, startToken);
        if (statement != null) return statement;
        statement = ForLoopStatement.findNext(tokens, startToken);
        if (statement != null) return statement;
        statement = ReturnStatement.findNext(tokens, startToken);
        if (statement != null) return statement;
        statement = PrintStatement.findNext(tokens, startToken);
        return statement;
    }

    public abstract Value execute(SymbolTable symbolTable) throws SymbolsError;
}
