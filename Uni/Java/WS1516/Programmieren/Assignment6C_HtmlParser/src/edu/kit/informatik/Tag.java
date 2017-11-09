package edu.kit.informatik;

import java.util.ArrayList;

/**
 * The Class Tag.
 * 
 * @author Hannes Kuchelmeister
 * @version 1.0
 */
public class Tag {
    private final String name;
    private final ArrayList<Pair<Integer, String>> text;
    private final ArrayList<Tag> subTags;

    /**
     * Instantiates a new tag.
     *
     * @param str
     *            the string which starts with the tag and ends with the tag
     */
    public Tag(final String str) {
        final String noEnter = str.replace("\n", " ");
        text = new ArrayList<Pair<Integer, String>>();

        subTags = new ArrayList<Tag>();
        final String[] splitted = noEnter.split("(?<=" + ">)|(?=(</)" + ")");
        name = getTagName(splitted[0].trim());

        int openTag = -1;
        String subTagName = "";
        for (int i = 1; i < splitted.length; i++) {
            // closing tag
            if (splitted[i].trim().matches("[<][\\/]([a-z0-9]|)+[>]") && openTag >= 0) {
                if (getTagName(splitted[i].trim()).equals(subTagName)) {
                    String sub = "";
                    for (int j = openTag; j <= i; j++) {
                        sub += splitted[j];
                    }
                    subTags.add(new Tag(sub));
                    openTag = -1;
                }
            }
            // opening tag
            if (splitted[i].trim().matches("[</][a-z0-9]+[>]") && openTag == -1) {
                openTag = i;
                subTagName = getTagName(splitted[i].trim());
            }
            // text
            if (splitted[i].trim().matches("([a-zA-Z0-9_-](\\s[a-zA-Z0-9_-])?)+") && openTag == -1) {
                final String[] tmpStrArr = splitted[i].split(" ");
                for (final String string : tmpStrArr) {
                    this.text.add(new Pair<Integer, String>(new Integer(subTags.size()), string.toLowerCase()));
                }
            }
        }
    }

    /**
     * Searches f.
     *
     * @param tagName
     *            the tag name
     * @return the string
     */
    public String getTagText(final String tagName) {
        if (this.name.equals(tagName)) {
            return this.toString();
        }

        String ret = "";
        for (final Tag tag : subTags) {
            if (!tag.getTagText(tagName).equals("")) {
                ret += tag.getTagText(tagName) + "\n";
            }
        }
        return ret.trim();
    }

    private String getTagName(final String tag) {
        String tmp = "";
        for (final char c : tag.toCharArray()) {
            if (c != '<' && c != '>' && c != '/') {
                tmp += Character.toString(c);
            }
        }
        return tmp;
    }

    /**
     * Finds the corresponding text to a tag
     */
    private String intToText(final int i) {
        String ret = "";
        for (final Pair<Integer, String> pair : text) {
            if (pair.getFirst() == i) {
                ret += pair.getSecond() + " ";
            }
        }
        return ret.trim();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < subTags.size() || i < text.size(); i++) {
            str += intToText(i);
            if (i < subTags.size()) {
                str += subTags.get(i).toString() + " ";
            }
        }
        return str.trim();
    }

}
