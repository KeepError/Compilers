package Grammar.Expressions.Expressions;

import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Symbols.Values.Value;
import Tokens.Token;
import Grammar.Expressions.Relation.RelationExpression;
import Grammar.Expressions.Expression;
import Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class MinimalExpression extends Expression {
    private final RelationExpression relationExpression;

    public MinimalExpression(int startToken, int tokensCount, RelationExpression relationExpression) {
        super(startToken, tokensCount);
        this.relationExpression = relationExpression;
    }

    public static MinimalExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        RelationExpression relationExpression = RelationExpression.findNext(tokens, startToken);
        if (relationExpression == null) return null;
        return new MinimalExpression(startToken, relationExpression.getTokensCount(), relationExpression);
    }

    @Override
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        relationExpression.analyse(symbolTable);
    }

    @Override
    public Value evaluate(SymbolTable symbolTable) throws SymbolsError {
        return relationExpression.evaluate(symbolTable);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("relationExpression", relationExpression);
        return fields;
    }
}
