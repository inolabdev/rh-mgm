package mz.inolabdev.rh.dao.impl;

import org.springframework.stereotype.Repository;

import mz.inolabdev.rh.dao.CommentDao;
import mz.inolabdev.rh.entity.Comment;

@Repository("commentDao")
public class CommentDaoImpl extends GenericDaoImpl<Comment> implements
		CommentDao {

}
