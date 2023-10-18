package Statement;

public record ParseResult<T extends Statement>(T statement, int tokensParsed) {
}
