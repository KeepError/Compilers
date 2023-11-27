package Interpretation;

import Grammar.Program;
import Symbols.SymbolTable;
import Symbols.SymbolsError;

public class Interpreter {
    public static void interpret(Program program) throws SymbolsError {
        SymbolTable symbolTable = new SymbolTable();
        program.execute(symbolTable);
    }
}
