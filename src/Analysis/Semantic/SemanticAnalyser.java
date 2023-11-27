package Analysis.Semantic;

import Grammar.Program;
import Symbols.SymbolTable;
import Symbols.SymbolsError;

public class SemanticAnalyser {
    public static SymbolTable analyse(Program program) throws SymbolsError {
        SymbolTable symbolTable = new SymbolTable();
        program.analyse(symbolTable);
        program.optimise(symbolTable);
        return symbolTable;
    }
}
