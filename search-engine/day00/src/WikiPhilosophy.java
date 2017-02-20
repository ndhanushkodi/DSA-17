import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiPhilosophy {

	private final static WikiFetcher wf = new WikiFetcher();
	final static List<String> visited = new ArrayList<String>();
	/**
	 * Tests a conjecture about Wikipedia and Philosophy.
	 * https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
	 *
	 * 1. Clicking on the first non-parenthesized, non-italicized link
     * 2. Ignoring external links, links to the current page, or red links
     * 3. Stopping when reaching "Philosophy", a page with no links or a page
     *    that does not exist, or when a loop occurs
	 */
	public static void main(String[] args) throws IOException {

		String destination = "https://en.wikipedia.org/wiki/Philosophy";
		String source = "https://en.wikipedia.org/wiki/Java_(programming_language)";

		testConjecture(destination, source, 10);
	}

	/**
	 * Starts from given URL and follows first link until it finds the destination or exceeds the limit.
	 *
	 * If you hit the limit or get stuck in a loop, throw an IllegalArgumentException()
     *
	 * @param source: url to start from
	 * @param destination: url to end up at
	 * @param limit: maximum number of clicks before we give up
	 */
	public static boolean testConjecture(String destination, String source, int limit) throws IOException {
		// Right now, this method tries the first link on the page, and if it is the destination, it returns true
		// TODO: fix this method.
		// Loop until reach limit, get stuck in a loop, reach a page with no links, or reach the destination
//		Element elt = getFirstValidLink(source);
//		String url = elt.attr("abs:href");
//		if (url.equals(destination))
//			return true;
//		return false;
		String url = source;
		for (int i=0; i<limit; i++) {
			if (visited.contains(url)) {
				System.err.println("We're in a loop, exiting.");
				return false;
			} else {
				visited.add(url);
			}
			Element elt = getFirstValidLink(url);
			if (elt == null) {
				System.err.println("Got to a page with no valid links.");
				return false;
			}

			System.out.println("**" + elt.text() + "**");
			url = elt.attr("abs:href");

			if (url.equals(destination)) {
				System.out.println("Eureka!");
				return true;
			}
		}
		return false;
	}

	/**
	 * @param url: url of the page we are visitng
	 * @return the Element containing the first hyperlink, or null.
	 */
	private static Element getFirstValidLink(String url) throws IOException {
		Elements paragraphs = wf.fetchWikipedia(url);
		WikiParser wp = new WikiParser(paragraphs);
		Element elt = wp.findFirstLink();
		return elt;
	}
}
