package decompressor;

import decompressor.dictionary.RawDictionary;
import decompressor.dictionary.Tree;

import java.util.ArrayList;

/* Stores the results from decompression
 */
public record Results(Ident ident, ArrayList<RawDictionary> rawDictionary, Tree tree) {
}
