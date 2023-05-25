package decompressor.dictionary;

public record Position(double offsetRight, int depth) {

    @Override
    public String toString() {
        return "Offset to right: " + offsetRight + "; Depth: " + depth;
    }
}
