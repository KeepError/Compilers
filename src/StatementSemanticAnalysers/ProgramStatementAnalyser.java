package StatementSemanticAnalysers;

import Statement.ProgramStatement;
import Symbols.SymbolTable;
import Symbols.ScopeType;

public class ProgramStatementAnalyser {
    public static void analyse(SymbolTable symbolTable, ProgramStatement programStatement) throws SemanticAnalyserError {
        symbolTable.enterScope(ScopeType.PROGRAM);
        System.out.println("Analysing program statement...");
        BlockStatementAnalyser.analyse(symbolTable, programStatement.body());
        symbolTable.exitScope();
    }
}
