/* LanguageTool, a natural language style checker 
 * Copyright (C) 2005 Daniel Naber (http://www.danielnaber.de)
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
package org.languagetool.rules.ca;

/*
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.jetbrains.annotations.NotNull;
*/
import org.languagetool.Language;
import org.languagetool.rules.Categories;
import org.languagetool.rules.ITSIssueType;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * A rule that matches lemmas found only in DNV (AVL dictionary) and suggests
 * alternative words. 
 * 
 * Catalan implementations. Loads the
 * relevant lemmas from <code>rules/ca/replace_dnv_colloquial.txt</code>.
 * 
 * @author Jaume Ortolà
 */
public class SimpleReplaceDNVColloquialRule extends AbstractSimpleReplaceLemmasRule {
/*
  private static final LoadingCache<String, Map<String, List<String>>> cache = CacheBuilder.newBuilder()
          .expireAfterWrite(30, TimeUnit.MINUTES)
          .build(new CacheLoader<String, Map<String, List<String>>>() {
            @Override
            public Map<String, List<String>> load(@NotNull String path) {
              return loadFromPath(path);
            }
          });
*/
  public SimpleReplaceDNVColloquialRule(final ResourceBundle messages, Language language) throws IOException {
    super(messages, language);
    wrongLemmas = loadFromPath("/ca/replace_dnv_colloquial.txt");
    super.setCategory(Categories.COLLOQUIALISMS.getCategory(messages));
    super.setLocQualityIssueType(ITSIssueType.Style);
  }  

  @Override
  public final String getId() {
    return "CA_SIMPLE_REPLACE_DNV_COLLOQUIAL";
  }

 @Override
  public String getDescription() {
    return "Detecta paraules marcades com a col·loquials en el DNV.";
  }

  @Override
  public String getShort() {
    return "Paraula o expressió col·loquial.";
  }
  
  @Override
  public String getMessage(String tokenStr,List<String> replacements) {
    return "Paraula o expressió col·loquial.";
  }
  
}
