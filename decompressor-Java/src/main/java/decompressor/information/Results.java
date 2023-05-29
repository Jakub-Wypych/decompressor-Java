package decompressor.information;

import decompressor.information.dictionary.RawDictionary;
import decompressor.information.dictionary.Tree;

import java.util.ArrayList;

/**
 * Stores the results from deciphering
 * @param ident the identifier and its values
 * @param rawDictionary dictionary before it gets converted into a tree
 * @param tree tree
 */
public record Results(Ident ident, ArrayList<RawDictionary> rawDictionary, Tree tree) {
}
