package Grammar.Expressions.Unary;

import Grammar.References.Reference;
import Grammar.SyntaxError;
import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Symbols.Values.Value;
import Tokens.Token;

import java.util.List;
import java.util.Map;

public class UnaryReferenceExpression extends UnaryExpression {
    private final Reference reference;

    public UnaryReferenceExpression(int startToken, int tokensCount, Reference reference) {
        super(startToken, tokensCount);
        this.reference = reference;
    }

    public static UnaryReferenceExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Reference reference = Reference.findNext(tokens, startToken);
        if (reference == null) return null;
        return new UnaryReferenceExpression(startToken, reference.getTokensCount(), reference);
    }

    @Override
    public Value evaluate(SymbolTable symbolTable) throws SymbolsError {
        return reference.getSymbolValue(symbolTable).getValue();
    }

    @Override
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        reference.analyse(symbolTable);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("reference", reference);
        return fields;
    }
}
