package Grammar.Expressions.Relation;

import Tokens.Token;
import Grammar.Expressions.Factor.FactorExpression;
import Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class MinimalRelationExpression extends RelationExpression {
    private final FactorExpression factorExpression;

    public MinimalRelationExpression(int startToken, int tokensCount, FactorExpression factorExpression) {
        super(startToken, tokensCount);
        this.factorExpression = factorExpression;
    }

    public static MinimalRelationExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        FactorExpression factorExpression = FactorExpression.findNext(tokens, startToken);
        if (factorExpression == null) return null;
        return new MinimalRelationExpression(startToken, factorExpression.getTokensCount(), factorExpression);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("factorExpression", factorExpression);
        return fields;
    }
}
