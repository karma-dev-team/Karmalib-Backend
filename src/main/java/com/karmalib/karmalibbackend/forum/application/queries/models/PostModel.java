package com.karmalib.karmalibbackend.forum.application.queries.models;

import com.karmalib.karmalibbackend.file.application.queries.models.FileModel;
import com.karmalib.karmalibbackend.forum.domain.entities.PostEntity;
import com.karmalib.karmalibbackend.forum.domain.enums.PostStatus;
import lombok.Data;
import com.karmalib.karmalibbackend.common.application.BaseModel;
import com.karmalib.karmalibbackend.forum.domain.entities.CommentEntity;
import com.karmalib.karmalibbackend.user.application.queries.results.UserModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@SuperBuilder
public class PostModel {
    private UUID id;
    private UserModel author;
    private String title;
    private String text;
    private int likesCount;
    private int dislikesCount;
    private PostStatus status;
    private boolean pinned;
    private boolean hidden;
    private UUID approvedById;
    private List<CommentModel> comments;
    private Set<FileModel> attachments;
    private Set<PostTagModel> tags;

    /**
     * Создает экземпляр PostModel на основе PostEntity.
     *
     * @param entity сущность PostEntity
     * @return модель PostModel
     */
    public static PostModel fromEntity(PostEntity entity) {
        return PostModel.builder()
                .id(entity.id)
                .author(UserModel.fromEntity(entity.getUser()))
                .title(entity.getTitle())
                .text(entity.getText())
                .likesCount(entity.getLikesCount()) // Берем счетчик лайков
                .dislikesCount(entity.getDislikesCount()) // Берем счетчик дизлайков
                .status(entity.getStatus())
                .pinned(entity.getPinned() != null ? entity.getPinned() : false)
                .hidden(entity.isHidden())
                .approvedById(entity.getApprovedById())
                .comments(entity.getComments() != null
                        ? entity.getComments().stream().map(CommentModel::fromEntity).toList()
                        : List.of())
                .attachments(entity.getAttachments() != null
                        ? entity.getAttachments().stream().map(FileModel::fromEntity).collect(Collectors.toSet())
                        : Set.of())
                .build();
    }
}
