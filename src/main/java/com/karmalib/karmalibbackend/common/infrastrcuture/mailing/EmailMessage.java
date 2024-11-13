package com.karmalib.karmalibbackend.common.infrastrcuture.mailing;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class EmailMessage {
    private String recipient;
    @Builder.Default
    private String sender = null;
    private String subject;
    private String body;
    private boolean isHtml;  // New field for HTML content
    private List<Attachment> attachments;  // New list of attachments

    public void addAttachment(String filename, byte[] content) {
        attachments.add(new Attachment(filename, content));
    }

    // Nested class for attachment details
    public static class Attachment {
        private final String filename;
        private final byte[] content;

        public Attachment(String filename, byte[] content) {
            this.filename = filename;
            this.content = content;
        }

        public String getFilename() {
            return filename;
        }

        public byte[] getContent() {
            return content;
        }
    }
}