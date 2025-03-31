package org.fastcampus.post.repository.jpa;

import org.fastcampus.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {
}
