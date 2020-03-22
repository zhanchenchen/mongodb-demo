package com.damon.service;

import com.damon.dao.CommentRepository;
import com.damon.po.Comment;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.List;

// 评论业务层
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 更新指定评论的点赞数+1
     * @param id
     */
    public void updateCommentLikeNum(String id){
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("likeNum");
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Comment.class);
    }

    /**
     * 根据父id查询分页列表
     * @param parentId
     * @param page
     * @param size
     * @return
     */
    public Page<Comment> findCommentListPageByParentId(String parentId,int page,int size){
        Page<Comment> commentPage = commentRepository.findByParentId(parentId, PageRequest.of(page - 1, size));
        return commentPage;
    }

    /**
     * 保存一个评论
     * @param comment
     */
    public void saveComment(Comment comment){
        //如果需要自定义主键，可以在这里指定主键；如果不指定主键，MongoDB会自动生成主键
        commentRepository.save(comment);
    }

    /**
     * 更新一个评论
     * @param comment
     */
    public void updateComment(Comment comment){
        commentRepository.save(comment);
    }

    /**
     * 删除一个评论
     * @param id
     */
    public void deleteComment(String id){
        commentRepository.deleteById(id);
    }

    /**
     * 查询所有评论
     * @return
     */
    public List<Comment> findCommentList(){
        return commentRepository.findAll();
    }

    /**
     * 根据id查询评论
     * @param id
     * @return
     */
    public Comment findById(String id){
        return commentRepository.findById(id).get();
    }
}
