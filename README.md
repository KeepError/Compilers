# 💻 Triple D

### Project for Compiler Construction Course in Innopolis University, F23

![14](https://github.com/KeepError/Dynamic-Language-Compiler/assets/88327718/05c85883-df74-472b-9cdc-a1cdd4996286)


## Common Description

    Object types are not specified and can change during program execution.
    The language is interpreted.
    Major notions: variable & literal (constant).
    Program structure: a sequence of declarations and statements.
    Builtin types: integer, real, boolean, string. User-defined types: array, tuple, function.
    Implicit type conversions.
    Statements: assignment, if/while, return, input/output.

## Informal Language Specification
Program Structure

The program is a sequence of statements, separated by semicolons or newline characters.
Statements

Statements execute sequentially, starting with the first in-text statement. Two categories: variable declaration and operators.
Variable Declaration

    Can be in any position within the program.
    Variable introduced by a declaration is active within its scope.
    Nested scopes may have variables with the same name, hiding variables from the enclosing scope.

Variable Type

Variable type is not fixed during declaration, allowing it to take any type of values within its scope.
Operations

    Common set of statements: assignment, conditional statement, repeat statements, function return, and print values.

Expressions

Syntax constructs defining an algorithm for calculating new values.
Reference

Defines accessors for variables, including array element access, function calls, and object element access.
Types and Literals

    Four simple types: integer, real, string, boolean.
    Two composite types: arrays and tuples.
    Special type: empty.
    Literals: integer, real, string, boolean, tuple, array, empty.

## Grammar

Defines the grammar for D language, including program structure, declarations, expressions, and statements.
## Semantics of Basic Operations

Defines the semantics of basic operations such as addition, subtraction, and assignment for different operand types.
## Structure of a D-Processor

Describes the structure of a D-processor, outlining the basic operations and their semantics.
