package Grammar.Literals;

import Grammar.Literals.FunctionBodies.FunctionBody;
import Grammar.SyntaxError;
import Symbols.ScopeType;
import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Symbols.Values.FunctionValue;
import Symbols.Values.Value;
import Tokens.IdentifierToken;
import Tokens.KeywordToken;
import Tokens.SeparatorToken;
import Tokens.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FunctionLiteral extends Literal {
    List<String> parameters;
    FunctionBody functionBody;

    public FunctionLiteral(int startToken, int tokensCount, List<String> parameters, FunctionBody functionBody) {
        super(startToken, tokensCount);
        this.parameters = parameters;
        this.functionBody = functionBody;
    }

    public static FunctionLiteral findNext(List<Token> tokens, int startToken) throws SyntaxError {
        int currentToken = startToken;
        Token token = tokens.get(currentToken);
        if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("func"))) {
            return null;
        }
        currentToken++;
        token = tokens.get(currentToken);
        List<String> parameters = new ArrayList<>();
        if (token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals("(")) {
            currentToken++;
            int separatorCount = 0;
            String separator = "";
            do {
                token = tokens.get(currentToken);
                if (separatorCount == parameters.size()) {
                    if (!(token instanceof IdentifierToken)) return null;
                    parameters.add(((IdentifierToken) token).getIdentifier());
                } else if (separatorCount == parameters.size() - 1) {
                    if (!(token instanceof SeparatorToken)) return null;
                    separator = ((SeparatorToken) token).getSeparator();
                    if (!(separator.equals(",") || separator.equals(")"))) return null;
                    separatorCount++;
                } else {
                    return null;
                }
                currentToken++;
            } while (currentToken <= tokens.size() && !(separator.equals(")")));
            if (separatorCount != parameters.size()) return null;
        }
        if (currentToken >= tokens.size()) return null;
        FunctionBody functionBody = FunctionBody.findNext(tokens, currentToken);
        if (functionBody == null) return null;
        currentToken += functionBody.getTokensCount();
        return new FunctionLiteral(startToken, currentToken - startToken, parameters, functionBody);
    }

    @Override
    public Value getValue(SymbolTable symbolTable) throws SymbolsError {
        symbolTable.assignGrammar(functionBody);
        return new FunctionValue(parameters, functionBody);
    }

    @Override
    public void analyse(SymbolTable symbolTable) throws SymbolsError {
        symbolTable.enterScope(ScopeType.FUNCTION);
        for (String parameter : parameters) {
            symbolTable.getCurrentScope().define(parameter);
        }
        functionBody.analyse(symbolTable);
        symbolTable.exitScope();
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("parameters", parameters);
        fields.put("functionBody", functionBody);
        return fields;
    }
}
