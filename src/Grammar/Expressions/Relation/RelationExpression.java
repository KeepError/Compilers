package Grammar.Expressions.Relation;

import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Symbols.Values.Value;
import Tokens.Token;
import Grammar.Grammar;
import Grammar.SyntaxError;

import java.util.List;

public abstract class RelationExpression extends Grammar {
    public RelationExpression(int startToken, int tokensCount) {
        super(startToken, tokensCount);
    }

    public static RelationExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        RelationExpression relationExpression;
        relationExpression = FullRelationExpression.findNext(tokens, startToken);
        if (relationExpression != null) return relationExpression;
        relationExpression = MinimalRelationExpression.findNext(tokens, startToken);
        return relationExpression;
    }

    public abstract Value evaluate(SymbolTable symbolTable) throws SymbolsError;
}
