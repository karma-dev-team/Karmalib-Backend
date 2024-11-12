package com.karmalib.karmalibbackend.common.infrastrcuture.mailing;

public class HtmlEmailBuilder {
    private StringBuilder htmlContent;

    public HtmlEmailBuilder() {
        this.htmlContent = new StringBuilder();
        htmlContent.append("<html><body>");
    }

    public HtmlEmailBuilder addHeading(String heading) {
        htmlContent.append("<h1>").append(heading).append("</h1>");
        return this;
    }

    public HtmlEmailBuilder addParagraph(String paragraph) {
        htmlContent.append("<p>").append(paragraph).append("</p>");
        return this;
    }

    public HtmlEmailBuilder addBoldText(String text) {
        htmlContent.append("<b>").append(text).append("</b>");
        return this;
    }

    public HtmlEmailBuilder addLineBreak() {
        htmlContent.append("<br>");
        return this;
    }

    public String build() {
        htmlContent.append("</body></html>");
        return htmlContent.toString();
    }
}