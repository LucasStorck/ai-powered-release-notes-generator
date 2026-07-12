package org.lucas.releasenotes.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "release_notes")
public class ReleaseNote {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String rawPatch;

  @Column(nullable = false)
  private String cleanPatch;

  @Column(nullable = false)
  private String markdownSummary;

  @Column(nullable = false)
  private String modelUsed;

  @Column(nullable = false, updatable = false, name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false, name = "user_id")
  private User user;

  public ReleaseNote() {
  }

  public ReleaseNote(String rawPatch, String cleanPatch, String markdownSummary, String modelUsed, LocalDateTime createdAt, User user) {
    this.rawPatch = rawPatch;
    this.cleanPatch = cleanPatch;
    this.markdownSummary = markdownSummary;
    this.modelUsed = modelUsed;
    this.createdAt = createdAt;
    this.user = user;
  }

  public UUID getId() {
    return id;
  }

  public String getRawPatch() {
    return rawPatch;
  }

  public void setRawPatch(String rawPatch) {
    this.rawPatch = rawPatch;
  }

  public String getCleanPatch() {
    return cleanPatch;
  }

  public void setCleanPatch(String cleanPatch) {
    this.cleanPatch = cleanPatch;
  }

  public String getMarkdownSummary() {
    return markdownSummary;
  }

  public void setMarkdownSummary(String markdownSummary) {
    this.markdownSummary = markdownSummary;
  }

  public String getModelUsed() {
    return modelUsed;
  }

  public void setModelUsed(String modelUsed) {
    this.modelUsed = modelUsed;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
