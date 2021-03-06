/* LanguageTool, a natural language style checker
 * Copyright (C) 2019 Sohaib Afifi, Taha Zerrouki
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.rules.ar;

import org.languagetool.AnalyzedTokenReadings;
import org.languagetool.language.Arabic;
import org.languagetool.rules.Example;
import org.languagetool.rules.WordRepeatRule;

import java.util.ResourceBundle;

/**
 * Word repeat rule for َArabic, to avoid false alarms in the generic word repetition rule.
 * @since 4.8
 */
public class ArabicWordRepeatRule extends WordRepeatRule {

  public ArabicWordRepeatRule(ResourceBundle messages) {
    super(messages, new Arabic());
    addExamplePair(Example.wrong("هذا <marker>فقط فقط</marker> مثال."),
      Example.fixed("هذا <marker>فقط</marker> مثال."));
  }

  @Override
  public String getId() {
    return "ARABIC_WORD_REPEAT_RULE";
  }

  @Override
  public boolean ignore(AnalyzedTokenReadings[] tokens, int position) {
    if (wordRepetitionOf("خطوة", tokens, position)) {
      return true;   // "نفذت التعليمات خطوة خطوة."
    }
    if (wordRepetitionOf("رويدا", tokens, position)) {
      return true;
    }
    return super.ignore(tokens, position);
  }

}
