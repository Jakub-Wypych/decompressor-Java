package decompressor;

import decompressor.dictionary.RawDictionary;
import decompressor.dictionary.Tree;

import java.util.ArrayList;

/**
 * Stores the results from decompression
 * @param ident the identifier and its values
 * @param rawDictionary dictionary before it gets converted into a tree
 * @param tree tree
 */
public record Results(Ident ident, ArrayList<RawDictionary> rawDictionary, Tree tree) {
}
