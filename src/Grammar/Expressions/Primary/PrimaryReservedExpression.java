package Grammar.Expressions.Primary;

import Grammar.SyntaxError;
import Symbols.SymbolTable;
import Symbols.SymbolsError;
import Symbols.Values.*;
import Tokens.KeywordToken;
import Tokens.Token;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PrimaryReservedExpression extends PrimaryExpression {
    private final String function;

    public PrimaryReservedExpression(int startToken, int tokensCount, String function) {
        super(startToken, tokensCount);
        this.function = function;
    }

    public static PrimaryReservedExpression findNext(List<Token> tokens, int startToken) throws SyntaxError {
        Token token = tokens.get(startToken);
        if (!(token instanceof KeywordToken)) {
            return null;
        }
        String keyword = ((KeywordToken) token).getKeyword();
        if (!keyword.equals("readInt") && !keyword.equals("readReal") && !keyword.equals("readString")) {
            return null;
        }
        return new PrimaryReservedExpression(startToken, 1, keyword);
    }

    @Override
    public Value evaluate(SymbolTable symbolTable) throws SymbolsError {
        Scanner scanner = new Scanner(System.in);
        try {
            if (function.equals("readInt")) {
                return new IntegerValue(scanner.nextInt());
            }
            if (function.equals("readReal")) {
                return new RealValue(scanner.nextDouble());
            }
            if (function.equals("readString")) {
                return new StringValue(scanner.nextLine());
            }
        } catch (InputMismatchException e) {
            throw new SymbolsError("Invalid input");
        }
        return new EmptyValue();
    }

    @Override
    public Map<String, Object> getJSONFields() {
        Map<String, Object> fields = super.getJSONFields();
        fields.put("function", function);
        return fields;
    }
}
