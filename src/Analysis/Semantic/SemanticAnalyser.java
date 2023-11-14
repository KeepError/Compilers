package Analysis.Semantic;

import Grammar.Program;
import Symbols.SymbolTable;
import Symbols.SymbolsError;

public class SemanticAnalyser {
    public static void analyse(Program program) throws SymbolsError {
        SymbolTable symbolTable = new SymbolTable();
        program.analyse(symbolTable);
        program.optimise(symbolTable);
    }
}
