import Statement.ProgramStatement;
import StatementSemanticAnalysers.ProgramStatementAnalyser;
import StatementSemanticAnalysers.SemanticAnalyserError;
import Symbols.SymbolTable;

public class SemanticAnalyser {
    public static void analyse(ProgramStatement programStatement) {
        SymbolTable symbolTable = new SymbolTable();
        try {
            ProgramStatementAnalyser.analyse(symbolTable, programStatement);
        } catch (SemanticAnalyserError e) {
            System.out.println("Semantic analyser error!!!!!!!!!!");
            System.out.println(e.getMessage());
        }
    }
}