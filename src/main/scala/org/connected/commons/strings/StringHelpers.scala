/*
 * Copyright  2019 Gopal Tiwari
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.connected.commons.strings

object StringHelpers
{
  /**
    * If str is surrounded by quotes it return the content between the quotes
    *
    * @param str String needs to be unquoted.
    * @return it return the content between the quotes.
    */
  def unquote(str: String): String =
  {
    if (str != null && str.length >= 2 && str.charAt(0) == '\"' && str.charAt(str.length - 1) == '\"')
      str.substring(1, str.length - 1)
    else
      str
  }

  /**
    * Turn a string of format "FooBar" into snake case "foo_bar"
    *
    * Note: snakify is not reversible, ie. in general the following will _not_ be true:
    *
    * s == camelify(snakify(s))
    *
    * @return the underscored string
    */
  def snakify(name: String) = name.replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2").replaceAll("([a-z\\d])([A-Z])", "$1_$2").toLowerCase

  def camelify(name : String): String = {
    def loop(x : List[Char]): List[Char] = (x: @unchecked) match {
      case '_' :: '_' :: rest => loop('_' :: rest)
      case '_' :: c :: rest => Character.toUpperCase(c) :: loop(rest)
      case '_' :: Nil => Nil
      case c :: rest => c :: loop(rest)
      case Nil => Nil
    }
    if (name == null)
      ""
    else
      loop('_' :: name.toList).mkString
  }
}
