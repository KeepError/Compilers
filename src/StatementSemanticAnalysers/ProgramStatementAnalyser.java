package StatementSemanticAnalysers;

import Statement.ProgramStatement;
import Symbols.SymbolTable;

public class ProgramStatementAnalyser {
    public static void analyse(SymbolTable symbolTable, ProgramStatement programStatement) throws SemanticAnalyserError {
        symbolTable.enterScope();
        System.out.println("Analysing program statement...");
        BlockStatementAnalyser.analyse(symbolTable, programStatement.body());
        symbolTable.exitScope();
    }
}
