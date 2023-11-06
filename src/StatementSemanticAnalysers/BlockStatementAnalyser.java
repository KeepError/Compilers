package StatementSemanticAnalysers;

import Statement.BlockStatement;
import Symbols.SymbolTable;

public class BlockStatementAnalyser {
    public static void analyse(SymbolTable symbolTable, BlockStatement blockStatement) throws SemanticAnalyserError {
        System.out.println("Analysing block statement...");
        for (int i = 0; i < blockStatement.statements().size(); i++) {
            StatementAnalyser.analyse(symbolTable, blockStatement.statements().get(i));
        }
    }
}
