package StatementSemanticAnalysers;

import Statement.BlockStatement;
import Statement.Statement;
import Symbols.SymbolTable;

public class BlockStatementAnalyser {
    public static void analyse(SymbolTable symbolTable, BlockStatement blockStatement) throws SemanticAnalyserError {
        System.out.println("Analysing block statement...");
        for (Statement statement : blockStatement.statements()) {
            StatementAnalyser.analyse(symbolTable, statement);
        }
    }
}
