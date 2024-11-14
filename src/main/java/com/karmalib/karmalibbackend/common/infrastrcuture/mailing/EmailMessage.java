package com.karmalib.karmalibbackend.common.infrastrcuture.mailing;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@Builder
public class EmailMessage {
    @NotEmpty
    private String recipient;
    @Builder.Default
    private String sender = null;
    @NotEmpty
    private String subject;
    private String body;
    @Builder.Default
    private boolean isHtml = false;  // New field for HTML content
    @Builder.Default
    private List<Attachment> attachments = null;  // New list of attachments

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