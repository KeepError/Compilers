import Statement.ProgramStatement;
import StatementSemanticAnalysers.ProgramStatementAnalyser;
import StatementSemanticAnalysers.SemanticAnalyserError;
import Symbols.SymbolTable;

public class SemanticAnalyser {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void analyse(ProgramStatement programStatement) {
        SymbolTable symbolTable = new SymbolTable();
        try {
            ProgramStatementAnalyser.analyse(symbolTable, programStatement);
        } catch (SemanticAnalyserError e) {
            System.out.println(ANSI_RED + "Semantic analyser error:");
            System.out.println(e.getMessage() + ANSI_RESET);
        }
    }
}