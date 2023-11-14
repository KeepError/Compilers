package Grammar.Expressions.Factor;

import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Tokens.Token;
import Grammar.Expressions.Term.TermExpression;
import Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class MinimalFactorExpression extends FactorExpression {
    private final TermExpression termExpression;

    public MinimalFactorExpression(int startToken, int tokensCount, TermExpression termExpression) {
        super(startToken, tokensCount);
        this.termExpression = termExpression;
    }

    public static MinimalFactorExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        TermExpression termExpression = TermExpression.findNext(tokens, startToken);
        if (termExpression == null) return null;
        return new MinimalFactorExpression(startToken, termExpression.getTokensCount(), termExpression);
    }

    @Override
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        termExpression.analyse(symbolTable);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("termExpression", termExpression);
        return fields;
    }
}
