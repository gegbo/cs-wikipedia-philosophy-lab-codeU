package com.flatironschool.javacs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import org.jsoup.select.Elements;

public class WikiPhilosophy {

        final static WikiFetcher wf = new WikiFetcher();
        ArrayList<String> done = new ArrayList<String>();
        int count = 0; //helps check if parentthesis are balanced
        /**
        * Tests a conjecture about Wikipedia and Philosophy.
        *
	* https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
	*
	* 1. Clicking on the first non-parenthesized, non-italicized link
	* 2. Ignoring external links, links to the current page, or red links
 * 3. Stopping when reaching "Philosophy", a page with no links or a page
      *    that does not exist, or when a loop occurs
               *
	                * @param args
			         * @throws IOException
				          */

					          public static void testPhilosophy(String start, String end) {
						                  String test = start;
								                  for(int count = 0; i<50; i++)
										                  {
												                          if(done.contains(test)){
															                                  return; //duplicate links (loops)
																			                          }
																						                          else
																									                          {
																												                                  done.add(test);
																																                          }

																																			                          Element link = getGoodLink(start);
																																						                          if(link == null) {
																																									                                  return; //no links on page
																																													                          }

																																																                          if (link.attr("abs:href").equals(end)) {
																																																			                                  break; //matches philosophy page
																																																							                          }
																																																										                  }
																																																												          }


																																																													          public static Element getGoodLink(String link) {
																																																														                  Elements paragraphs = wf.fetchWikipedia(link);

																																																																                  for (Element paragraph: paragraphs) {
																																																																		                          Element firstLink = firstLinkInPara(paragraph);
																																																																					                          if(firstLink!=null) {
																																																																								                                  return firstLink;
																																																																												                          }
																																																																															                  }
																																																																																	                  return null;
																																																																																			          }
																																																																																				          public static Element firstLinkInPara(Node head) {
																																																																																					                  Iterable<Node> iterate = new WikiNodeIterable(head);

																																																																																							                  for(Node node: head) {

																																																																																									                          //if(node instanceof TextNode) {
																																																																																												                          //      checkParen((TextNode)node);
																																																																																															                          //}

																																																																																																		                          if(node instanceof Element) {
																																																																																																					                                  Element aLink = findLink((Element)node);
																																																																																																									                                  if(aLink != null) {
																																																																																																													                                          return aLink;
																																																																																																																		                                  }
																																																																																																																						                          }
																																																																																																																									                  }
																																																																																																																											                  return null;
																																																																																																																													          }

																																																																																																																														          public static Element findLink(Element element) {
																																																																																																																															                  if(!element.tagName().equals("a")) { //symbol for a link
																																																																																																																																	                          return null;
																																																																																																																																				                  }
																																																																																																																																						                  char[] parenthesis_text = (String)element.toCharArray();

																																																																																																																																								                  for(char symbol: parenthesis_text)//checks if in parenthesis
																																																																																																																																										                  {
																																																																																																																																												                          if(symbol == '('){
																																																																																																																																															                                  count++;
																																																																																																																																																			                          }

																																																																																																																																																						                          if(symbol == ')'){

																																																																																																																																																									                                  count--;
																																																																																																																																																													                          }
																																																																																																																																																																                          if(count<0) { //string unbalanced from the start
																																																																																																																																																																			                                  return null;
																																																																																																																																																																							                          }

																																																																																																																																																																										                          if(count != 0)
																																																																																																																																																																													                          {
																																																																																																																																																																																                                  return null;
																																																																																																																																																																																				                          } 
																																																																																																																																																																																							                 }

																																																																																																																																																																																									                 for ( Element e = element; element !=null; e = element.parent())
																																																																																																																																																																																											 {
																																																																																																																																																																																											                         if(e.tagname().equals("i") || e.tagName().equals("em")){
																																																																																																																																																																																														                                 return null;
																																																																																																																																																																																																		                         }
																																																																																																																																																																																																					                 }
																																																																																																																																																																																																							                 //above checks if word is in italices
																																																																																																																																																																																																									                 return element;

																																																																																																																																																																																																											         }
																																																																																																																																																																																																												         public static void main(String[] args) throws IOException {

																																																																																																																																																																																																													                 String startUrl = "https://en.wikipedia.org/wiki/Washington_Univ
																																																																																																																																																																																																															 ersity_in_St._Louis";

																																																																																																																																																																																																															                 String finalUrl = "https://en.wikipedia.org/wiki/Philosophy";

																																																																																																																																																																																																																	                 testPhilosophy(startUrl,finalUrl);

																																																																																																																																																																																																																			         }
																																																																																																																																																																																																																				 }
