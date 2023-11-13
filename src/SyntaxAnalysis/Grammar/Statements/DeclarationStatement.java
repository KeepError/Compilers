package SyntaxAnalysis.Grammar.Statements;

import LexicalAnalysis.Tokens.KeywordToken;
import LexicalAnalysis.Tokens.SeparatorToken;
import LexicalAnalysis.Tokens.Token;
import SyntaxAnalysis.Grammar.SyntaxError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeclarationStatement extends Statement {
    List<VariableDefinition> variableDefinitions;

    public DeclarationStatement(int startToken, int tokensCount, List<VariableDefinition> variableDefinitions) {
        super(startToken, tokensCount);
        this.variableDefinitions = variableDefinitions;
    }

    public static DeclarationStatement findNext(List<Token> tokens, int startToken) throws SyntaxError {
        int currentToken = startToken;
        Token token = tokens.get(currentToken);
        if (!(token instanceof KeywordToken && ((KeywordToken) token).getKeyword().equals("var"))) return null;
        currentToken++;
        if (currentToken > tokens.size()) return null;

        List<VariableDefinition> variableDefinitions = new ArrayList<>();
        int separatorCount = 0;
        boolean noSeparator = false;
        do {
            if (variableDefinitions.size() == separatorCount) {
                VariableDefinition variableDefinition = VariableDefinition.findNext(tokens, currentToken);
                if (variableDefinition == null) throw new SyntaxError(tokens.get(currentToken), "Variable definition is expected");
                currentToken += variableDefinition.getTokensCount();
                if (currentToken > tokens.size()) return null;
                variableDefinitions.add(variableDefinition);
            } else if (variableDefinitions.size() - 1 == separatorCount) {
                token = tokens.get(currentToken);
                if (token instanceof SeparatorToken && ((SeparatorToken) token).getSeparator().equals(",")) {
                    separatorCount++;
                    currentToken++;
                    if (currentToken > tokens.size()) return null;
                } else {
                    noSeparator = true;
                }
            } else {
                return null;
            }
        } while (!noSeparator);
        return new DeclarationStatement(startToken, currentToken - startToken, variableDefinitions);
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("variableDefinitions", variableDefinitions);
        return fields;
    }
}
