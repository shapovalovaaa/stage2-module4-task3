package com.mjc.stage2.parser;

import com.mjc.stage2.entity.AbstractTextComponent;
import com.mjc.stage2.entity.SymbolLeaf;
import com.mjc.stage2.entity.TextComponent;
import com.mjc.stage2.entity.TextComponentType;

public class LexemeParser extends AbstractTextParser {
    private static final String LEXEME_REGEX = "\\s+";
    private static final String WORD_REGEX = "\\(?\\w[\\w!=?(),'.\\-:]+";

    @Override
    public void parse(AbstractTextComponent abstractTextComponent, String string) {
        if (abstractTextComponent.getComponentType() == TextComponentType.SENTENCE) {
            String[] lexemes = string.split(LEXEME_REGEX);
            AbstractTextComponent newComponent;
            for (String lexeme : lexemes) {
                if (lexeme.matches(WORD_REGEX)) {
                    newComponent = new TextComponent(TextComponentType.WORD);
                    if (nextParser != null) {
                        nextParser.parse(newComponent, lexeme);
                    }
                } else {
                    newComponent = new SymbolLeaf(TextComponentType.SYMBOL, lexeme.charAt(0));
                }

                abstractTextComponent.add(newComponent);
            }
        } else if (nextParser != null) {
            nextParser.parse(abstractTextComponent, string);
        }
    }
    // Write your code here!
}