package com.damon.dao;

import com.damon.po.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
// 评论的持久层接口
public interface CommentRepository extends MongoRepository<Comment,String> {

    // 根据父id查询子评论的分页列表
    Page<Comment> findByParentId(String parentId, Pageable pageable);

}
