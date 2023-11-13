package SyntaxAnalysis.Grammar.Statements;

import LexicalAnalysis.Tokens.OperatorToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.Expressions.Expression;
import SyntaxAnalysis.Grammar.References.Reference;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.List;
import java.util.Map;

public class AssignmentStatement extends Statement {
    Reference reference;
    Expression expression;

    public AssignmentStatement(int startToken, int tokensCount, Reference reference, Expression expression) {
        super(startToken, tokensCount);
        this.reference = reference;
        this.expression = expression;
    }

    public static AssignmentStatement findNext(List<Token> tokens, int startToken) throws SyntaxError {
        int currentToken = startToken;
        Reference reference = Reference.findNext(tokens, currentToken);
        if (reference == null) return null;
        currentToken += reference.getTokensCount();
        if (currentToken > tokens.size()) return null;

        Token token = tokens.get(currentToken);
        if (!(token instanceof OperatorToken && ((OperatorToken) token).getOperator().equals(":="))) {
            return null;
        }
        currentToken++;
        if (currentToken > tokens.size()) return null;

        Expression expression = Expression.findNext(tokens, currentToken);
        if (expression == null) throw new SyntaxError(tokens.get(currentToken), "Expression is expected");
        currentToken += expression.getTokensCount();
        if (currentToken > tokens.size()) return null;

        return new AssignmentStatement(startToken, currentToken - startToken, reference, expression);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("reference", reference);
        fields.put("expression", expression);
        return fields;
    }
}
