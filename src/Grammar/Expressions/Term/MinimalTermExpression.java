package Grammar.Expressions.Term;

import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Symbols.Values.Value;
import Tokens.Token;
import Grammar.Expressions.Unary.UnaryExpression;
import Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class MinimalTermExpression extends TermExpression {
    private final UnaryExpression unaryExpression;

    public MinimalTermExpression(int startToken, int tokensCount, UnaryExpression unaryExpression) {
        super(startToken, tokensCount);
        this.unaryExpression = unaryExpression;
    }

    public static MinimalTermExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        UnaryExpression unaryExpression = UnaryExpression.findNext(tokens, startToken);
        if (unaryExpression == null) return null;
        return new MinimalTermExpression(startToken, unaryExpression.getTokensCount(), unaryExpression);
    }

    @Override
    public Value evaluate(SymbolTable symbolTable) throws SymbolsError {
        return unaryExpression.evaluate(symbolTable);
    }

    @Override
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        unaryExpression.analyse(symbolTable);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("unaryExpression", unaryExpression);
        return fields;
    }
}
