package com.damon.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 文章评论实体类
 */
//把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档。
// @Document(collection="mongodb 对应 collection 名")
// 若未加 @Document ，该 bean save 到 mongo 的 comment collection
// 若添加 @Document ，则 save 到 comment collection @Document(collection="comment")
// 可以省略，如果省略，则默认使用类名小写映射集合
// 复合索引
// @CompoundIndex( def = "{'userid': 1, 'nickname': -1}")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Comment implements Serializable {
    //主键标识，该属性的值会自动对应mongodb的主键字段"_id"，如果该属性名就叫“id”,则该注解可以省略，否则必须写    
    @Id
    private String id;//主键    
    // 该属性对应mongodb的字段的名字，如果一致，则无需该注解    
    @Field("content")
    private String content;//吐槽内容    
    private Date publishTime;//发布日期    
    // 添加了一个单字段的索引    
    @Indexed
    private String userId;//发布人ID    
    private String nickname;//昵称    
    private LocalDateTime createDateTime;//评论的日期时间    
    private Integer likeNum;//点赞数    
    private Integer replyNum;//回复数    
    private String state;//状态    
    private String parentId;//上级ID    
    private String articleId;
}