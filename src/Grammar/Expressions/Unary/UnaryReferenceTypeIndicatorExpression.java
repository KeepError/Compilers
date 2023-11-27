package Grammar.Expressions.Unary;

import Grammar.References.Reference;
import Grammar.SyntaxError;
import Grammar.TypeIndicators.*;
import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Symbols.Values.*;
import Tokens.KeywordToken;
import Tokens.Token;

import java.util.List;
import java.util.Map;

public class UnaryReferenceTypeIndicatorExpression extends UnaryExpression {
    private final Reference reference;
    private final TypeIndicator typeIndicator;

    public UnaryReferenceTypeIndicatorExpression(int startToken, int tokensCount, Reference reference, TypeIndicator typeIndicator) {
        super(startToken, tokensCount);
        this.reference = reference;
        this.typeIndicator = typeIndicator;
    }

    public static UnaryReferenceTypeIndicatorExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        int currentToken = startToken;
        Reference reference = Reference.findNext(tokens, currentToken);
        if (reference == null) return null;
        currentToken += reference.getTokensCount();
        if (currentToken > tokens.size()) return null;

        Token token = tokens.get(currentToken);
        if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("is"))) {
            return null;
        }
        currentToken++;
        if (currentToken > tokens.size()) return null;

        TypeIndicator typeIndicator = TypeIndicator.findNext(tokens, currentToken);
        if (typeIndicator == null) return null;
        currentToken += typeIndicator.getTokensCount();

        return new UnaryReferenceTypeIndicatorExpression(startToken, currentToken - startToken, reference, typeIndicator);
    }

    @Override
    public Value evaluate(SymbolTable symbolTable) throws SymbolsError {
        Value value = reference.getSymbolValue(symbolTable).getValue();
        return switch (typeIndicator) {
            case IntegerTypeIndicator ignoredIntegerTypeIndicator -> new BooleanValue(value instanceof IntegerValue);
            case RealTypeIndicator ignoredRealTypeIndicator -> new BooleanValue(value instanceof RealValue);
            case StringTypeIndicator ignoredStringTypeIndicator -> new BooleanValue(value instanceof StringValue);
            case BooleanTypeIndicator ignoredBooleanTypeIndicator -> new BooleanValue(value instanceof BooleanValue);
            case ArrayTypeIndicator ignoredArrayTypeIndicator -> new BooleanValue(value instanceof ArrayValue);
            case TupleTypeIndicator ignoredTupleTypeIndicator -> new BooleanValue(value instanceof TupleValue);
            case FunctionTypeIndicator ignoredFunctionTypeIndicator -> new BooleanValue(value instanceof FunctionValue);
            case EmptyTypeIndicator ignoredEmptyTypeIndicator -> new BooleanValue(value instanceof EmptyValue);
            case null, default -> new BooleanValue(false);
        };
    }

    @Override
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        reference.analyse(symbolTable);
        typeIndicator.analyse(symbolTable);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("reference", reference);
        fields.put("typeIndicator", typeIndicator);
        return fields;
    }
}
